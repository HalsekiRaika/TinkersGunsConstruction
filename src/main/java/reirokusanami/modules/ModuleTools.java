package reirokusanami.modules;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;
import reirokusanami.handler.TGCRegister;
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
import slimeknights.tconstruct.tools.AbstractToolPulse;
import slimeknights.tconstruct.tools.TinkerTraits;

public class ModuleTools extends AbstractToolPulse implements IModule {
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
	public static ToolCore	toolWeaponHandgun;
	public static ToolCore toolWeaponSorceryGun;
	public static AmmoBullet bullet;

	public static void initializationItems(IForgeRegistry<Item> registry) {
		partBarrelMedium = TGCRegister.registerParts("medium_barrel", 8, registry);
		partHammer = TGCRegister.registerParts("hammer", 1, registry);
		partMuzzle = TGCRegister.registerParts("muzzle", 2, registry);
		partGrip = TGCRegister.registerParts("grip", 3, registry);
		partCore = TGCRegister.registerOrbParts("orb", 1, registry);
		partSmallHandle = TGCRegister.registerParts("smallhandle", 1, registry);
		partSmallBarrel = TGCRegister.registerParts("smallbarrel", 3, registry);
		partBulletcartridge = TGCRegister.registerParts("bulletcartridge", 3, registry);
		partWarhead = TGCRegister.registerParts("warhead", 2, registry);
		partPropellant = TGCRegister.registerParts("propellant", 2, registry);

		//MATERIAL
		Material.UNKNOWN.addStats(new OrbMaterialStats(1, 1));


		ENDERPEARL_MATERIAL.setCraftable(true);
		ENDERPEARL_MATERIAL.addItem(Items.ENDER_PEARL, 1, Material.VALUE_Ingot);
		ENDERPEARL_MATERIAL.addTrait(TinkerTraits.alien, GunMaterialTypes.ORB);
		TinkerRegistry.addMaterialStats(ENDERPEARL_MATERIAL, new OrbMaterialStats(160, 3));
		TinkerRegistry.addMaterial(ENDERPEARL_MATERIAL);

		toolWeaponHandgun = TGCRegister.registerTools(TGCConfig.WeaponCategoly.handGun, new WeaponHandgun(), registry);
		toolWeaponSorceryGun = TGCRegister.registerTools(TGCConfig.WeaponCategoly.sorceryGun, new WeaponSorceryGun(), registry);
		bullet = registerTool(registry, new AmmoBullet(), "bullet");

		TinkerRegistry.registerToolCrafting(toolWeaponSorceryGun);
	}

}
