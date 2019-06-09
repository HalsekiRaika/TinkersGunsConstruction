package reirokusanami.modules;

import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;
import reirokusanami.handler.TGCRegister;
import reirokusanami.miscellaneous.TGCConfig;
import reirokusanami.tools.WeaponHandgun;
import slimeknights.tconstruct.library.tools.ToolCore;
import slimeknights.tconstruct.library.tools.ToolPart;

public class ModuleTools implements IModule {

	// PARTS
	public static ToolPart	partBarrelMedium;
	public static ToolPart	partHammer;
	public static ToolPart	partMuzzle;
	public static ToolPart	partGrip;
	public static ToolPart	partBulletcartridge;
	public static ToolPart	partWarhead;
	public static ToolPart	partPropellant;

	// TOOLS
	public static ToolCore	toolWeaponHandgun;

	public static void initializationItems(IForgeRegistry<Item> registry) {
		partBarrelMedium = TGCRegister.registerParts("medium_barrel", 8, registry);
		partHammer = TGCRegister.registerParts("hammer", 1, registry);
		partMuzzle = TGCRegister.registerParts("muzzle", 2, registry);
		partGrip = TGCRegister.registerParts("grip", 3, registry);

		toolWeaponHandgun = TGCRegister.registerTools(TGCConfig.WeaponCategoly.handGun, new WeaponHandgun(), registry);
		TGCRegister.registerToolBuilding();
	}

}
