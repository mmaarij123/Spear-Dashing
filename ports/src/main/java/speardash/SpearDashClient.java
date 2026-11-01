package speardash;

import java.util.Random;
import me.fzzyhmstrs.fzzy_config.api.ConfigApiJava;
import me.fzzyhmstrs.fzzy_config.api.RegisterType;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.client.Minecraft;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.Holder;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.registries.Registries;
import speardash.KeyBinding;
import speardash.SpearDashConfig;
import speardash.mixin.client.ForceAttack;

@Environment(value=EnvType.CLIENT)
public class SpearDashClient
implements ClientModInitializer {
    int bindDelay = -1;
    int hitWithDelay = -1;
    int glideWithDelay = -1;
    int swapBacktoElytraDelay = -1;
    boolean cooldownHopper = false;
    int slotMem = 0;
    float yawMem = 0.0f;
    int swapBackDelay = -1;
    boolean pressJump = false;
    int spearswapDelay = -1;
    boolean elytraClause = false;
    boolean forwardsOverride = false;
    int hitDelay = -1;
    boolean chargedUp = false;
    boolean rangeState = true;
    int dashWithDelay = -1;
    int rotMemory = 0;
    static boolean effectState = true;
    boolean advancedFeaturesEnabled = false;
    public boolean initialized = false;
    public static SpearDashConfig config = (SpearDashConfig)ConfigApiJava.registerAndLoadConfig(SpearDashConfig::new, (RegisterType)RegisterType.CLIENT);

    public void simulateHit() {
        Minecraft client = Minecraft.getInstance();
        if (client instanceof ForceAttack) {
            ForceAttack accessor = (ForceAttack)client;
            boolean bl = accessor.callDoAttack();
        }
    }

    public static void playSound(SoundEvent sound, Minecraft client) {
        if (SpearDashClient.config.soundEffects) {
            client.getSoundManager().play((SoundInstance)SimpleSoundInstance.forUI((SoundEvent)sound, (float)0.0f, (float)111.0f));
        }
    }

    public static void playSoundFast(SoundEvent sound, Minecraft client) {
        if (SpearDashClient.config.soundEffects) {
            client.getSoundManager().play((SoundInstance)SimpleSoundInstance.forUI((SoundEvent)sound, (float)2.0f, (float)0.1f));
        }
    }

    public static void particlesMaker(Entity e, Minecraft client, ParticleOptions part) {
        if (SpearDashClient.config.particleEffects) {
            float x_ = (new Random().nextFloat() * 2.0f - 1.0f) * 0.7f;
            float z_ = (new Random().nextFloat() * 2.0f - 1.0f) * 0.7f;
            client.level.addParticle((ParticleOptions)ParticleTypes.POOF, e.getX(), e.getY() + 1.0, e.getZ(), (double)x_, 0.05, (double)z_);
            for (int i = 0; i < 100; ++i) {
                float c_ = (new Random().nextFloat() * 2.0f - 1.0f) * 0.7f;
                float r_ = (new Random().nextFloat() * 2.0f - 1.0f) * 0.7f;
                client.level.addParticle(part, e.getX(), e.getY(), e.getZ(), (double)c_, 0.05, (double)r_);
            }
        }
    }

    public boolean swapSlot(Player player, String itemname) {
        int swordswapcounter = 0;
        for (ItemStack itemStack : player.getInventory()) {
            if (String.valueOf(itemStack.getItem()).contains(itemname)) {
                Minecraft.getInstance().player.getInventory().setSelectedSlot(swordswapcounter);
                return true;
            }
            if (++swordswapcounter <= 8) continue;
            break;
        }
        return false;
    }

    public boolean swapSlotEnchSelect(Player player, String itemname, ResourceKey enchan) {
        int swordswapcounter = 0;
        for (ItemStack itemStack : player.getInventory()) {
            Holder.Reference WB;
            if (String.valueOf(itemStack.getItem()).contains(itemname) && EnchantmentHelper.getItemEnchantmentLevel((Holder)(WB = player.level().registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(enchan)), (ItemStack)itemStack) > 0) {
                Minecraft.getInstance().player.getInventory().setSelectedSlot(swordswapcounter);
                return true;
            }
            if (++swordswapcounter <= 8) continue;
            break;
        }
        return false;
    }

    public boolean swapSlotNOTEnch(Player player, String itemname, ResourceKey enchan) {
        int swordswapcounter = 0;
        for (ItemStack itemStack : player.getInventory()) {
            Holder.Reference WB;
            if (String.valueOf(itemStack.getItem()).contains(itemname) && EnchantmentHelper.getItemEnchantmentLevel((Holder)(WB = player.level().registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(enchan)), (ItemStack)itemStack) <= 0) {
                Minecraft.getInstance().player.getInventory().setSelectedSlot(swordswapcounter);
                return true;
            }
            if (++swordswapcounter <= 8) continue;
            break;
        }
        return false;
    }

    public static void changePlayerRange(double newRange) {
        Minecraft client = Minecraft.getInstance();
        LocalPlayer player = client.player;
        if (player == null) {
            return;
        }
        AttributeInstance speed = player.getAttribute(Attributes.ENTITY_INTERACTION_RANGE);
        if (speed != null) {
            speed.setBaseValue(newRange);
        }
    }

    public void onInitializeClient() {
        new KeyBinding();
        ClientTickEvents.START_CLIENT_TICK.register(client -> {
            // Consume clicks once per tick, including taps shorter than one tick.
            // Held input must not depend on the operating system's repeat timer.
            boolean dashRequested = KeyBinding.getSpearRoll().isDown();
            while (KeyBinding.getSpearRoll().consumeClick()) {
                dashRequested = true;
            }
            config.swapDelayTicks = Math.max(1, Math.min(5, config.swapDelayTicks));
            if (client.player == null || client.level == null || client.gameMode == null) {
                this.bindDelay = this.swapBackDelay = this.hitWithDelay = -1;
                this.spearswapDelay = this.hitDelay = this.glideWithDelay = -1;
                this.swapBacktoElytraDelay = this.dashWithDelay = -1;
                this.cooldownHopper = this.pressJump = this.chargedUp = false;
                this.elytraClause = false;
                this.forwardsOverride = true;
                return;
            }
            if (VersionCompat.isScreenOpen(client)) {
                dashRequested = false;
            }
            if (client.level != null && !this.advancedFeaturesEnabled && !this.initialized) {
                this.initialized = true;
            }
            --this.bindDelay;
            --this.swapBackDelay;
            --this.hitWithDelay;
            --this.spearswapDelay;
            --this.hitDelay;
            --this.glideWithDelay;
            --this.swapBacktoElytraDelay;
            --this.dashWithDelay;
            if (this.spearswapDelay == 0) {
                this.swapBackDelay = 1;
            }
            if (this.swapBackDelay == 0) {
                client.player.getInventory().setSelectedSlot(this.slotMem);
                if (!this.forwardsOverride) {
                    client.player.setYRot(this.yawMem);
                    this.forwardsOverride = true;
                }
            }
            if (this.pressJump) {
                Minecraft.getInstance().options.keyJump.setDown(true);
                Minecraft.getInstance().options.keyJump.setDown(false);
                this.pressJump = false;
            }
            if (this.swapBacktoElytraDelay == 0) {
                this.swapBackDelay = SpearDashClient.config.swapDelayTicks;
                if (this.swapSlot((Player)client.player, "elytra")) {
                    client.gameMode.useItem((Player)client.player, InteractionHand.MAIN_HAND);
                    this.pressJump = true;
                }
            }
            if (this.dashWithDelay == 0) {
                this.dash(client, this.rotMemory);
            }
            if (this.hitDelay == 0) {
                this.simulateHit();
            }
            if (this.hitWithDelay == 0 && this.swapSlotEnchSelect((Player)client.player, "spear", Enchantments.LUNGE)) {
                this.simulateHit();
                if (this.elytraClause) {
                    this.swapBacktoElytraDelay = 1;
                    SpearDashClient.playSound(SoundEvents.FIREWORK_ROCKET_LARGE_BLAST, client);
                    SpearDashClient.playSound(SoundEvents.WARDEN_LISTENING_ANGRY, client);
                } else {
                    this.swapBackDelay = SpearDashClient.config.swapDelayTicks;
                    SpearDashClient.playSound(SoundEvents.ARMADILLO_ROLL, client);
                }
                SpearDashClient.particlesMaker((Entity)client.player, client, (ParticleOptions)ParticleTypes.POOF);
            }
            if (this.cooldownHopper && (double)client.player.getAttackStrengthScale(0.0f) == 1.0) {
                this.hitWithDelay = 1;
                this.cooldownHopper = false;
                this.elytraClause = true;
            }
            if (this.glideWithDelay == 0 && this.swapSlot((Player)client.player, "_chestplate")) {
                client.gameMode.useItem((Player)client.player, InteractionHand.MAIN_HAND);
                SpearDashClient.particlesMaker((Entity)client.player, client, (ParticleOptions)ParticleTypes.POOF);
                SpearDashClient.playSound(SoundEvents.ZOMBIE_NAUTILUS_DASH_ON_LAND, client);
                this.cooldownHopper = true;
            }
            if (SpearDashClient.config.dashing && SpearDashClient.config.omnidirectionalDashing && this.advancedFeaturesEnabled) {
                if (dashRequested && Minecraft.getInstance().options.keyLeft.isDown() && Minecraft.getInstance().options.keyUp.isDown() && this.bindDelay <= 0 && this.swapBackDelay <= 0 && this.dashWithDelay <= 0) {
                    this.slotMem = client.player.getInventory().getSelectedSlot();
                    this.yawMem = client.player.getYHeadRot();
                    this.rotMemory = -45;
                    client.player.setYRot(this.yawMem + (float)this.rotMemory);
                    this.dashWithDelay = 1;
                    this.bindDelay = config.swapDelayTicks;
                    return;
                }
                if (dashRequested && Minecraft.getInstance().options.keyRight.isDown() && Minecraft.getInstance().options.keyUp.isDown() && this.bindDelay <= 0 && this.swapBackDelay <= 0 && this.dashWithDelay <= 0) {
                    this.slotMem = client.player.getInventory().getSelectedSlot();
                    this.yawMem = client.player.getYHeadRot();
                    this.rotMemory = 45;
                    client.player.setYRot(this.yawMem + (float)this.rotMemory);
                    this.dashWithDelay = 1;
                    this.bindDelay = config.swapDelayTicks;
                    return;
                }
                if (dashRequested && Minecraft.getInstance().options.keyLeft.isDown() && Minecraft.getInstance().options.keyDown.isDown() && this.bindDelay <= 0 && this.swapBackDelay <= 0 && this.dashWithDelay <= 0) {
                    this.slotMem = client.player.getInventory().getSelectedSlot();
                    this.yawMem = client.player.getYHeadRot();
                    this.rotMemory = -135;
                    client.player.setYRot(this.yawMem + (float)this.rotMemory);
                    this.dashWithDelay = 1;
                    this.bindDelay = config.swapDelayTicks;
                    return;
                }
                if (dashRequested && Minecraft.getInstance().options.keyRight.isDown() && Minecraft.getInstance().options.keyDown.isDown() && this.bindDelay <= 0 && this.swapBackDelay <= 0 && this.dashWithDelay <= 0) {
                    this.slotMem = client.player.getInventory().getSelectedSlot();
                    this.yawMem = client.player.getYHeadRot();
                    this.rotMemory = 135;
                    client.player.setYRot(this.yawMem + (float)this.rotMemory);
                    this.dashWithDelay = 1;
                    this.bindDelay = config.swapDelayTicks;
                    return;
                }
            }
            if (Minecraft.getInstance().player != null) {
                if (this.chargedUp) {
                    if (client.crosshairPickEntity != null) {
                        if ((double)client.player.distanceTo(client.crosshairPickEntity) >= 3.2 && (double)client.player.getAttackStrengthScale(0.0f) == 1.0) {
                            this.hitDelay = 1;
                            this.chargedUp = false;
                        }
                    } else {
                        this.chargedUp = false;
                    }
                }
                int swordswapcounter = 0;
                boolean foundSpear = false;
                for (ItemStack itemStack : client.player.getInventory()) {
                    Holder.Reference WB;
                    if (String.valueOf(itemStack.getItem()).contains("spear") && EnchantmentHelper.getItemEnchantmentLevel((Holder)(WB = client.player.level().registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.LUNGE)), (ItemStack)itemStack) <= 0) {
                        SpearDashClient.changePlayerRange(4.6);
                        foundSpear = true;
                    }
                    if (++swordswapcounter <= 8) continue;
                    break;
                }
                if (!foundSpear) {
                    SpearDashClient.changePlayerRange(3.0);
                }
                if (SpearDashClient.config.dashing) {
                    if (SpearDashClient.config.omnidirectionalDashing) {
                        if (dashRequested && Minecraft.getInstance().options.keyUp.isDown() && this.bindDelay <= 0 && this.swapBackDelay <= 0 && this.dashWithDelay <= 0) {
                            this.yawMem = client.player.getYHeadRot();
                            this.dash(client, 0);
                        }
                        if (dashRequested && Minecraft.getInstance().options.keyLeft.isDown() && this.bindDelay <= 0 && this.swapBackDelay <= 0 && this.dashWithDelay <= 0) {
                            this.slotMem = client.player.getInventory().getSelectedSlot();
                            this.yawMem = client.player.getYHeadRot();
                            this.rotMemory = -90;
                            client.player.setYRot(this.yawMem + (float)this.rotMemory);
                            this.dashWithDelay = 1;
                            this.bindDelay = config.swapDelayTicks;
                            return;
                        }
                        if (dashRequested && Minecraft.getInstance().options.keyRight.isDown() && this.bindDelay <= 0 && this.swapBackDelay <= 0 && this.dashWithDelay <= 0) {
                            this.slotMem = client.player.getInventory().getSelectedSlot();
                            this.yawMem = client.player.getYHeadRot();
                            this.rotMemory = 90;
                            client.player.setYRot(this.yawMem + (float)this.rotMemory);
                            this.dashWithDelay = 1;
                            this.bindDelay = config.swapDelayTicks;
                            return;
                        }
                        if (dashRequested && Minecraft.getInstance().options.keyDown.isDown() && this.bindDelay <= 0 && this.swapBackDelay <= 0 && this.dashWithDelay <= 0) {
                            this.slotMem = client.player.getInventory().getSelectedSlot();
                            this.yawMem = client.player.getYHeadRot();
                            this.rotMemory = -180;
                            client.player.setYRot(this.yawMem + (float)this.rotMemory);
                            this.dashWithDelay = 1;
                            this.bindDelay = config.swapDelayTicks;
                            return;
                        }
                    } else {
                        if (dashRequested && this.bindDelay <= 0 && this.swapBackDelay <= 0 && this.dashWithDelay <= 0) {
                            this.yawMem = client.player.getYHeadRot();
                            this.dash(client, 0);
                        }
                    }
                }
            }
        });
        AttackEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
            Minecraft client = Minecraft.getInstance();
            if (entity == null || client.crosshairPickEntity == null) {
                return InteractionResult.PASS;
            }
            if ((double)player.distanceTo(entity) >= 3.2 && SpearDashClient.config.rangeExtensions) {
                if (this.advancedFeaturesEnabled) {
                    this.slotMem = player.getInventory().getSelectedSlot();
                    if ((double)client.player.getAttackStrengthScale(0.0f) >= 1.0 && this.swapSlotNOTEnch(player, "spear", Enchantments.LUNGE)) {
                        SpearDashClient.playSoundFast(SoundEvents.NAUTILUS_DASH, client);
                        SpearDashClient.particlesMaker(entity, client, (ParticleOptions)ParticleTypes.WITCH);
                        this.yawMem = player.getYRot();
                        this.swapBackDelay = SpearDashClient.config.swapDelayTicks;
                        if (client instanceof ForceAttack) {
                            ForceAttack accessor = (ForceAttack)client;
                            boolean bl = accessor.callDoAttack();
                        }
                    }
                    return InteractionResult.FAIL;
                }
                return InteractionResult.PASS;
            }
            return InteractionResult.PASS;
        });
    }

    private void dash(Minecraft client, int rotDegrees) {
        if ((double)client.player.getAttackStrengthScale(0.0f) >= 1.0) {
            this.rotMemory = 0;
            if (!client.player.isFallFlying()) {
                this.slotMem = client.player.getInventory().getSelectedSlot();
                if (this.swapSlotEnchSelect((Player)client.player, "spear", Enchantments.LUNGE)) {
                    this.simulateHit();
                    this.forwardsOverride = true;
                    this.swapBackDelay = SpearDashClient.config.swapDelayTicks;
                    SpearDashClient.particlesMaker((Entity)client.player, client, (ParticleOptions)ParticleTypes.POOF);
                    SpearDashClient.playSound(SoundEvents.ARMADILLO_ROLL, client);
                    this.bindDelay = config.swapDelayTicks;
                }
            } else if (!client.player.getMainHandItem().getItemName().contains(Component.nullToEmpty((String)"chestplate"))) {
                this.slotMem = client.player.getInventory().getSelectedSlot();
                this.glideWithDelay = 1;
            }
        }
    }
}
