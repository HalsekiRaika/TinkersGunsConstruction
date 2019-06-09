package reirokusanami.modules;

import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;
import reirokusanami.handler.TGCRegister;
import reirokusanami.miscellaneous.TGCConfig;
import reirokusanami.tools.WeaponHandgun;
import reirokusanami.tools.WeaponSorceryGun;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.tools.ToolCore;
import slimeknights.tconstruct.library.tools.ToolPart;

public class ModuleTools implements IModule {
	public static final Material CHEESE_MATERIAL = new Material("cheese", 0XF2C132);

	public static final Material STRING_CHEESE = new Material("string_cheese", 0XF2C132);
	// PARTS
	public static ToolPart	partBarrelMedium;
	public static ToolPart	partHammer;
	public static ToolPart	partMuzzle;
	public static ToolPart	partGrip;
	public static ToolPart	partBulletcartridge;
	public static ToolPart	partWarhead;
	public static ToolPart	partPropellant;
	public static ToolPart partCore;
	public static ToolPart partSmallHandle;
	public static ToolPart partSmallBarrel;

	// TOOLS
	public static ToolCore	toolWeaponHandgun;
	public static ToolCore toolWeaponSorceryGun;

	public static void initializationItems(IForgeRegistry<Item> registry) {
		partBarrelMedium = TGCRegister.registerParts("medium_barrel", 8, registry);
		partHammer = TGCRegister.registerParts("hammer", 1, registry);
		partMuzzle = TGCRegister.registerParts("muzzle", 2, registry);
		partGrip = TGCRegister.registerParts("grip", 3, registry);
		partCore = TGCRegister.registerParts("orb", 1, registry);
		partSmallHandle = TGCRegister.registerParts("smallhandle", 1, registry);
		partSmallBarrel = TGCRegister.registerParts("smallbarrel", 3, registry);

		toolWeaponHandgun = TGCRegister.registerTools(TGCConfig.WeaponCategoly.handGun, new WeaponHandgun(), registry);
		toolWeaponSorceryGun = TGCRegister.registerTools(TGCConfig.WeaponCategoly.sorceryGun, new WeaponSorceryGun(), registry);
		TGCRegister.registerToolBuilding();
	}

}
