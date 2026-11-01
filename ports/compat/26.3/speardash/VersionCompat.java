package speardash;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.Minecraft;

/** Minecraft 26.3 also renamed the keyboard input type. */
final class VersionCompat {
    private VersionCompat() {}

    static boolean isScreenOpen(Minecraft client) {
        return client.gui.screen() != null;
    }

    static InputConstants.Type keyboardInputType() {
        return InputConstants.Type.KEYBOARD;
    }
}
