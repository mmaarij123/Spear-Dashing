"""Pinned Java 25 builds for unobfuscated Minecraft 26.x Fabric clients."""
import argparse
import concurrent.futures
import hashlib
import json
import os
from pathlib import Path
import re
import subprocess
import tempfile
import urllib.request
import zipfile

ROOT = Path(__file__).resolve().parents[1]
PORTS = ROOT / 'ports'
LOCK = json.loads((PORTS / 'versions.json').read_text(encoding='utf-8'))


def obtain(url, path, algorithm, expected):
    """Download only verified files; never trust an unverified cached artifact."""
    path.parent.mkdir(parents=True, exist_ok=True)
    if path.exists() and hashlib.new(algorithm, path.read_bytes()).hexdigest() == expected:
        return path
    request = urllib.request.Request(url, headers={'User-Agent': 'Spear-Dashing-Fabric-Build/1.1'})
    with urllib.request.urlopen(request, timeout=120) as response:
        data = response.read()
    if hashlib.new(algorithm, data).hexdigest() != expected:
        raise RuntimeError(f'Checksum mismatch: {path.name}')
    with tempfile.NamedTemporaryFile(dir=path.parent, delete=False) as temporary:
        temporary.write(data)
        temp_path = Path(temporary.name)
    temp_path.replace(path)
    return path


def nested_jars(path, cache):
    paths = [path]
    with zipfile.ZipFile(path) as archive:
        for name in archive.namelist():
            if name.startswith('META-INF/jars/') and name.endswith('.jar'):
                target = cache / 'nested' / path.name / Path(name).name
                target.parent.mkdir(parents=True, exist_ok=True)
                target.write_bytes(archive.read(name))
                paths.append(target)
    return paths


def build(version, cache, output):
    target = LOCK['targets'][version]
    spec = target['minecraft_manifest']
    manifest_path = obtain(spec['url'], cache / f'manifest-{version}.json', 'sha1', spec['sha1'])
    manifest = json.loads(manifest_path.read_text(encoding='utf-8'))
    game = manifest['downloads']['client']
    client = obtain(game['url'], cache / f'minecraft-{version}.jar', 'sha1', game['sha1'])
    jobs = []
    for library in manifest['libraries']:
        artifact = library.get('downloads', {}).get('artifact')
        if artifact and ':natives-' not in library['name']:
            jobs.append((artifact['url'], cache / 'libraries' / artifact['path'], 'sha1', artifact['sha1']))
    for library in LOCK['loader_libraries']:
        jobs.append((library['url'], cache / 'libraries' / library['path'], 'sha256', library['sha256']))
    with concurrent.futures.ThreadPoolExecutor(max_workers=8) as pool:
        libraries = list(pool.map(lambda job: obtain(*job), jobs))
    mods = []
    classpath = [client] + libraries
    for mod in target['mods'].values():
        path = obtain(mod['url'], cache / mod['filename'], 'sha512', mod['sha512'])
        mods.append(path)
        classpath.extend(nested_jars(path, cache))
    classpath = list(dict.fromkeys(path.resolve() for path in classpath))

    # This string target is not checked by javac, so validate the invoker separately.
    minecraft_methods = subprocess.check_output(['javap', '-p', '-classpath', str(client), 'net.minecraft.client.Minecraft'], text=True)
    if 'private boolean startAttack();' not in minecraft_methods:
        raise RuntimeError(f'{version}: Minecraft.startAttack() invoker target changed')

    destination = output / version
    destination.mkdir(parents=True, exist_ok=True)
    filename = f'Spear Dashing V1.1 MC{version}.jar'
    jar_path = destination / filename
    sources = sorted((PORTS / 'src/main/java').rglob('*.java'))
    sources += sorted((PORTS / 'compat' / target['compat']).rglob('*.java'))
    with tempfile.TemporaryDirectory(prefix='compile-', dir=destination) as temporary:
        classes = Path(temporary) / 'classes'
        classes.mkdir()
        arguments = ['--release', '25', '-proc:none', '-classpath', os.pathsep.join(map(str, classpath)), '-d', str(classes)]
        arguments += list(map(str, sources))
        argfile = Path(temporary) / 'javac.args'
        argfile.write_text('\n'.join('"' + value.replace('\\', '/').replace('"', '\\"') + '"' for value in arguments), encoding='utf-8')
        subprocess.run(['javac', '@' + str(argfile)], check=True)
        metadata = json.loads((ROOT / 'src/main/resources/fabric.mod.json').read_text(encoding='utf-8'))
        metadata['version'] = LOCK['mod_version']
        metadata['depends'].update({'minecraft': '=' + version, 'java': '>=25', 'fabricloader': '>=0.19.5', 'fzzy_config': '>=0.7.7', 'fabric-language-kotlin': '>=1.13.11+kotlin.2.3.21'})
        metadata['custom'] = {'speardash:target_minecraft': version}
        resources = ROOT / 'src/main/resources'
        with zipfile.ZipFile(jar_path, 'w', zipfile.ZIP_DEFLATED) as archive:
            for path in sorted(classes.rglob('*.class')):
                payload = path.read_bytes()
                assert int.from_bytes(payload[6:8], 'big') == 69, 'Expected Java 25 class files'
                assert b'net/minecraft/class_' not in payload, 'Legacy intermediary symbols remain'
                archive.writestr(path.relative_to(classes).as_posix(), payload)
            for path in sorted(resources.rglob('*')):
                if not path.is_file():
                    continue
                relative = path.relative_to(resources).as_posix()
                if relative in ['fabric.mod.json', 'META-INF/MANIFEST.MF']:
                    continue
                payload = path.read_bytes()
                if relative.endswith('.mixins.json'):
                    mixin = json.loads(payload)
                    mixin['compatibilityLevel'] = 'JAVA_25'
                    payload = (json.dumps(mixin, indent=2) + '\n').encode('utf-8')
                archive.writestr(relative, payload)
            archive.writestr('fabric.mod.json', json.dumps(metadata, indent=2) + '\n')
            archive.writestr('META-INF/MANIFEST.MF', f'Manifest-Version: 1.0\r\nImplementation-Title: Spear Dashing\r\nImplementation-Version: 1.1\r\nMinecraft-Version: {version}\r\n\r\n')
        with zipfile.ZipFile(jar_path) as archive:
            assert archive.testzip() is None
            assert archive.read('LICENSE_proprietary') == (ROOT / 'LICENSE').read_bytes()
            assert archive.read('LICENSE_speardash') == (resources / 'LICENSE_speardash').read_bytes()
            assert archive.read('THIRD_PARTY_NOTICES.md') == (ROOT / 'THIRD_PARTY_NOTICES.md').read_bytes()
            assert 'speardash/VersionCompat.class' in archive.namelist()
    checksum = hashlib.sha256(jar_path.read_bytes()).hexdigest()
    (destination / 'SHA256SUMS').write_text(f'{checksum}  {filename}\n', encoding='utf-8')
    report = {'minecraft': version, 'mod_version': LOCK['mod_version'], 'java': 25, 'jar': filename, 'sha256': checksum, 'dependency_versions': {key: value['version'] for key, value in target['mods'].items()}, 'compilation': 'passed', 'mixin_target': 'Minecraft.startAttack()Z verified', 'gameplay_tested': False}
    (destination / 'build-report.json').write_text(json.dumps(report, indent=2) + '\n', encoding='utf-8')
    print(f'{version}: {jar_path} (SHA-256 {checksum})', flush=True)
    return jar_path


def main():
    parser = argparse.ArgumentParser(description=__doc__)
    target = parser.add_mutually_exclusive_group(required=True)
    target.add_argument('--all', action='store_true', help='Build all five supported game versions')
    target.add_argument('--minecraft', choices=LOCK['targets'])
    parser.add_argument('--cache-dir', type=Path, default=ROOT / 'build/port-cache')
    parser.add_argument('--out-dir', type=Path, default=ROOT / 'build/ports')
    args = parser.parse_args()
    javac = subprocess.check_output(['javac', '-version'], stderr=subprocess.STDOUT, text=True)
    major = re.search(r'javac (\d+)', javac)
    if not major or int(major.group(1)) < 25:
        parser.error('A JDK 25 or newer must provide javac and javap on PATH.')
    for version in LOCK['targets'] if args.all else [args.minecraft]:
        build(version, args.cache_dir.resolve(), args.out_dir.resolve())


if __name__ == '__main__':
    main()
