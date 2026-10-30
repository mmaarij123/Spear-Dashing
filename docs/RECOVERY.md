# Spear Dashing 1.1.0

Recovered and modified source for the supplied Spear Dashing 1.0 JAR.
Target: Minecraft 1.21.11, Fabric, Java 21+, Fzzy Config 0.7.6.

## Changes

- Sample the held dash key on every client tick instead of waiting for keyboard repeat events. Drain queued press events once per tick so brief taps are also detected, without executing a held-key while loop.
- Replace the fixed 8-tick input lockout with the configured interval. Use zero-inclusive timer checks so a setting of 1 can retry on the very next tick when the attack cooldown permits.
- Finish the previous slot restoration before starting another swap, preserving the original hotbar slot during continuous use.
- Restrict the settings control to 1–5 ticks and clamp loaded values into that range at runtime. The existing default remains 2; choose 1 for the fastest retry interval.
- Ignore new dash input while a screen is open; reset pending actions when the player/world/interaction manager is absent.
- Limit hotbar searches to valid slots 0–8.

The original attack cooldown gate and game/server mechanics still apply. A one-tick retry interval does not promise a successful lunge every tick. Direction-changing dashes retain their existing one-tick rotation setup.

## Install and try

Close Minecraft, replace the old Spear Dashing JAR in the profile's mods folder with `Spear-Dashing-1.1.0-optimized.jar`, then restart. Keep only one version of this mod in the mods folder. The installed original was not replaced by this task.

Set the interval to 1. With a Lunge-enchanted spear in the hotbar, hold the dash key and compare the first repeat with later repeats. Also try a short tap, release/repress, intervals 2–5, opening the inventory, and leaving/rejoining the world. Check the original selected slot returns after releasing the key.

## Build and validation

Run `./build.ps1` in PowerShell with a JDK on PATH. The build uses the existing profile's Minecraft, Fabric API, Fzzy Config and library caches; optional parameters select another Modrinth root or profile. It compiles to Java 21. This recovered project retains the original JAR's Fabric intermediary symbols to compile against the exact installed runtime; it is not a reconstructed Loom/Yarn project.

Compilation and packaged bytecode checks were performed. In-game timing, visual feel, server acceptance and mod compatibility have not been tested.

Input semantics reference: https://maven.fabricmc.net/docs/yarn-1.21.11%2Bbuild.6/net/minecraft/client/option/KeyBinding.html
