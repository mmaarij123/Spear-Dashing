/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper
 *  net.minecraft.class_304
 *  net.minecraft.class_304$class_11900
 *  net.minecraft.class_3675$class_307
 */
package speardash;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.class_304;
import net.minecraft.class_3675;

@Environment(value=EnvType.CLIENT)
public class KeyBinding {
    public static final class_304 SPEAR_ROLL = KeyBindingHelper.registerKeyBinding((class_304)new class_304("key.speardash.roll", class_3675.class_307.field_1668, 82, class_304.class_11900.field_62556));

    public static class_304 getSpearRoll() {
        return SPEAR_ROLL;
    }
}

