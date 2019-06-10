package reirokusanami.tools;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import reirokusanami.TinkersGunsConstruction;
import reirokusanami.materials.GunMaterial;
import reirokusanami.materials.GunMaterialTypes;
import reirokusanami.modules.ModuleTools;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.materials.MaterialTypes;
import slimeknights.tconstruct.library.tinkering.Category;
import slimeknights.tconstruct.library.tinkering.PartMaterialType;
import slimeknights.tconstruct.library.tools.TinkerToolCore;
import slimeknights.tconstruct.library.tools.ToolNBT;

import java.util.List;

public class WeaponSorceryGun extends TinkerToolCore {


    public WeaponSorceryGun() {
        super(PartMaterialType.handle(ModuleTools.partSmallHandle), PartMaterialType.head(ModuleTools.partSmallBarrel), GunMaterial.orb(ModuleTools.partCore));
        this.addCategory(Category.WEAPON);
        this.setUnlocalizedName("sorcerygun");
        this.setRegistryName(new ResourceLocation(TinkersGunsConstruction.MODID, this.getUnlocalizedName().substring(5)));
    }

    @Override
    public float damagePotential() {
        return 2.0f;
    }

    @Override
    public double attackSpeed() {
        return 2;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand hand) {
        return null;
    }

    protected ToolNBT buildTagData(List<Material> materialList) {
        GunNBT NBT2 = new GunNBT();
        NBT2.handle(materialList.get(0).getStatsOrUnknown(MaterialTypes.HANDLE));
        NBT2.head(materialList.get(1).getStatsOrUnknown(MaterialTypes.HEAD));
        // Now for the time being [Extra]
        NBT2.orbextra(materialList.get(2).getStatsOrUnknown(GunMaterialTypes.ORB));

        return NBT2;
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> subItems) {
        if (this.isInCreativeTab(tab)) {
            addDefaultSubItems(subItems, null, null, ModuleTools.ENDERPEARL_MATERIAL);
        }
    }
}
