package reirokusanami.tools;

import com.google.common.collect.ImmutableList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import reirokusanami.Entity.EntityProjectile;
import reirokusanami.TinkersGunsConstruction;
import reirokusanami.core.GunCore;
import reirokusanami.event.GunToolEvent;
import reirokusanami.modules.ModuleTools;
import slimeknights.tconstruct.library.events.ProjectileEvent;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.materials.MaterialTypes;
import slimeknights.tconstruct.library.tinkering.Category;
import slimeknights.tconstruct.library.tinkering.PartMaterialType;
import slimeknights.tconstruct.library.tools.ProjectileLauncherNBT;
import slimeknights.tconstruct.library.utils.ToolHelper;

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
		return 7.0f;
	}

	@Override
	protected float baseInaccuracy() {
		return 0.0004f;
	}

	protected float baseInaccuracyRange() {
		return 0.45f;
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

	@Override
	public void ShootProjectile(@Nonnull ItemStack ammoIn, @Nonnull ItemStack bow, World worldIn, EntityPlayer player) {
		EntityProjectile.setRegexAccuracy(0.004f);
		float power = ItemBow.getArrowVelocity(30) * baseProjectileSpeed();
		power *= ProjectileLauncherNBT.from(bow).range;

		if (!worldIn.isRemote) {
			GunToolEvent.OnGunShoot toolEvent = GunToolEvent.OnGunShoot.fireEvent(bow, ammoIn, player, 1, baseInaccuracy());
			ItemStack ammoShoot = ammoIn.copy();
			for (int i = 0; i < toolEvent.projectileCount; i++){
				boolean usedAmmo = false;
				if(i == 0 || toolEvent.consumeAmmoPerProjectile){
					usedAmmo = consumeAmmo(ammoIn, player);
				}
				float baseInAccuracy = toolEvent.bonusInaccuracy;
				if (i > 0) {
					baseInAccuracy += 0.004;
				}

				baseInAccuracy *= baseInaccuracyRange();
				EntityArrow projectile = getProjectileEntity(ammoShoot, bow, worldIn, player, power, baseInAccuracy, 1, usedAmmo);
				if(projectile != null && ProjectileEvent.OnLaunch.fireEvent(projectile, bow, player)){
					if(!player.capabilities.isCreativeMode){
						ToolHelper.damageTool(bow, 1, player);
					}
					worldIn.spawnEntity(projectile);
				}
			}
		}
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
