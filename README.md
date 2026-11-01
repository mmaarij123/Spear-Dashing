<div align="center">

<img src="src/main/resources/assets/speardash/icon.png" alt="Spear Dashing" width="88" />

# Spear Dashing V1.1

**One key. A quick swap. Your next dash.**

Automatic spear swapping and dash controls for Minecraft Java Edition on Fabric.

![Version](https://img.shields.io/badge/mod_version-1.1-7c3aed?style=flat-square)
![Minecraft](https://img.shields.io/badge/Minecraft-26.1%20%E2%80%93%2026.3%20%7C%201.21.11-62a04d?style=flat-square)
![Fabric](https://img.shields.io/badge/loader-Fabric-dbb790?style=flat-square)
![Java](https://img.shields.io/badge/Java-25%2B_for_26.x-e89539?style=flat-square)
![Environment](https://img.shields.io/badge/environment-Client-64748b?style=flat-square)

[Download your version](#supported-minecraft-versions) · [Required dependencies](#must-download-fzzy-config) · [Installation](#getting-started) · [Settings](#settings) · [Build from source](#build-from-source)

</div>

---

## Supported Minecraft versions

**The mod version stays V1.1.** There are five separately compiled Minecraft 26.x builds, plus the original 1.21.11 release. Select the JAR matching your **exact Minecraft version**.

| Minecraft | Download JAR | Release notes |
| :--- | :--- | :--- |
| **26.3** | [Spear Dashing V1.1 MC26.3.jar](https://github.com/mmaarij123/Spear-Dashing/releases/download/v1.1-mc26.3/Spear.Dashing.V1.1.MC26.3.jar) | [26.3 release](https://github.com/mmaarij123/Spear-Dashing/releases/tag/v1.1-mc26.3) |
| **26.2** | [Spear Dashing V1.1 MC26.2.jar](https://github.com/mmaarij123/Spear-Dashing/releases/download/v1.1-mc26.2/Spear.Dashing.V1.1.MC26.2.jar) | [26.2 release](https://github.com/mmaarij123/Spear-Dashing/releases/tag/v1.1-mc26.2) |
| **26.1.2** | [Spear Dashing V1.1 MC26.1.2.jar](https://github.com/mmaarij123/Spear-Dashing/releases/download/v1.1-mc26.1.2/Spear.Dashing.V1.1.MC26.1.2.jar) | [26.1.2 release](https://github.com/mmaarij123/Spear-Dashing/releases/tag/v1.1-mc26.1.2) |
| **26.1.1** | [Spear Dashing V1.1 MC26.1.1.jar](https://github.com/mmaarij123/Spear-Dashing/releases/download/v1.1-mc26.1.1/Spear.Dashing.V1.1.MC26.1.1.jar) | [26.1.1 release](https://github.com/mmaarij123/Spear-Dashing/releases/tag/v1.1-mc26.1.1) |
| **26.1** | [Spear Dashing V1.1 MC26.1.jar](https://github.com/mmaarij123/Spear-Dashing/releases/download/v1.1-mc26.1/Spear.Dashing.V1.1.MC26.1.jar) | [26.1 release](https://github.com/mmaarij123/Spear-Dashing/releases/tag/v1.1-mc26.1) |
| **1.21.11** | [Spear Dashing V1.1.jar](https://github.com/mmaarij123/Spear-Dashing/releases/download/v1.1/Spear.Dashing.V1.1.jar) | [Original release](https://github.com/mmaarij123/Spear-Dashing/releases/tag/v1.1) |

Install one Spear Dashing JAR per profile. Compiled JARs are distributed through **[GitHub Releases](https://github.com/mmaarij123/Spear-Dashing/releases)**; the source ZIP and source tarball are for development.

## Requirements

| Build | Java | Loader | Required mods |
| :--- | :--- | :--- | :--- |
| **26.1, 26.1.1, 26.1.2, 26.2, 26.3** | **25+** | **Fabric Loader 0.19.5+** | Matching **Fabric API**, **Fzzy Config**, and **Fabric Language Kotlin** |
| **Original 1.21.11** | **21+** | Fabric for Minecraft 1.21.11 | Matching **Fabric API**, **Fzzy Config**, and its **Fabric Language Kotlin** dependency |

Spear Dashing runs on the **client**. The 26.x startup checks used Fabric Loader **0.20.1** and the dependency versions listed below.

## Must download: Fzzy Config

> **MUST DOWNLOAD: Fzzy Config is a required dependency. Spear Dashing will not load or work without it.**

Fzzy Config provides the configuration system used by the mod's settings. It also requires **Fabric API** and **Fabric Language Kotlin**. Place all dependency JARs in the **same profile's `mods` folder** as Spear Dashing.

### Minecraft 26.x dependency downloads

| Minecraft | Fabric API | Fzzy Config — required | Fabric Language Kotlin — required |
| :--- | :--- | :--- | :--- |
| **26.1 / 26.1.1 / 26.1.2** | [0.155.3+26.1.2](https://modrinth.com/mod/fabric-api/version/3dM0X6ou) | [0.7.7+26.1](https://modrinth.com/mod/fzzy-config/version/Ugxie86a) | [1.14.2+kotlin.2.4.21](https://modrinth.com/mod/fabric-language-kotlin/version/SKW1BTL3) |
| **26.2** | [0.161.0+26.2](https://modrinth.com/mod/fabric-api/version/ewUK83HI) | [0.7.7+26.2](https://modrinth.com/mod/fzzy-config/version/tZ4vc79R) | [1.14.2+kotlin.2.4.21](https://modrinth.com/mod/fabric-language-kotlin/version/SKW1BTL3) |
| **26.3** | [0.165.0+26.3](https://modrinth.com/mod/fabric-api/version/EQKgpeKu) | [0.7.7+fix3+26.3](https://modrinth.com/mod/fzzy-config/version/YkOumqzV) | [1.14.2+kotlin.2.4.21](https://modrinth.com/mod/fabric-language-kotlin/version/SKW1BTL3) |

The linked dependency files in the first row support all three 26.1-family versions. The **Spear Dashing JAR itself must still match your exact game version**. The [pinned dependency manifest](ports/versions.json) records the files used for compilation and startup checks.

### Original Minecraft 1.21.11 dependencies

**[Download Fzzy Config 0.7.7 for 1.21.11 / Fabric — MUST DOWNLOAD](https://cdn.modrinth.com/data/hYykXjDp/versions/5SSZmpdx/fzzy_config-0.7.7%2B1.21.11.jar?mr_download_reason=standalone&mr_game_version=1.21.11&mr_loader=fabric)**

File: `fzzy_config-0.7.7+1.21.11.jar` · [Version details](https://modrinth.com/mod/fzzy-config/version/5SSZmpdx)

Also install the appropriate Minecraft 1.21.11 versions of [Fabric API](https://modrinth.com/mod/fabric-api) and [Fabric Language Kotlin](https://modrinth.com/mod/fabric-language-kotlin).

## Getting started

1. Close Minecraft and select a Fabric profile for the game version you want to play.
2. Check that the profile uses the required Java version from the [requirements table](#requirements).
3. Download the matching Spear Dashing JAR from the [release table](#supported-minecraft-versions).
4. Download **Fabric API**, **Fzzy Config**, and **Fabric Language Kotlin** for that game version.
5. Put the mod and its dependency JARs in the profile's `mods` folder.
6. Keep only one Spear Dashing JAR in the profile, then launch Minecraft.

### Your first dash

1. Put a **Lunge-enchanted spear in your hotbar**.
2. Press **R**, the default Spear Dashing key. You can rebind it in Minecraft's Controls settings.
3. Tap for a single input, or hold the key for repeated attempts.
4. Start with the default **2-tick** interval; choose **1 tick** for the fastest configured retry.

The mod swaps to a suitable spear and restores the original hotbar slot between swaps. New dash input is ignored while a menu is open, and pending actions are cleared when the player or world is unavailable.

> The 1–5 tick setting controls retry and swap-back timing. Minecraft's attack cooldown and server mechanics still determine when a dash succeeds. A one-tick setting does not guarantee a successful dash every tick.

## Features & settings

- **Tap or hold:** held input is sampled every client tick, with queued short presses also detected.
- **Configurable timing:** adjust the swap-back and minimum repeat interval from 1–5 ticks.
- **Hotbar restoration:** complete the previous restoration before beginning another swap.
- **Optional effects:** enable or disable sound and particle effects.
- **Version-specific builds:** separate compiled JARs for each supported 26.x target.

## Settings

| Setting | Default | Purpose |
| :--- | :---: | :--- |
| `dashing` | `true` | Enables automatic spear swapping and dashing. |
| `swapDelayTicks` | `2` | Swap-back and minimum repeat interval, clamped to **1–5 ticks**. |
| `soundEffects` | `true` | Enables sound effects. |
| `particleEffects` | `true` | Enables particle effects. |
| `omnidirectionalDashing` | `false` | Requests movement-key direction handling in the applicable code paths. |
| `rangeExtensions` | `true` | Requests spear swapping for out-of-range hits in the applicable advanced code path. |

**Advanced settings status:** the inherited `advancedFeaturesEnabled` flag remains `false`. Some directional and range-related paths are therefore disabled, while fallback handling remains. These settings are not presented as fully enabled or gameplay-verified features. The main dash, interval, hotbar and effects settings remain available.

## What's new in V1.1

### Minecraft 26.x releases

- Adds separately compiled Fabric builds for **26.1**, **26.1.1**, **26.1.2**, **26.2**, and **26.3**, keeping the mod version at **1.1**.
- Ports Minecraft references to the game's official, unobfuscated class names and Java 25.
- Updates Fabric key registration to `KeyMappingHelper.registerKeyMapping`.
- Handles screen access through a small adapter for each API family; 26.3 also uses the renamed keyboard input type.
- Validates the attack-invoker target and gives every JAR an exact Minecraft-version requirement.
- Adds pinned dependency downloads, checksum checks, and a build command for the full five-target matrix.

### Existing dash improvements

- Tick-based held-key sampling instead of relying on operating-system keyboard repeat.
- Queued-press handling for short taps.
- A configured retry interval in place of the original fixed eight-tick input lockout.
- Original-slot restoration during continuous use and searches limited to valid hotbar slots **0–8**.
- Menu and world-state handling for pending dash actions.

Direction-changing dashes retain the original one-tick rotation setup. The [recovery notes](docs/RECOVERY.md) document the original 1.21.11 changes, and the [26.x port guide](ports/README.md) documents the API updates.

## Troubleshooting

| Problem | What to check |
| :--- | :--- |
| Missing `fzzy_config` dependency | Install the matching **Fzzy Config** JAR from the dependency table. |
| Missing Fabric API or Fabric Language Kotlin | Install both libraries for the selected Minecraft version. Fzzy Config requires them. |
| Unsupported Java or class-file-version error | Minecraft 26.x needs **Java 25+**; the original 1.21.11 build needs **Java 21+**. Check the launcher profile's Java selection. |
| Incompatible Minecraft version or duplicate mod | Use the exact target JAR and keep one Spear Dashing version in the profile. |
| The dash does not trigger | Check that dashing is enabled, **R** is bound correctly, a Lunge-enchanted spear is in the hotbar, a menu is not open, and the attack cooldown permits another attempt. |

## Build from source

### Minecraft 26.x — JDK 25+ and Python 3.10+

From the repository root, build all five targets:

```powershell
python ports/build.py --all
```

Or build one target:

```powershell
python ports/build.py --minecraft 26.3
```

The builder uses Python's standard library plus `javac` and `javap` from the JDK. It downloads pinned game manifests and dependencies, checks their hashes, compiles the common source with the correct version adapter, and packages a target-specific JAR.

Outputs are saved under `build/ports/<minecraft-version>/`, alongside `SHA256SUMS` and `build-report.json`. For example:

```text
build/ports/26.3/Spear Dashing V1.1 MC26.3.jar
```

See the [complete port build guide](ports/README.md) for all output paths and API differences.

<details>
<summary><strong>Build the original Minecraft 1.21.11 release</strong></summary>

The original source was recovered and modified from the supplied Spear Dashing 1.0 JAR. It retains Fabric intermediary names and compiles against an installed Modrinth profile's runtime caches.

With a JDK 21+ on `PATH`, run:

```powershell
./build.ps1
```

The default profile is `Remarkably Optimized` under `%APPDATA%/ModrinthApp`. To select a different profile:

```powershell
./build.ps1 -ModrinthRoot 'D:/Games/ModrinthApp' -ProfileName 'My Fabric Profile'
```

This legacy build script specifically expects these cached paths:

```text
.fabric/remappedJars/minecraft-1.21.11-0.18.6/client-intermediary.jar
mods/fzzy_config-0.7.6+1.21.11.jar
```

The cached **0.7.6** filename is a source-build requirement of that script; the **0.7.7** link above is the installation download supplied for players.

Output: `build/Spear Dashing V1.1.jar`.

</details>

## Validation & known limits

| Build family | Compilation | Client startup | In-world dashing / multiplayer |
| :--- | :--- | :--- | :--- |
| **All five 26.x targets** | Passed separately for every target | Passed in isolated Fabric clients | **Not tested** |
| **Original 1.21.11** | Compilation and packaged-bytecode checks recorded in the recovery notes | Not verified in the recovery record | **Not tested** |

The 26.x startup checks verified the title screen, Fzzy Config registration, dash key binding registration, and application of the attack-invoker mixin. Each published 26.x JAR was also downloaded and its SHA-256 checked against the built artifact.

These checks do not establish in-world dash timing, server acceptance, or compatibility with unrelated mods. See [VALIDATION.json](ports/VALIDATION.json) for per-target results and hashes, and [release details](docs/RELEASES.md) for the original artifact's checksum.

To check gameplay, use a test world and try tapping and holding **R**, intervals 1–5, opening and closing the inventory, and leaving/rejoining the world. Confirm the original selected hotbar slot returns after releasing the key.

## Project layout

```text
Spear-Dashing/
├── src/main/java/speardash/     # Original 1.21.11 source
├── src/main/resources/         # Shared icon, metadata templates and license notices
├── ports/
│   ├── src/main/java/          # Common Minecraft 26.x source
│   ├── compat/                 # 26.1-family, 26.2 and 26.3 adapters
│   ├── versions.json           # Pinned game manifests and dependencies
│   ├── build.py                # Five-target Java 25 builder
│   ├── VALIDATION.json         # Per-target startup results and artifact hashes
│   └── README.md               # Detailed port guide
├── docs/
│   ├── RECOVERY.md             # Original recovery and change record
│   └── RELEASES.md             # Release links and original artifact details
├── build.ps1                   # Original 1.21.11 build script
├── LICENSE
└── THIRD_PARTY_NOTICES.md
```

## Credits & license

**Maarij** is credited in the mod metadata. Original contributions covered by the [proprietary license](LICENSE) are **All Rights Reserved**; personal gameplay with the unmodified published JAR is permitted under that notice.

Recovered upstream code and material previously released under **CC0** keep their existing permissions. The proprietary notice does not revoke those rights. See [THIRD_PARTY_NOTICES.md](THIRD_PARTY_NOTICES.md) for the full scope.

---

<div align="center">
<sub>Spear Dashing V1.1 · Fabric · Five Minecraft 26.x builds + original 1.21.11</sub>
</div>
