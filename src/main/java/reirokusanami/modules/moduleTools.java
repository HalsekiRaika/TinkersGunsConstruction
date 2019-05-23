package reirokusanami.modules;

import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;
import reirokusanami.handler.TGCRegister;
import reirokusanami.miscellaneous.TGCConfig;
import reirokusanami.tools.weaponHandgun;
import slimeknights.tconstruct.library.tools.ToolCore;
import slimeknights.tconstruct.library.tools.ToolPart;

import javax.annotation.OverridingMethodsMustInvokeSuper;
import java.util.ArrayList;
import java.util.List;

public class moduleTools extends TGCRegister implements IModule {
    //PARTS
    public static ToolPart part_barrelMedium;
    public static ToolPart part_Hammer;
    public static ToolPart part_Muzzle;
    public static ToolPart part_Slide;
    public static ToolPart part_Bulletcartridge;
    public static ToolPart part_Warhead;
    public static ToolPart part_Propellant;

    //TOOLS
    public static ToolCore tool_weaponHandgun = new weaponHandgun();

    @Override
    public void PartInitialization(IForgeRegistry<Item> event){
        part_barrelMedium = RegisterParts(part_barrelMedium, "mediumbarrel", 4, event);
        part_Hammer = RegisterParts(part_Hammer, "hammer", 1, event);
        part_Muzzle = RegisterParts(part_Muzzle, "muzzle", 1, event);
        part_Slide = RegisterParts(part_Slide, "slide", 3 ,event);

        ToolInitialization(event);
    }

    @Override
    public void ToolInitialization(IForgeRegistry<Item> event) {
        RegisterTools(TGCConfig.WeaponCategoly.HandGun, true, tool_weaponHandgun, event);

        RegisterToolBuilding();
    }
}
