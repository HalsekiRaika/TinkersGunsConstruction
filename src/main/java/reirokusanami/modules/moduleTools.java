package reirokusanami.modules;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import reirokusanami.handler.TGCRegister;
import reirokusanami.miscellaneous.TGCConfig;
import reirokusanami.tools.weaponHandgun;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.tools.IToolPart;
import slimeknights.tconstruct.library.tools.ToolCore;
import slimeknights.tconstruct.library.tools.ToolPart;

import java.util.ArrayList;
import java.util.List;

public class moduleTools implements IModule {
    //PARTS
    public static ToolPart part_barrelMedium;
    public static ToolPart part_Hammer;
    public static ToolPart part_Muzzle;
    public static ToolPart part_Slide;

    //TOOLS
    public static ToolCore tool_weaponHandgun = new weaponHandgun();

    private static final List<ToolCore> TOOL_CORES = new ArrayList<>();
    private static final List<IToolPart> TOOL_PARTS = new ArrayList<>();

    @SubscribeEvent
    public static void InitializationItems(RegistryEvent.Register<Item> event){
        TGCRegister.RegiterParts(part_barrelMedium, "part_barrelMedium", 8, event);
        TGCRegister.RegiterParts(part_Hammer, "part_Hammer", 1, event);
        TGCRegister.RegiterParts(part_Muzzle, "part_Muzzle", 2, event);
        TGCRegister.RegiterParts(part_Slide, "part_Slide", 3, event);

        TGCRegister.RegisterTools(TGCConfig.WeaponCategoly.HandGun ,tool_weaponHandgun, event);
    }

}
