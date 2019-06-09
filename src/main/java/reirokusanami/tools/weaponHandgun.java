package reirokusanami.tools;

import java.util.List;

import net.minecraft.util.ResourceLocation;
import reirokusanami.TinkersGunsConstruction;
import reirokusanami.modules.ModuleTools;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.materials.MaterialTypes;
import slimeknights.tconstruct.library.tinkering.Category;
import slimeknights.tconstruct.library.tinkering.PartMaterialType;
import slimeknights.tconstruct.library.tools.TinkerToolCore;
import slimeknights.tconstruct.library.tools.ToolNBT;

public class WeaponHandgun extends TinkerToolCore {

	protected final static String HAMMER = "part_hammer";
	protected final static String SLIDE = "part_slide";

	public WeaponHandgun() {
		super(PartMaterialType.handle(ModuleTools.partGrip), PartMaterialType.head(ModuleTools.partMuzzle), new PartMaterialType(ModuleTools.partHammer), new PartMaterialType(ModuleTools.partBarrelMedium));
		this.addCategory(Category.WEAPON);
		this.setUnlocalizedName("handgun");
		this.setRegistryName(new ResourceLocation(TinkersGunsConstruction.MODID, this.getUnlocalizedName().substring(5)));
	}

	@Override
	public float damagePotential() {
		return 1.5f;
	}

	@Override
	public double attackSpeed() {
		return 2;
	}

	protected ToolNBT buildTagData(List<Material> materialList) {
		ToolNBT NBT = new ToolNBT();
		NBT.handle(materialList.get(0).getStatsOrUnknown(MaterialTypes.HANDLE));
		NBT.head(materialList.get(1).getStatsOrUnknown(MaterialTypes.HEAD));
		// Now for the time being [Extra]
		NBT.extra(materialList.get(2).getStatsOrUnknown(HAMMER));
		NBT.extra(materialList.get(3).getStatsOrUnknown(SLIDE));

		return NBT;
	}
}
