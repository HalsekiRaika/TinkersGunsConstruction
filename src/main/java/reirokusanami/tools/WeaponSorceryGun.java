package reirokusanami.tools;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import reirokusanami.TinkersGunsConstruction;
import reirokusanami.modules.ModuleTools;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.materials.MaterialTypes;
import slimeknights.tconstruct.library.tinkering.Category;
import slimeknights.tconstruct.library.tinkering.PartMaterialType;
import slimeknights.tconstruct.library.tools.TinkerToolCore;
import slimeknights.tconstruct.library.tools.ToolNBT;

import java.util.List;

public class WeaponSorceryGun extends TinkerToolCore {

    protected final static String HAMMER = "part_hammer";
    protected final static String SLIDE = "part_slide";

    public WeaponSorceryGun() {
        super(PartMaterialType.handle(ModuleTools.partSmallHandle), PartMaterialType.head(ModuleTools.partCore), new PartMaterialType(ModuleTools.partSmallBarrel));
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
        ToolNBT NBT = new ToolNBT();
        NBT.handle(materialList.get(0).getStatsOrUnknown(MaterialTypes.HANDLE));
        NBT.head(materialList.get(1).getStatsOrUnknown(MaterialTypes.HEAD));
        // Now for the time being [Extra]
        NBT.extra(materialList.get(2).getStatsOrUnknown(SLIDE));

        return NBT;
    }
}
