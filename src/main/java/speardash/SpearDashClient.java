/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  me.fzzyhmstrs.fzzy_config.api.ConfigApiJava
 *  me.fzzyhmstrs.fzzy_config.api.RegisterType
 *  net.fabricmc.api.ClientModInitializer
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
 *  net.fabricmc.fabric.api.event.player.AttackEntityCallback
 *  net.minecraft.class_1109
 *  net.minecraft.class_1113
 *  net.minecraft.class_1268
 *  net.minecraft.class_1269
 *  net.minecraft.class_1297
 *  net.minecraft.class_1324
 *  net.minecraft.class_1657
 *  net.minecraft.class_1799
 *  net.minecraft.class_1890
 *  net.minecraft.class_1893
 *  net.minecraft.class_2394
 *  net.minecraft.class_2398
 *  net.minecraft.class_2561
 *  net.minecraft.class_310
 *  net.minecraft.class_3414
 *  net.minecraft.class_3417
 *  net.minecraft.class_5134
 *  net.minecraft.class_5321
 *  net.minecraft.class_6880
 *  net.minecraft.class_6880$class_6883
 *  net.minecraft.class_746
 *  net.minecraft.class_7924
 */
package speardash;

import java.util.Random;
import me.fzzyhmstrs.fzzy_config.api.ConfigApiJava;
import me.fzzyhmstrs.fzzy_config.api.RegisterType;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.class_1109;
import net.minecraft.class_1113;
import net.minecraft.class_1268;
import net.minecraft.class_1269;
import net.minecraft.class_1297;
import net.minecraft.class_1324;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_1890;
import net.minecraft.class_1893;
import net.minecraft.class_2394;
import net.minecraft.class_2398;
import net.minecraft.class_2561;
import net.minecraft.class_310;
import net.minecraft.class_3414;
import net.minecraft.class_3417;
import net.minecraft.class_5134;
import net.minecraft.class_5321;
import net.minecraft.class_6880;
import net.minecraft.class_746;
import net.minecraft.class_7924;
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
        class_310 client = class_310.method_1551();
        if (client instanceof ForceAttack) {
            ForceAttack accessor = (ForceAttack)client;
            boolean bl = accessor.callDoAttack();
        }
    }

    public static void playSound(class_3414 sound, class_310 client) {
        if (SpearDashClient.config.soundEffects) {
            client.method_1483().method_4873((class_1113)class_1109.method_4757((class_3414)sound, (float)0.0f, (float)111.0f));
        }
    }

    public static void playSoundFast(class_3414 sound, class_310 client) {
        if (SpearDashClient.config.soundEffects) {
            client.method_1483().method_4873((class_1113)class_1109.method_4757((class_3414)sound, (float)2.0f, (float)0.1f));
        }
    }

    public static void particlesMaker(class_1297 e, class_310 client, class_2394 part) {
        if (SpearDashClient.config.particleEffects) {
            float x_ = (new Random().nextFloat() * 2.0f - 1.0f) * 0.7f;
            float z_ = (new Random().nextFloat() * 2.0f - 1.0f) * 0.7f;
            client.field_1687.method_8406((class_2394)class_2398.field_11203, e.method_23317(), e.method_23318() + 1.0, e.method_23321(), (double)x_, 0.05, (double)z_);
            for (int i = 0; i < 100; ++i) {
                float c_ = (new Random().nextFloat() * 2.0f - 1.0f) * 0.7f;
                float r_ = (new Random().nextFloat() * 2.0f - 1.0f) * 0.7f;
                client.field_1687.method_8406(part, e.method_23317(), e.method_23318(), e.method_23321(), (double)c_, 0.05, (double)r_);
            }
        }
    }

    public boolean swapSlot(class_1657 player, String itemname) {
        int swordswapcounter = 0;
        for (class_1799 itemStack : player.method_31548()) {
            if (String.valueOf(itemStack.method_7909()).contains(itemname)) {
                class_310.method_1551().field_1724.method_31548().method_61496(swordswapcounter);
                return true;
            }
            if (++swordswapcounter <= 8) continue;
            break;
        }
        return false;
    }

    public boolean swapSlotEnchSelect(class_1657 player, String itemname, class_5321 enchan) {
        int swordswapcounter = 0;
        for (class_1799 itemStack : player.method_31548()) {
            class_6880.class_6883 WB;
            if (String.valueOf(itemStack.method_7909()).contains(itemname) && class_1890.method_8225((class_6880)(WB = player.method_73183().method_30349().method_30530(class_7924.field_41265).method_46747(enchan)), (class_1799)itemStack) > 0) {
                class_310.method_1551().field_1724.method_31548().method_61496(swordswapcounter);
                return true;
            }
            if (++swordswapcounter <= 8) continue;
            break;
        }
        return false;
    }

    public boolean swapSlotNOTEnch(class_1657 player, String itemname, class_5321 enchan) {
        int swordswapcounter = 0;
        for (class_1799 itemStack : player.method_31548()) {
            class_6880.class_6883 WB;
            if (String.valueOf(itemStack.method_7909()).contains(itemname) && class_1890.method_8225((class_6880)(WB = player.method_73183().method_30349().method_30530(class_7924.field_41265).method_46747(enchan)), (class_1799)itemStack) <= 0) {
                class_310.method_1551().field_1724.method_31548().method_61496(swordswapcounter);
                return true;
            }
            if (++swordswapcounter <= 8) continue;
            break;
        }
        return false;
    }

    public static void changePlayerRange(double newRange) {
        class_310 client = class_310.method_1551();
        class_746 player = client.field_1724;
        if (player == null) {
            return;
        }
        class_1324 speed = player.method_5996(class_5134.field_47759);
        if (speed != null) {
            speed.method_6192(newRange);
        }
    }

    public void onInitializeClient() {
        new KeyBinding();
        ClientTickEvents.START_CLIENT_TICK.register(client -> {
            // Consume clicks once per tick, including taps shorter than one tick.
            // Held input must not depend on the operating system's repeat timer.
            boolean dashRequested = KeyBinding.getSpearRoll().method_1434();
            while (KeyBinding.getSpearRoll().method_1436()) {
                dashRequested = true;
            }
            config.swapDelayTicks = Math.max(1, Math.min(5, config.swapDelayTicks));
            if (client.field_1724 == null || client.field_1687 == null || client.field_1761 == null) {
                this.bindDelay = this.swapBackDelay = this.hitWithDelay = -1;
                this.spearswapDelay = this.hitDelay = this.glideWithDelay = -1;
                this.swapBacktoElytraDelay = this.dashWithDelay = -1;
                this.cooldownHopper = this.pressJump = this.chargedUp = false;
                this.elytraClause = false;
                this.forwardsOverride = true;
                return;
            }
            if (client.field_1755 != null) {
                dashRequested = false;
            }
            if (client.field_1687 != null && !this.advancedFeaturesEnabled && !this.initialized) {
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
                client.field_1724.method_31548().method_61496(this.slotMem);
                if (!this.forwardsOverride) {
                    client.field_1724.method_36456(this.yawMem);
                    this.forwardsOverride = true;
                }
            }
            if (this.pressJump) {
                class_310.method_1551().field_1690.field_1903.method_23481(true);
                class_310.method_1551().field_1690.field_1903.method_23481(false);
                this.pressJump = false;
            }
            if (this.swapBacktoElytraDelay == 0) {
                this.swapBackDelay = SpearDashClient.config.swapDelayTicks;
                if (this.swapSlot((class_1657)client.field_1724, "elytra")) {
                    client.field_1761.method_2919((class_1657)client.field_1724, class_1268.field_5808);
                    this.pressJump = true;
                }
            }
            if (this.dashWithDelay == 0) {
                this.dash(client, this.rotMemory);
            }
            if (this.hitDelay == 0) {
                this.simulateHit();
            }
            if (this.hitWithDelay == 0 && this.swapSlotEnchSelect((class_1657)client.field_1724, "spear", class_1893.field_63420)) {
                this.simulateHit();
                if (this.elytraClause) {
                    this.swapBacktoElytraDelay = 1;
                    SpearDashClient.playSound(class_3417.field_15188, client);
                    SpearDashClient.playSound(class_3417.field_38071, client);
                } else {
                    this.swapBackDelay = SpearDashClient.config.swapDelayTicks;
                    SpearDashClient.playSound(class_3417.field_47716, client);
                }
                SpearDashClient.particlesMaker((class_1297)client.field_1724, client, (class_2394)class_2398.field_11203);
            }
            if (this.cooldownHopper && (double)client.field_1724.method_7261(0.0f) == 1.0) {
                this.hitWithDelay = 1;
                this.cooldownHopper = false;
                this.elytraClause = true;
            }
            if (this.glideWithDelay == 0 && this.swapSlot((class_1657)client.field_1724, "_chestplate")) {
                client.field_1761.method_2919((class_1657)client.field_1724, class_1268.field_5808);
                SpearDashClient.particlesMaker((class_1297)client.field_1724, client, (class_2394)class_2398.field_11203);
                SpearDashClient.playSound(class_3417.field_64605, client);
                this.cooldownHopper = true;
            }
            if (SpearDashClient.config.dashing && SpearDashClient.config.omnidirectionalDashing && this.advancedFeaturesEnabled) {
                if (dashRequested && class_310.method_1551().field_1690.field_1913.method_1434() && class_310.method_1551().field_1690.field_1894.method_1434() && this.bindDelay <= 0 && this.swapBackDelay <= 0 && this.dashWithDelay <= 0) {
                    this.slotMem = client.field_1724.method_31548().method_67532();
                    this.yawMem = client.field_1724.method_5791();
                    this.rotMemory = -45;
                    client.field_1724.method_36456(this.yawMem + (float)this.rotMemory);
                    this.dashWithDelay = 1;
                    this.bindDelay = config.swapDelayTicks;
                    return;
                }
                if (dashRequested && class_310.method_1551().field_1690.field_1849.method_1434() && class_310.method_1551().field_1690.field_1894.method_1434() && this.bindDelay <= 0 && this.swapBackDelay <= 0 && this.dashWithDelay <= 0) {
                    this.slotMem = client.field_1724.method_31548().method_67532();
                    this.yawMem = client.field_1724.method_5791();
                    this.rotMemory = 45;
                    client.field_1724.method_36456(this.yawMem + (float)this.rotMemory);
                    this.dashWithDelay = 1;
                    this.bindDelay = config.swapDelayTicks;
                    return;
                }
                if (dashRequested && class_310.method_1551().field_1690.field_1913.method_1434() && class_310.method_1551().field_1690.field_1881.method_1434() && this.bindDelay <= 0 && this.swapBackDelay <= 0 && this.dashWithDelay <= 0) {
                    this.slotMem = client.field_1724.method_31548().method_67532();
                    this.yawMem = client.field_1724.method_5791();
                    this.rotMemory = -135;
                    client.field_1724.method_36456(this.yawMem + (float)this.rotMemory);
                    this.dashWithDelay = 1;
                    this.bindDelay = config.swapDelayTicks;
                    return;
                }
                if (dashRequested && class_310.method_1551().field_1690.field_1849.method_1434() && class_310.method_1551().field_1690.field_1881.method_1434() && this.bindDelay <= 0 && this.swapBackDelay <= 0 && this.dashWithDelay <= 0) {
                    this.slotMem = client.field_1724.method_31548().method_67532();
                    this.yawMem = client.field_1724.method_5791();
                    this.rotMemory = 135;
                    client.field_1724.method_36456(this.yawMem + (float)this.rotMemory);
                    this.dashWithDelay = 1;
                    this.bindDelay = config.swapDelayTicks;
                    return;
                }
            }
            if (class_310.method_1551().field_1724 != null) {
                if (this.chargedUp) {
                    if (client.field_1692 != null) {
                        if ((double)client.field_1724.method_5739(client.field_1692) >= 3.2 && (double)client.field_1724.method_7261(0.0f) == 1.0) {
                            this.hitDelay = 1;
                            this.chargedUp = false;
                        }
                    } else {
                        this.chargedUp = false;
                    }
                }
                int swordswapcounter = 0;
                boolean foundSpear = false;
                for (class_1799 itemStack : client.field_1724.method_31548()) {
                    class_6880.class_6883 WB;
                    if (String.valueOf(itemStack.method_7909()).contains("spear") && class_1890.method_8225((class_6880)(WB = client.field_1724.method_73183().method_30349().method_30530(class_7924.field_41265).method_46747(class_1893.field_63420)), (class_1799)itemStack) <= 0) {
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
                        if (dashRequested && class_310.method_1551().field_1690.field_1894.method_1434() && this.bindDelay <= 0 && this.swapBackDelay <= 0 && this.dashWithDelay <= 0) {
                            this.yawMem = client.field_1724.method_5791();
                            this.dash(client, 0);
                        }
                        if (dashRequested && class_310.method_1551().field_1690.field_1913.method_1434() && this.bindDelay <= 0 && this.swapBackDelay <= 0 && this.dashWithDelay <= 0) {
                            this.slotMem = client.field_1724.method_31548().method_67532();
                            this.yawMem = client.field_1724.method_5791();
                            this.rotMemory = -90;
                            client.field_1724.method_36456(this.yawMem + (float)this.rotMemory);
                            this.dashWithDelay = 1;
                            this.bindDelay = config.swapDelayTicks;
                            return;
                        }
                        if (dashRequested && class_310.method_1551().field_1690.field_1849.method_1434() && this.bindDelay <= 0 && this.swapBackDelay <= 0 && this.dashWithDelay <= 0) {
                            this.slotMem = client.field_1724.method_31548().method_67532();
                            this.yawMem = client.field_1724.method_5791();
                            this.rotMemory = 90;
                            client.field_1724.method_36456(this.yawMem + (float)this.rotMemory);
                            this.dashWithDelay = 1;
                            this.bindDelay = config.swapDelayTicks;
                            return;
                        }
                        if (dashRequested && class_310.method_1551().field_1690.field_1881.method_1434() && this.bindDelay <= 0 && this.swapBackDelay <= 0 && this.dashWithDelay <= 0) {
                            this.slotMem = client.field_1724.method_31548().method_67532();
                            this.yawMem = client.field_1724.method_5791();
                            this.rotMemory = -180;
                            client.field_1724.method_36456(this.yawMem + (float)this.rotMemory);
                            this.dashWithDelay = 1;
                            this.bindDelay = config.swapDelayTicks;
                            return;
                        }
                    } else {
                        if (dashRequested && this.bindDelay <= 0 && this.swapBackDelay <= 0 && this.dashWithDelay <= 0) {
                            this.yawMem = client.field_1724.method_5791();
                            this.dash(client, 0);
                        }
                    }
                }
            }
        });
        AttackEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
            class_310 client = class_310.method_1551();
            if (entity == null || client.field_1692 == null) {
                return class_1269.field_5811;
            }
            if ((double)player.method_5739(entity) >= 3.2 && SpearDashClient.config.rangeExtensions) {
                if (this.advancedFeaturesEnabled) {
                    this.slotMem = player.method_31548().method_67532();
                    if ((double)client.field_1724.method_7261(0.0f) >= 1.0 && this.swapSlotNOTEnch(player, "spear", class_1893.field_63420)) {
                        SpearDashClient.playSoundFast(class_3417.field_63241, client);
                        SpearDashClient.particlesMaker(entity, client, (class_2394)class_2398.field_11249);
                        this.yawMem = player.method_36454();
                        this.swapBackDelay = SpearDashClient.config.swapDelayTicks;
                        if (client instanceof ForceAttack) {
                            ForceAttack accessor = (ForceAttack)client;
                            boolean bl = accessor.callDoAttack();
                        }
                    }
                    return class_1269.field_5814;
                }
                return class_1269.field_5811;
            }
            return class_1269.field_5811;
        });
    }

    private void dash(class_310 client, int rotDegrees) {
        if ((double)client.field_1724.method_7261(0.0f) >= 1.0) {
            this.rotMemory = 0;
            if (!client.field_1724.method_6128()) {
                this.slotMem = client.field_1724.method_31548().method_67532();
                if (this.swapSlotEnchSelect((class_1657)client.field_1724, "spear", class_1893.field_63420)) {
                    this.simulateHit();
                    this.forwardsOverride = true;
                    this.swapBackDelay = SpearDashClient.config.swapDelayTicks;
                    SpearDashClient.particlesMaker((class_1297)client.field_1724, client, (class_2394)class_2398.field_11203);
                    SpearDashClient.playSound(class_3417.field_47716, client);
                    this.bindDelay = config.swapDelayTicks;
                }
            } else if (!client.field_1724.method_6047().method_63693().method_44745(class_2561.method_30163((String)"chestplate"))) {
                this.slotMem = client.field_1724.method_31548().method_67532();
                this.glideWithDelay = 1;
            }
        }
    }
}

