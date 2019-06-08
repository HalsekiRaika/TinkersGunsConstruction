package reirokusanami.modules;

import static reirokusanami.handler.TGCRegister.RegisterToolBuilding;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import reirokusanami.handler.TGCRegister;
import slimeknights.tconstruct.library.tools.ToolPart;

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

    public static void InitializationItems(RegistryEvent.Register<Item> event){
        TGCRegister.RegisterParts(part_barrelMedium, "medium_barrel", 8, event);
        TGCRegister.RegisterParts(part_Hammer, "hammer", 1, event);
        TGCRegister.RegisterParts(part_Muzzle, "muzzle", 2, event);
        TGCRegister.RegisterParts(part_Grip, "grip", 3, event);

        //TGCRegister.RegisterTools(TGCConfig.WeaponCategoly.HandGun ,tool_weaponHandgun, event);
        RegisterToolBuilding();
    }

}
