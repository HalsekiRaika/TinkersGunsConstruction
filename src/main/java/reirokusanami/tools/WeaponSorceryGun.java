package reirokusanami.tools;

import com.google.common.collect.ImmutableList;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import reirokusanami.TinkersGunsConstruction;
import reirokusanami.core.GunCore;
import reirokusanami.materials.GunMaterial;
import reirokusanami.materials.GunMaterialTypes;
import reirokusanami.modules.ModuleTools;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.materials.MaterialTypes;
import slimeknights.tconstruct.library.tinkering.Category;
import slimeknights.tconstruct.library.tinkering.PartMaterialType;
import slimeknights.tconstruct.library.tools.ProjectileLauncherNBT;

import javax.annotation.Nonnull;
import java.util.List;

public class WeaponSorceryGun extends GunCore {

    private ImmutableList<Item> BulletMatches = null;

    public WeaponSorceryGun() {
        super(PartMaterialType.handle(ModuleTools.partSmallHandle), PartMaterialType.head(ModuleTools.partSmallBarrel), GunMaterial.orb(ModuleTools.partCore));
        this.addCategory(Category.WEAPON);
        this.setUnlocalizedName("sorcerygun");
        this.setRegistryName(new ResourceLocation(TinkersGunsConstruction.MODID, this.getUnlocalizedName().substring(5)));
    }

    @Override
    public float damagePotential() {
        return 1.0f;
    }

    @Override
    public double attackSpeed() {
        return 2;
    }

    @Override
    public ProjectileLauncherNBT buildTagData(List<Material> materialList) {
        GunNBT NBT2 = new GunNBT();
        NBT2.handle(materialList.get(0).getStatsOrUnknown(MaterialTypes.HANDLE));
        NBT2.head(materialList.get(1).getStatsOrUnknown(MaterialTypes.HEAD));
        // Now for the time being [Extra]
        NBT2.orbextra(materialList.get(2).getStatsOrUnknown(GunMaterialTypes.ORB));

        return NBT2;
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        playerIn.getCooldownTracker().setCooldown(this, 10);
        return super.onItemRightClick(worldIn, playerIn, handIn);
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
    public float baseProjectileDamage() {
        return 0.8F;
    }

    @Override
    public float projectileDamageModifier() {
        return 1.0F;
    }

    @Override
    protected float baseInaccuracy() {
        return 0.002f;
    }

    protected float baseInaccuracyRange() {
        return 0.5f;
    }

    @Override
    protected float baseProjectileSpeed() {
        return 4.0f;
    }




    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> subItems) {
        if (this.isInCreativeTab(tab)) {
            addDefaultSubItems(subItems, null, null, ModuleTools.ENDERPEARL_MATERIAL);
        }
    }

    @Nonnull
    @Override
    public ItemStack getAmmoToRender(@Nonnull ItemStack weapon, EntityLivingBase player) {
        return ItemStack.EMPTY;
    }
}
