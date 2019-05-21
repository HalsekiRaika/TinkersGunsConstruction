package reirokusanami.materials.modules;

import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.tools.IToolPart;
import slimeknights.tconstruct.library.tools.ToolCore;
import slimeknights.tconstruct.library.tools.ToolPart;

import java.util.ArrayList;
import java.util.List;

public class BaseMaterial {
    public static final List<ToolCore> TOOL_CORES = new ArrayList<>();
    public static final List<IToolPart> TOOL_PARTS = new ArrayList<>();

    protected static ToolPart RegisterParts(ToolPart ToolPart, String Name, int Costs, IForgeRegistry<Item> Registry) {
        ToolPart = new ToolPart(Material.VALUE_Ingot * Costs);
        ToolPart.setRegistryName(Name);
        Registry.register(ToolPart);
        TinkerRegistry.registerToolPart(ToolPart);
        //reirokusanami.Proxy.ClientProxy.registerToolPartModel(ToolPart);
        TOOL_PARTS.add(ToolPart);

        return ToolPart;
    }

    protected static void RegisterTools(ToolCore ToolCore, Boolean IsFORGE, IForgeRegistry<Item> Registry){
        Registry.register(ToolCore);
        if(IsFORGE == true){
            TinkerRegistry.registerToolForgeCrafting(ToolCore);
        } else {
            TinkerRegistry.registerToolStationCrafting(ToolCore);
        }
        //reirokusanami.Proxy.registerToolModel(ToolCore);
        TOOL_CORES.add(ToolCore);
    }

}
