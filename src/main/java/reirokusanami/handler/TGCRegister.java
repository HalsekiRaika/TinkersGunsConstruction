package reirokusanami.handler;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.SidedProxy;
import reirokusanami.proxy.UsualProxy;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.tools.ToolCore;
import slimeknights.tconstruct.library.tools.ToolPart;

import java.util.ArrayList;
import java.util.List;

public class TGCRegister {

    public static final List<ToolPart> TOOL_PARTS = new ArrayList<>();
    public static final List<ToolCore> TOOL_CORES = new ArrayList<>();
    private static final String CLIENT_PROXY = "reirokusanami.proxy.ClientProxy";
    private static final String SERVER_PROXY = "reirokusanami.proxy.UsualProxy";

    @SidedProxy(clientSide = CLIENT_PROXY)
    public static UsualProxy proxy;
    /*
     * @param Toolpart
     * @param Name Enter the ToolPart name.
     * @param Cost Cost required to cast ToolPart (ingot conversion)
     * @param event RegistryEvent.Register<Item>
     */
    public static void RegiterParts(ToolPart _ToolPart, String Name, int Cost, RegistryEvent.Register<Item> event) {
        _ToolPart = new ToolPart(Material.VALUE_Ingot * Cost);
        _ToolPart.setRegistryName(Name);
        event.getRegistry().register(_ToolPart);
        TinkerRegistry.registerToolPart(_ToolPart);
        proxy.registerToolPartModel(_ToolPart);
        TOOL_PARTS.add(_ToolPart);
    }

    /*
     * @param isAllowConfig Reference from TGCConfig
     * @param _ToolCore EXAMPLE: public static ToolCore tool_weaponHandgun = new weaponHandgun();
     * @param event RegistryEvent.Register<Item>
     */
    public static void RegisterTools(Boolean isAllowConfig ,ToolCore _ToolCore, RegistryEvent.Register<Item> event) {
        if(isAllowConfig){
            event.getRegistry().register(_ToolCore);
            TinkerRegistry.registerTool(_ToolCore);
            proxy.registerToolModel(_ToolCore);
            TOOL_CORES.add(_ToolCore);
        }
    }
}
