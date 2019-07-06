package reirokusanami.tools;

import com.google.common.collect.ImmutableList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import reirokusanami.TinkersGunsConstruction;
import reirokusanami.core.GunCore;
import reirokusanami.modules.ModuleTools;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.materials.MaterialTypes;
import slimeknights.tconstruct.library.tinkering.Category;
import slimeknights.tconstruct.library.tinkering.PartMaterialType;
import slimeknights.tconstruct.library.tools.ProjectileLauncherNBT;

import javax.annotation.Nonnull;
import java.util.List;

public class WeaponHandgun extends GunCore {

	protected final static String HAMMER = "part_hammer";
	protected final static String SLIDE = "part_slide";
	private ImmutableList<Item> BulletMatches = null;

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

	@Override
	public float baseProjectileDamage() {
		return 4;
	}

	@Override
	public float projectileDamageModifier() {
        return 0.4F;
	}

	@Override
	protected float baseProjectileSpeed() {
		return 5.0f;
	}

	@Override
	protected float baseInaccuracy() {
		return 0.5f;
	}

	protected float baseInaccuracyRange() {
		return 0.8f;
	}

	@Override
	protected List<Item> getAmmoItems() {
		if (BulletMatches == null) {
			ImmutableList.Builder<Item> builder = ImmutableList.builder();
			if (ModuleTools.bullet != null) {
				builder.add(ModuleTools.bullet);
			}
			BulletMatches = builder.build();
		}
		return BulletMatches;
	}



	@Nonnull
	@Override
	public ItemStack getAmmoToRender(@Nonnull ItemStack weapon, EntityLivingBase player) {
		return ItemStack.EMPTY;
	}

	public ProjectileLauncherNBT buildTagData(List<Material> materialList) {
		ProjectileLauncherNBT NBT = new ProjectileLauncherNBT();
		NBT.handle(materialList.get(0).getStatsOrUnknown(MaterialTypes.HANDLE));
		NBT.head(materialList.get(1).getStatsOrUnknown(MaterialTypes.HEAD));
		// Now for the time being [Extra]
		NBT.extra(materialList.get(2).getStatsOrUnknown(HAMMER));
		NBT.extra(materialList.get(3).getStatsOrUnknown(SLIDE));

		return NBT;
	}
}
