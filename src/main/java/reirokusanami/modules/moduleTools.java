package reirokusanami.modules;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import reirokusanami.TinkersGunsConstruction;
import reirokusanami.handler.TGCRegister;
import reirokusanami.miscellaneous.TGCConfig;
import reirokusanami.tools.weaponHandgun;
import slimeknights.tconstruct.common.TinkerPulse;
import slimeknights.tconstruct.library.tools.ToolCore;
import slimeknights.tconstruct.library.tools.ToolPart;

import static reirokusanami.handler.TGCRegister.RegisterToolBuilding;

@Mod.EventBusSubscriber(modid = TinkersGunsConstruction.MODID)
public class moduleTools implements IModule {
    //PARTS
    public static ToolPart part_barrelMedium;
    public static ToolPart part_Hammer;
    public static ToolPart part_Muzzle;
    public static ToolPart part_Grip;
    public static ToolPart part_Bulletcartridge;
    public static ToolPart part_Warhead;
    public static ToolPart part_Propellant;

    //TOOLS
    //public static ToolCore tool_weaponHandgun = new weaponHandgun();

    @SubscribeEvent
    public static void InitializationItems(RegistryEvent.Register<Item> event){
        TGCRegister.RegisterParts(part_barrelMedium, "medium_Barrel", 8, event);
        TGCRegister.RegisterParts(part_Hammer, "hammer", 1, event);
        TGCRegister.RegisterParts(part_Muzzle, "muzzle", 2, event);
        TGCRegister.RegisterParts(part_Grip, "grip", 3, event);

        //TGCRegister.RegisterTools(TGCConfig.WeaponCategoly.HandGun ,tool_weaponHandgun, event);
        RegisterToolBuilding();
    }

}
