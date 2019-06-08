package reirokusanami.modules;

import static reirokusanami.handler.TGCRegister.RegisterToolBuilding;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.RegistryEvent;
import reirokusanami.handler.TGCRegister;
import reirokusanami.miscellaneous.TGCConfig;
import reirokusanami.tools.weaponHandgun;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.tools.Pattern;
import slimeknights.tconstruct.library.tools.ToolCore;
import slimeknights.tconstruct.library.tools.ToolPart;
import slimeknights.tconstruct.tools.TinkerTools;

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
    public static ToolCore tool_weaponHandgun;

    public static void InitializationItems(RegistryEvent.Register<Item> event){
    	part_barrelMedium = TGCRegister.RegisterParts(part_barrelMedium, "medium_barrel", 8, event);
        part_Hammer = TGCRegister.RegisterParts(part_Hammer, "hammer", 1, event);
        part_Muzzle = TGCRegister.RegisterParts(part_Muzzle, "muzzle", 2, event);
        part_Grip = TGCRegister.RegisterParts(part_Grip, "grip", 3, event);
        
        registerPartStencil(part_barrelMedium);
        registerPartStencil(part_Hammer);
        registerPartStencil(part_Muzzle);
        registerPartStencil(part_Grip);

        tool_weaponHandgun = new weaponHandgun();
        TGCRegister.RegisterTools(TGCConfig.WeaponCategoly.HandGun ,tool_weaponHandgun, event);
        RegisterToolBuilding();
    }
    
    private static void registerPartStencil(ToolPart part)
    {
    	ItemStack stencil = new ItemStack(TinkerTools.pattern);
		Pattern.setTagForPart(stencil, part);
		TinkerRegistry.registerStencilTableCrafting(stencil);
    }

}
