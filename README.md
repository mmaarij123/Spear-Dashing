<div align="center">

<img src="src/main/resources/assets/speardash/icon.png" alt="Spear Dashing icon" width="88" />

# Spear Dashing

**One key. A quick swap. Your next dash.**

A client-side Fabric mod for automatic spear swapping and dash controls.

![Version](https://img.shields.io/badge/release-V1.1-7c3aed?style=flat-square)
![Minecraft](https://img.shields.io/badge/Minecraft-1.21.11%20%7C%2026.x-62a04d?style=flat-square)
![Loader](https://img.shields.io/badge/loader-Fabric-dbb790?style=flat-square)
![Java](https://img.shields.io/badge/Java-21%2B-e89539?style=flat-square)
![License](https://img.shields.io/badge/license-Proprietary%20%2B%20CC0%20upstream-64748b?style=flat-square)

[Choose a download](#supported-minecraft-versions) · [All releases](https://github.com/mmaarij123/Spear-Dashing/releases) · [Getting started](#getting-started) · [Settings](#settings)

</div>

---

## Supported Minecraft versions

**Mod version stays V1.1.** Each Minecraft 26.x target has its own compiled Fabric JAR. Choose the release matching your game version and install one Spear Dashing JAR per profile.

| Minecraft | Fabric release | Required Fzzy Config |
| :--- | :--- | :--- |
| **26.3** | [Spear Dashing V1.1 — MC26.3](https://github.com/mmaarij123/Spear-Dashing/releases/tag/v1.1-mc26.3) | [0.7.7+fix3+26.3](https://modrinth.com/mod/fzzy-config/version/YkOumqzV) |
| **26.2** | [Spear Dashing V1.1 — MC26.2](https://github.com/mmaarij123/Spear-Dashing/releases/tag/v1.1-mc26.2) | [0.7.7+26.2](https://modrinth.com/mod/fzzy-config/version/tZ4vc79R) |
| **26.1.2** | [Spear Dashing V1.1 — MC26.1.2](https://github.com/mmaarij123/Spear-Dashing/releases/tag/v1.1-mc26.1.2) | [0.7.7+26.1](https://modrinth.com/mod/fzzy-config/version/Ugxie86a) |
| **26.1.1** | [Spear Dashing V1.1 — MC26.1.1](https://github.com/mmaarij123/Spear-Dashing/releases/tag/v1.1-mc26.1.1) | [0.7.7+26.1](https://modrinth.com/mod/fzzy-config/version/Ugxie86a) |
| **26.1** | [Spear Dashing V1.1 — MC26.1](https://github.com/mmaarij123/Spear-Dashing/releases/tag/v1.1-mc26.1) | [0.7.7+26.1](https://modrinth.com/mod/fzzy-config/version/Ugxie86a) |
| **1.21.11** | [Original Spear Dashing V1.1](https://github.com/mmaarij123/Spear-Dashing/releases/tag/v1.1) | [0.7.7+1.21.11](https://modrinth.com/mod/fzzy-config/version/5SSZmpdx) |

**For Minecraft 26.x, you must install Java 25+, Fabric Loader 0.19.5+, Fabric API, Fzzy Config, and Fabric Language Kotlin.** Fzzy Config is mandatory; Spear Dashing will not load without it. Use the [version-specific dependency downloads](ports/README.md#required-downloads) for your target.

## A smoother way to dash

Keep a **Lunge-enchanted spear in your hotbar**, press **R**, and let Spear Dashing handle the swap. Hold the key to repeat when the configured interval and attack cooldown allow. Your original hotbar slot is restored between swaps.

### What's inside

- **Tap or hold** — detects brief presses and samples held input every client tick.
- **Adjustable timing** — choose a repeat and swap-back interval from **1–5 ticks**.
- **Slot restoration** — completes the previous restoration before beginning another swap.
- **Optional effects** — turn sound and particle effects on or off.
- **Menu awareness** — ignores new dash input while a screen is open and clears pending actions when the player or world is unavailable.

> The interval controls retry timing. Minecraft's attack cooldown and server mechanics still determine when a dash succeeds; a one-tick interval does not guarantee a dash every tick.

## Getting started

### Requirements

The table below describes the original **Minecraft 1.21.11** build. For **26.x**, use the [port requirements and downloads](ports/README.md#required-downloads).

| Component | Target |
| :--- | :--- |
| Minecraft | **1.21.11** |
| Mod loader | **Fabric** — the supplied build script expects the **0.18.6** runtime cache |
| Java | **21 or newer** |
| Dependencies | **Fabric API** and **Fzzy Config** for Minecraft 1.21.11 — see the required download below |
| Environment | **Client** |

### Must download: Fzzy Config

> **Fzzy Config is REQUIRED. Spear Dashing will not load or work without it.**

The direct download below is for **1.21.11**. For **26.x**, select the Fzzy Config version in the support table above.

Fzzy Config provides the configuration system used by Spear Dashing's settings. Download **Fzzy Config 0.7.7 for Minecraft 1.21.11 / Fabric** and place its JAR in the **same `mods` folder** as Spear Dashing and Fabric API.

**[Download Fzzy Config — MUST DOWNLOAD](https://cdn.modrinth.com/data/hYykXjDp/versions/5SSZmpdx/fzzy_config-0.7.7%2B1.21.11.jar?mr_download_reason=standalone&mr_game_version=1.21.11&mr_loader=fabric)**

File: `fzzy_config-0.7.7+1.21.11.jar` · [View this version on Modrinth](https://modrinth.com/mod/fzzy-config/version/5SSZmpdx)

### Install

1. Close Minecraft.
2. Download the JAR for **your exact Minecraft version** from the [release table](#supported-minecraft-versions).
3. **Install the matching Fzzy Config and Fabric API JARs.** Minecraft 26.x also requires Fabric Language Kotlin. Place the mod and dependencies in the same profile's `mods` folder. Spear Dashing cannot work if Fzzy Config is missing.
4. Remove older Spear Dashing JARs so only one version remains.
5. Launch the profile.

### Your first dash

1. Put a **Lunge-enchanted spear** in a hotbar slot.
2. Press **R**, the default **Spear Dashing** key binding. Rebind it in Minecraft's Controls settings if needed.
3. Hold the key for repeated attempts, or tap for a single input.
4. Start with the default **2-tick** interval; choose **1 tick** for the fastest configured retry.

## Settings

| Setting | Default | Purpose |
| :--- | :---: | :--- |
| `dashing` | `true` | Enables automatic spear swapping and dashing. |
| `swapDelayTicks` | `2` | Sets the swap-back and minimum repeat interval; clamped to **1–5**. |
| `soundEffects` | `true` | Enables sound effects. |
| `particleEffects` | `true` | Enables particle effects. |
| `omnidirectionalDashing` | `false` | Requests movement-key direction handling when the applicable advanced-feature code path is available. |
| `rangeExtensions` | `true` | Requests spear swapping for out-of-range hits when the applicable advanced-feature code path is available. |

**Advanced feature status:** the recovered source initializes `advancedFeaturesEnabled` to `false` and contains no assignment enabling it. Omnidirectional and range settings should therefore not be treated as fully enabled features in this build; some fallback handling remains in the original logic.

## What's new in 1.1.0

The update focuses on input responsiveness and consistent swap timing:

- Replaces reliance on keyboard-repeat events with tick-based held-key sampling.
- Drains queued presses so short taps are also detected.
- Replaces the fixed eight-tick input lockout with the configured interval.
- Allows a one-tick interval to retry on the next tick when the cooldown permits.
- Preserves the original selected slot during continuous use.
- Restricts hotbar searches to valid slots **0–8**.

Direction-changing dashes retain the original one-tick rotation setup. See the [recovery and validation notes](docs/RECOVERY.md) for the original change record.

## Build from source

### Minecraft 26.x — Java 25

The modern port uses the game's official, unobfuscated class names, common source, and small version-specific adapters. With Python 3.10+ and JDK 25+ on `PATH`, run:

```powershell
python ports/build.py --all
```

To build one target:

```powershell
python ports/build.py --minecraft 26.3
```

Outputs are under `build/ports/<minecraft-version>/`. Dependencies and game manifests are pinned and checksum-verified. See the [26.x build guide and validation notes](ports/README.md).

### Original Minecraft 1.21.11 — Java 21

This is **recovered and modified source from the supplied Spear Dashing 1.0 JAR**. It retains Fabric intermediary names such as `class_310` and uses the installed profile's dependencies. It is not a reconstructed Gradle/Loom project.

#### Windows / PowerShell

Ensure a **JDK 21+** is on `PATH`. Launch the target Modrinth profile once so its Minecraft, Fabric and library caches exist, then close the game before installing the resulting JAR.

From the repository root:

```powershell
./build.ps1
```

The default profile is **Remarkably Optimized** under `%APPDATA%/ModrinthApp`. To use another profile:

```powershell
./build.ps1 -ModrinthRoot 'D:/Games/ModrinthApp' -ProfileName 'My Fabric Profile'
```

For source compilation, the current `build.ps1` still expects the original cached Fzzy Config **0.7.6** filename below. The **0.7.7** download above is the supplied installation link. The selected build profile must have these exact runtime-cache and dependency paths:

```text
.fabric/remappedJars/minecraft-1.21.11-0.18.6/client-intermediary.jar
mods/fzzy_config-0.7.6+1.21.11.jar
```

Build output:

```text
build/Spear Dashing V1.1.jar
```

## Releases

Installable JARs are available under **[GitHub Releases](https://github.com/mmaarij123/Spear-Dashing/releases)**. Use the [support table](#supported-minecraft-versions) to select the matching JAR. The source ZIP and source tarball are for development.

The source tree contains the code, resources, build script and documentation. Compiled binaries are distributed separately as release assets. See [release details](docs/RELEASES.md) for the filename, checksum and build notes.

## Project map

```text
Spear-Dashing/
├── src/main/java/speardash/
│   ├── SpearDash.java                # Mod entry point
│   ├── SpearDashClient.java          # Input, swap and dash logic
│   ├── SpearDashConfig.java          # Settings and defaults
│   ├── SpearDashConfigApi.java       # Config dialog support
│   ├── KeyBinding.java              # Default R binding
│   └── mixin/client/ForceAttack.java
├── src/main/resources/              # Metadata, mixins, icon and language files
├── docs/RECOVERY.md                  # Original recovery and validation record
├── docs/RELEASES.md                  # Release download and artifact checksum
├── ports/src/main/java/             # Common Minecraft 26.x source
├── ports/compat/                    # Version-specific API adapters
├── ports/versions.json              # Pinned manifests and dependencies
├── ports/build.py                   # Java 25 build matrix
├── build.ps1                        # Local-cache build script
└── LICENSE                          # Reserved rights; earlier CC0 rights preserved
```

## Validation notes

The **26.x** variants were compiled separately against all five target game JARs. Isolated Fabric clients verified title-screen startup, Fzzy Config registration, key binding registration, and application of the attack-invoker mixin. See the [port validation record](ports/VALIDATION.json). Actual in-world dash timing, multiplayer behavior, and compatibility with other mods have not been tested.

The original recovery record reports successful compilation and packaged bytecode checks. In-game timing, server acceptance and compatibility with other mods have **not** been verified. The release JAR uses the existing optimized compiled classes, repackaged with updated licensing metadata and notices, and renamed for distribution. Its class files are unchanged; this is not a new compilation. The [release details](docs/RELEASES.md) record its SHA-256 checksum.

For a quick in-game check, try a tap, a held press, release/repress, intervals 1–5, opening the inventory, and leaving/rejoining a world. Verify that the original selected slot returns after releasing the key.

## Credits & license

**Maarij** is credited in the mod metadata. Original contributions that have not already been licensed with continuing permissions are **All Rights Reserved** under the [proprietary license](LICENSE). Personal gameplay with the unmodified published JAR is permitted; reuse of covered source code requires prior written permission.

**Earlier CC0 rights remain intact.** The recovered upstream source and material already made available under CC0 retain their original permissions. This notice cannot make the entire recovered codebase exclusive or prevent GitHub's permitted public forks. See [licensing scope and third-party notices](THIRD_PARTY_NOTICES.md) for the distinction.

---

<div align="center">
<sub>Spear Dashing · Fabric · V1.1</sub>
</div>
