# Spear Dashing V1.1 — Fabric 26.x

Five separately compiled builds target **26.1**, **26.1.1**, **26.1.2**, **26.2**, and **26.3**. Every JAR reports mod version **1.1** and declares one exact supported Minecraft version.

## Required downloads

**Must download: Fzzy Config. Spear Dashing will not load or work without it.** Fzzy Config also requires Fabric API and Fabric Language Kotlin. Install all three dependency JARs alongside the matching Spear Dashing release in your profile's `mods` folder.

All five ports require **Java 25 or newer** and **Fabric Loader 0.19.5 or newer**. Startup validation used **Fabric Loader 0.20.1**.

| Minecraft | Fabric API | Fzzy Config — required | Fabric Language Kotlin — required |
| :--- | :--- | :--- | :--- |
| 26.1 | [0.155.3+26.1.2](https://modrinth.com/mod/fabric-api/version/3dM0X6ou) | [0.7.7+26.1](https://modrinth.com/mod/fzzy-config/version/Ugxie86a) | [1.14.2+kotlin.2.4.21](https://modrinth.com/mod/fabric-language-kotlin/version/SKW1BTL3) |
| 26.1.1 | [0.155.3+26.1.2](https://modrinth.com/mod/fabric-api/version/3dM0X6ou) | [0.7.7+26.1](https://modrinth.com/mod/fzzy-config/version/Ugxie86a) | [1.14.2+kotlin.2.4.21](https://modrinth.com/mod/fabric-language-kotlin/version/SKW1BTL3) |
| 26.1.2 | [0.155.3+26.1.2](https://modrinth.com/mod/fabric-api/version/3dM0X6ou) | [0.7.7+26.1](https://modrinth.com/mod/fzzy-config/version/Ugxie86a) | [1.14.2+kotlin.2.4.21](https://modrinth.com/mod/fabric-language-kotlin/version/SKW1BTL3) |
| 26.2 | [0.161.0+26.2](https://modrinth.com/mod/fabric-api/version/ewUK83HI) | [0.7.7+26.2](https://modrinth.com/mod/fzzy-config/version/tZ4vc79R) | [1.14.2+kotlin.2.4.21](https://modrinth.com/mod/fabric-language-kotlin/version/SKW1BTL3) |
| 26.3 | [0.165.0+26.3](https://modrinth.com/mod/fabric-api/version/EQKgpeKu) | [0.7.7+fix3+26.3](https://modrinth.com/mod/fzzy-config/version/YkOumqzV) | [1.14.2+kotlin.2.4.21](https://modrinth.com/mod/fabric-language-kotlin/version/SKW1BTL3) |

Fabric API's `0.155.3+26.1.2` release declares support for all three 26.1-family targets. Use the row matching your actual Minecraft version. The [pinned dependency manifest](versions.json) records the exact verified files used to compile and launch each port.

## Build

Install Python **3.10+** and a **JDK 25+** providing `java`, `javac`, and `javap` on `PATH`. From the repository root:

```powershell
python ports/build.py --all
```

For one target:

```powershell
python ports/build.py --minecraft 26.2
```

The script uses Python's standard library and the JDK directly. Minecraft 26.x is unobfuscated, so this build uses official names and does not perform an intermediary/Yarn remapping step. Every dependency is downloaded from its pinned official Minecraft/Fabric or Modrinth URL and checked against the pinned hash.

Outputs:

```text
build/ports/26.1/Spear Dashing V1.1 MC26.1.jar
build/ports/26.1.1/Spear Dashing V1.1 MC26.1.1.jar
build/ports/26.1.2/Spear Dashing V1.1 MC26.1.2.jar
build/ports/26.2/Spear Dashing V1.1 MC26.2.jar
build/ports/26.3/Spear Dashing V1.1 MC26.3.jar
```

Each output directory also contains `SHA256SUMS` and a `build-report.json`. Compilation caches and binaries stay under the ignored `build/` directory. The original 1.21.11 build remains available through the root `build.ps1`.

## API differences handled

- The common source translates the recovered 1.21.11 intermediary symbols to official names.
- Fabric's key-binding API is replaced with `KeyMappingHelper.registerKeyMapping`.
- The 26.1 family reads `Minecraft.screen`.
- The 26.2 and 26.3 adapters read `Minecraft.gui.screen()`.
- The 26.3 adapter uses `InputConstants.Type.KEYBOARD`; older targets use `KEYSYM`.
- The invoker targets the verified private `Minecraft.startAttack()Z` method with remapping disabled.
- Mixin compatibility and class-file output use Java 25.

## Validation

See [VALIDATION.json](VALIDATION.json) for artifact hashes and the per-target validation record. Each target is compiled against its own game JAR. The build also verifies the invoker's target signature, Java class version, absence of legacy intermediary class references, JAR integrity, exact game-version metadata, and included licensing notices.

Isolated Fabric clients with the listed dependencies were used to verify title-screen startup, Fzzy Config registration, dash key binding registration and the transformed attack-invoker interface. **In-world dashing, multiplayer behavior, and compatibility with unrelated mods have not been tested.**

To check gameplay, create a test world, put a Lunge-enchanted spear in the hotbar, try tapping and holding **R**, test intervals 1–5, open and close the inventory, and leave/rejoin the world. Confirm the selected hotbar slot is restored after releasing the key.

## Release names

Each port is published under a tag such as `v1.1-mc26.3`. The game version is part of the release tag and JAR filename; the mod's own version remains **1.1**. GitHub normalizes spaces in stored asset filenames to dots, while the display label uses spaces.

The original licensing scope and upstream CC0 notices are preserved. See the root [LICENSE](../LICENSE) and [THIRD_PARTY_NOTICES.md](../THIRD_PARTY_NOTICES.md).

Porting reference: [Fabric for Minecraft 26.1](https://www.fabricmc.net/2026/03/14/261.html).
