# Spear Dashing V1.1

Download the compiled mod from **[GitHub Releases](https://github.com/mmaarij123/Spear-Dashing/releases/tag/v1.1)**.

| Detail | Value |
| :--- | :--- |
| Release | **Spear Dashing V1.1** |
| Tag | `v1.1` |
| JAR display name | `Spear Dashing V1.1.jar` |
| Asset download | [Download the JAR](https://github.com/mmaarij123/Spear-Dashing/releases/download/v1.1/Spear.Dashing.V1.1.jar) |
| Minecraft | 1.21.11 / Fabric |
| Requirements | Java 21+, Fabric API, Fzzy Config 0.7.6 |

GitHub normalizes spaces in stored release asset filenames to dots, so the download URL uses `Spear.Dashing.V1.1.jar`. The asset's display label and the local build filename are **Spear Dashing V1.1.jar**. Renaming a downloaded JAR does not change its contents.

## Install

Close Minecraft, place the JAR in the profile's `mods` folder, keep only one Spear Dashing version installed, and restart. Follow the [README](../README.md#getting-started) for controls and settings.

## Verify the download

The release JAR's SHA-256 is:

```text
a997aec538a5dffd3f21473ec589051d035ee11791b708b700f5f789b32033e4
```

In PowerShell, use the actual downloaded filename:

```powershell
Get-FileHash -Algorithm SHA256 -LiteralPath './Spear.Dashing.V1.1.jar'
```

The compiled classes and licensing notices are the same as in the previously published artifact; only the distribution filename changes in this release. The internal mod version remains `1.1.0`.

## Build locally

Run `./build.ps1` with the profile and JDK described in the [build instructions](../README.md#build-from-source). It outputs `build/Spear Dashing V1.1.jar`. A fresh compilation may produce a different JAR checksum.

Source archives contain source code and resources. Installable JARs are attached to Releases separately.
