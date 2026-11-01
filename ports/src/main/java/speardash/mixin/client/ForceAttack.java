package speardash.mixin.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Environment(value=EnvType.CLIENT)
@Mixin(value={Minecraft.class}, remap=false)
public interface ForceAttack {
    @Invoker(value="startAttack", remap=false)
    public boolean callDoAttack();
}
