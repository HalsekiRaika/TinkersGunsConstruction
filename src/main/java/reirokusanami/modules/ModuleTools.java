package reirokusanami.modules;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;
import reirokusanami.handler.TGCToolRegister;
import reirokusanami.materials.GunMaterialTypes;
import reirokusanami.materials.OrbMaterialStats;
import reirokusanami.miscellaneous.TGCConfig;
import reirokusanami.tools.Ammo.AmmoBullet;
import reirokusanami.tools.WeaponHandgun;
import reirokusanami.tools.WeaponSorceryGun;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.tools.ToolCore;
import slimeknights.tconstruct.library.tools.ToolPart;
import slimeknights.tconstruct.tools.TinkerTraits;

public class ModuleTools implements IModule {
	//MATERIALS
	public static final Material ENDERPEARL_MATERIAL = new Material("enderpearl", 0X008b8b, false);
	//public static final Material GUNPOWDER_MATERIAL = new Material("gunpowder", 0XD8e5ee, false);

	// PARTS
	public static ToolPart partBarrelMedium;
	public static ToolPart partHammer;
	public static ToolPart partMuzzle;
	public static ToolPart partGrip;
	public static ToolPart partBulletcartridge;
	public static ToolPart partWarhead;
	public static ToolPart partPropellant;
	public static ToolPart partCore;
	public static ToolPart partSmallHandle;
	public static ToolPart partSmallBarrel;

	// TOOLS
	public static ToolCore toolWeaponHandgun;
	public static ToolCore toolWeaponSorceryGun;
	public static AmmoBullet bullet;

	public static void initializationTools(IForgeRegistry<Item> registry) {
		partBarrelMedium = TGCToolRegister.registerParts("medium_barrel", 8, registry);
		partHammer = TGCToolRegister.registerParts("hammer", 1, registry);
		partMuzzle = TGCToolRegister.registerParts("muzzle", 2, registry);
		partGrip = TGCToolRegister.registerParts("grip", 3, registry);
		partCore = TGCToolRegister.registerOrbParts("orb", 1, registry);
		partSmallHandle = TGCToolRegister.registerParts("smallhandle", 1, registry);
		partSmallBarrel = TGCToolRegister.registerParts("smallbarrel", 3, registry);
		partBulletcartridge = TGCToolRegister.registerParts("bulletcartridge", 3, registry);
		partWarhead = TGCToolRegister.registerParts("warhead", 2, registry);
		partPropellant = TGCToolRegister.registerParts("propellant", 2, registry);

		//MATERIAL
		Material.UNKNOWN.addStats(new OrbMaterialStats(1, 1));


		ENDERPEARL_MATERIAL.setCraftable(true);
		ENDERPEARL_MATERIAL.addItem(Items.ENDER_PEARL, 1, Material.VALUE_Ingot);
		ENDERPEARL_MATERIAL.addTrait(TinkerTraits.enderference, GunMaterialTypes.ORB);
		TinkerRegistry.addMaterialStats(ENDERPEARL_MATERIAL, new OrbMaterialStats(-40, 3));
		TinkerRegistry.addMaterial(ENDERPEARL_MATERIAL);

		toolWeaponHandgun = TGCToolRegister.registerTools(TGCConfig.WeaponCategoly.handGun, new WeaponHandgun(), registry);
		toolWeaponSorceryGun = TGCToolRegister.registerTools(TGCConfig.WeaponCategoly.sorceryGun, new WeaponSorceryGun(), registry);
		bullet = TGCToolRegister.registerBullets(new AmmoBullet(), registry);

		TinkerRegistry.registerToolCrafting(toolWeaponSorceryGun);
	}

}
