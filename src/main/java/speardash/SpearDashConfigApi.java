/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_11417
 *  net.minecraft.class_11419
 *  net.minecraft.class_11420
 *  net.minecraft.class_11455
 *  net.minecraft.class_11520
 *  net.minecraft.class_2561
 *  net.minecraft.class_2960
 *  net.minecraft.class_5321
 *  net.minecraft.class_6885
 *  net.minecraft.class_7871
 *  net.minecraft.class_7891
 *  net.minecraft.class_7924
 */
package speardash;

import com.google.gson.Gson;
import java.io.FileReader;
import java.io.Reader;
import java.util.List;
import java.util.Optional;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_11417;
import net.minecraft.class_11419;
import net.minecraft.class_11420;
import net.minecraft.class_11455;
import net.minecraft.class_11520;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_5321;
import net.minecraft.class_6885;
import net.minecraft.class_7871;
import net.minecraft.class_7891;
import net.minecraft.class_7924;

@Environment(value=EnvType.CLIENT)
public class SpearDashConfigApi {
    public static class_5321<class_11419> MY_CONFIG_DIALOG = SpearDashConfigApi.of("my_config_dialog");

    public static class_5321<class_11419> of(String id) {
        return class_5321.method_29179((class_5321)class_7924.field_60818, (class_2960)class_2960.method_60655((String)"speardash", (String)id));
    }

    public static void bootstrap(class_7891<class_11419> registry) {
        try {
            Gson gson = new Gson();
            ConfigData data = (ConfigData)gson.fromJson((Reader)new FileReader("config/speardash/config.json"), ConfigData.class);
            class_7871 lookup = registry.method_46799(class_7924.field_60818);
            List<String> buttons = data.buttons;
            class_11417 commonData = new class_11417((class_2561)class_2561.method_43470((String)data.title), Optional.of(class_2561.method_43470((String)data.description)), true, true, class_11520.field_60962, List.of(), List.of());
            registry.method_46838(MY_CONFIG_DIALOG, new class_11420(commonData, (class_6885)lookup.method_46735(class_11455.field_60663), Optional.empty(), 1, 310));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Environment(value=EnvType.CLIENT)
    public static class ConfigData {
        public String title;
        public String description;
        public List<String> buttons;
    }
}

