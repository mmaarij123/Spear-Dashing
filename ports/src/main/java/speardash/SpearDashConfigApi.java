package speardash;

import com.google.gson.Gson;
import java.io.FileReader;
import java.io.Reader;
import java.util.List;
import java.util.Optional;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.server.dialog.CommonDialogData;
import net.minecraft.server.dialog.Dialog;
import net.minecraft.server.dialog.DialogListDialog;
import net.minecraft.tags.DialogTags;
import net.minecraft.server.dialog.DialogAction;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.HolderSet;
import net.minecraft.core.HolderGetter;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.core.registries.Registries;

@Environment(value=EnvType.CLIENT)
public class SpearDashConfigApi {
    public static ResourceKey<Dialog> MY_CONFIG_DIALOG = SpearDashConfigApi.of("my_config_dialog");

    public static ResourceKey<Dialog> of(String id) {
        return ResourceKey.create((ResourceKey)Registries.DIALOG, (Identifier)Identifier.fromNamespaceAndPath((String)"speardash", (String)id));
    }

    public static void bootstrap(BootstrapContext<Dialog> registry) {
        try {
            Gson gson = new Gson();
            ConfigData data = (ConfigData)gson.fromJson((Reader)new FileReader("config/speardash/config.json"), ConfigData.class);
            HolderGetter lookup = registry.lookup(Registries.DIALOG);
            List<String> buttons = data.buttons;
            CommonDialogData commonData = new CommonDialogData((Component)Component.literal((String)data.title), Optional.of(Component.literal((String)data.description)), true, true, DialogAction.CLOSE, List.of(), List.of());
            registry.register(MY_CONFIG_DIALOG, new DialogListDialog(commonData, (HolderSet)lookup.getOrThrow(DialogTags.PAUSE_SCREEN_ADDITIONS), Optional.empty(), 1, 310));
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
