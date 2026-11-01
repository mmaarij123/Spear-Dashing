package speardash;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.Minecraft;

/** Minecraft 26.1, 26.1.1 and 26.1.2 API differences. */
final class VersionCompat {
    private VersionCompat() {}

    static boolean isScreenOpen(Minecraft client) {
        return client.screen != null;
    }

    static InputConstants.Type keyboardInputType() {
        return InputConstants.Type.KEYSYM;
    }
}
