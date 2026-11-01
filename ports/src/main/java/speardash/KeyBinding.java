package speardash;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.minecraft.client.KeyMapping;
import com.mojang.blaze3d.platform.InputConstants;

@Environment(value=EnvType.CLIENT)
public class KeyBinding {
    public static final KeyMapping SPEAR_ROLL = KeyMappingHelper.registerKeyMapping((KeyMapping)new KeyMapping("key.speardash.roll", VersionCompat.keyboardInputType(), 82, KeyMapping.Category.MISC));

    public static KeyMapping getSpearRoll() {
        return SPEAR_ROLL;
    }
}
