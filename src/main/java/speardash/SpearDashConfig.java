/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  me.fzzyhmstrs.fzzy_config.annotations.Comment
 *  me.fzzyhmstrs.fzzy_config.config.Config
 *  me.fzzyhmstrs.fzzy_config.validation.number.ValidatedInt$Restrict
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_2960
 */
package speardash;

import me.fzzyhmstrs.fzzy_config.annotations.Comment;
import me.fzzyhmstrs.fzzy_config.config.Config;
import me.fzzyhmstrs.fzzy_config.validation.number.ValidatedInt;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_2960;

@Environment(value=EnvType.CLIENT)
public class SpearDashConfig
extends Config {
    @Comment(value="Press the configured dash key with a Lunge-enchanted spear in your inventory to perform an automatic spear swap and dash.")
    public boolean dashing = true;
    @Comment(value="Allows dash direction to follow the movement keys currently being held when advanced features are available.")
    public boolean omnidirectionalDashing = false;
    public boolean soundEffects = true;
    public boolean particleEffects = true;
    @Comment(value="Automatically swaps to an applicable spear for an out-of-range hit when advanced features are available.")
    public boolean rangeExtensions = true;
    @Comment(value="Swap-back and minimum repeat interval in ticks (1-5). Holding the dash key repeats as soon as the interval and attack cooldown allow.")
    @ValidatedInt.Restrict(min=1, max=5)
    public int swapDelayTicks = 2;

    public SpearDashConfig() {
        super(class_2960.method_60655((String)"speardash", (String)"settings"));
    }
}

