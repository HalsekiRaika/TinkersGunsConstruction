package reirokusanami.modules;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
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
    public static ToolCore tool_weaponHandgun;

    private static final List<ToolCore> TOOL_CORES = new ArrayList<>();
    private static final List<IToolPart> TOOL_PARTS = new ArrayList<>();

    @SubscribeEvent
    public static void InitializationItems(RegistryEvent.Register<Item> event){
        part_barrelMedium = new ToolPart(Material.VALUE_Ingot * 6);
        part_barrelMedium.setRegistryName("part_barrelMedium");
        event.getRegistry().register(part_barrelMedium);
        TinkerRegistry.registerToolPart(part_barrelMedium);
        //reirokusanami.proxy.registerToolPartModel(); This Line has Error
        TOOL_PARTS.add(part_barrelMedium);

        part_Hammer = new ToolPart(Material.VALUE_Ingot * 1);
        part_Hammer.setRegistryName("part_Hammer");
        event.getRegistry().register(part_Hammer);
        TinkerRegistry.registerToolPart(part_Hammer);
        //reirokusanami.proxy.registerToolPartModel(); This Line has Error
        TOOL_PARTS.add(part_Hammer);

        part_Muzzle = new ToolPart(Material.VALUE_Ingot * 1);
        part_Muzzle.setRegistryName("part_Muzzle");
        event.getRegistry().register(part_Muzzle);
        TinkerRegistry.registerToolPart(part_Muzzle);
        //reirokusanami.proxy.registerToolPartModel(); This Line has Error
        TOOL_PARTS.add(part_Muzzle);

        part_Slide = new ToolPart(Material.VALUE_Ingot * 1);
        part_Slide.setRegistryName("part_Muzzle");
        event.getRegistry().register(part_Slide);
        TinkerRegistry.registerToolPart(part_Slide);
        //reirokusanami.proxy.registerToolPartModel(); This Line has Error
        TOOL_PARTS.add(part_Slide);

        if(TGCConfig.WeaponCategoly.HandGun){
            tool_weaponHandgun = new weaponHandgun();
            event.getRegistry().register(tool_weaponHandgun);
            TinkerRegistry.registerTool(tool_weaponHandgun);
            TOOL_CORES.add(tool_weaponHandgun);

        }
    }

}
