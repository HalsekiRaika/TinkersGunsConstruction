package reirokusanami.modules;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.tools.IToolPart;
import slimeknights.tconstruct.library.tools.ToolCore;
import slimeknights.tconstruct.library.tools.ToolPart;

import java.util.ArrayList;
import java.util.List;

public class moduleTools implements IModule {
    public static ToolPart part_barrelMedium;

    private static final List<ToolCore> tools = new ArrayList<>();
    private static final List<IToolPart> toolParts = new ArrayList<>();

    @SubscribeEvent
    public static void InitializationItems(RegistryEvent.Register<Item> event){
        part_barrelMedium = new ToolPart(Material.VALUE_Ingot * 6);
        part_barrelMedium.setRegistryName("part_barrelMedium");
        event.getRegistry().register(part_barrelMedium);
        TinkerRegistry.registerToolPart(part_barrelMedium);
        //reirokusanami.proxy.registerToolPartModel(); This Line has Error
    }

}
