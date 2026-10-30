<div align="center">

<img src="src/main/resources/assets/speardash/icon.png" alt="Spear Dashing icon" width="88" />

# Spear Dashing

**One key. A quick swap. Your next dash.**

A client-side Fabric mod for automatic spear swapping and dash controls.

![Version](https://img.shields.io/badge/release-V1.1-7c3aed?style=flat-square)
![Minecraft](https://img.shields.io/badge/Minecraft-1.21.11-62a04d?style=flat-square)
![Loader](https://img.shields.io/badge/loader-Fabric-dbb790?style=flat-square)
![Java](https://img.shields.io/badge/Java-21%2B-e89539?style=flat-square)
![License](https://img.shields.io/badge/license-Proprietary%20%2B%20CC0%20upstream-64748b?style=flat-square)

[Download V1.1](https://github.com/mmaarij123/Spear-Dashing/releases/download/v1.1/Spear.Dashing.V1.1.jar) · [Release notes](https://github.com/mmaarij123/Spear-Dashing/releases/tag/v1.1) · [Getting started](#getting-started) · [Settings](#settings)

</div>

---

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

| Component | Target |
| :--- | :--- |
| Minecraft | **1.21.11** |
| Mod loader | **Fabric** — the supplied build script expects the **0.18.6** runtime cache |
| Java | **21 or newer** |
| Dependencies | **Fabric API** and **Fzzy Config 0.7.6** for Minecraft 1.21.11 |
| Environment | **Client** |

### Install

1. Close Minecraft.
2. Download [`Spear Dashing V1.1.jar`](https://github.com/mmaarij123/Spear-Dashing/releases/download/v1.1/Spear.Dashing.V1.1.jar) from [Releases](https://github.com/mmaarij123/Spear-Dashing/releases/tag/v1.1).
3. Place it in your Fabric profile's `mods` folder alongside Fabric API and Fzzy Config.
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

This is **recovered and modified source from the supplied Spear Dashing 1.0 JAR**. It retains Fabric intermediary names such as `class_310` and uses the installed profile's dependencies. It is not a reconstructed Gradle/Loom project.

### Windows / PowerShell

Ensure a **JDK 21+** is on `PATH`. Launch the target Modrinth profile once so its Minecraft, Fabric and library caches exist, then close the game before installing the resulting JAR.

From the repository root:

```powershell
./build.ps1
```

The default profile is **Remarkably Optimized** under `%APPDATA%/ModrinthApp`. To use another profile:

```powershell
./build.ps1 -ModrinthRoot 'D:/Games/ModrinthApp' -ProfileName 'My Fabric Profile'
```

The selected profile must have the exact runtime cache and Fzzy Config JAR expected by `build.ps1`:

```text
.fabric/remappedJars/minecraft-1.21.11-0.18.6/client-intermediary.jar
mods/fzzy_config-0.7.6+1.21.11.jar
```

Build output:

```text
build/Spear Dashing V1.1.jar
```

## Releases

Installable JARs are available under **[GitHub Releases](https://github.com/mmaarij123/Spear-Dashing/releases)**. Download the **Spear Dashing V1.1.jar** asset for this version; the source ZIP and source tarball are for development.

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
├── build.ps1                        # Local-cache build script
└── LICENSE                          # Reserved rights; earlier CC0 rights preserved
```

## Validation notes

The original recovery record reports successful compilation and packaged bytecode checks. In-game timing, server acceptance and compatibility with other mods have **not** been verified. The release JAR uses the existing optimized compiled classes, repackaged with updated licensing metadata and notices, and renamed for distribution. Its class files are unchanged; this is not a new compilation. The [release details](docs/RELEASES.md) record its SHA-256 checksum.

For a quick in-game check, try a tap, a held press, release/repress, intervals 1–5, opening the inventory, and leaving/rejoining a world. Verify that the original selected slot returns after releasing the key.

## Credits & license

**Maarij** is credited in the mod metadata. Original contributions that have not already been licensed with continuing permissions are **All Rights Reserved** under the [proprietary license](LICENSE). Personal gameplay with the unmodified published JAR is permitted; reuse of covered source code requires prior written permission.

**Earlier CC0 rights remain intact.** The recovered upstream source and material already made available under CC0 retain their original permissions. This notice cannot make the entire recovered codebase exclusive or prevent GitHub's permitted public forks. See [licensing scope and third-party notices](THIRD_PARTY_NOTICES.md) for the distinction.

---

<div align="center">
<sub>Spear Dashing · Fabric · V1.1</sub>
</div>
