package reirokusanami.tools;

import reirokusanami.modules.moduleTools;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.materials.MaterialTypes;
import slimeknights.tconstruct.library.tinkering.Category;
import slimeknights.tconstruct.library.tinkering.PartMaterialType;
import slimeknights.tconstruct.library.tools.TinkerToolCore;
import slimeknights.tconstruct.library.tools.ToolNBT;
import slimeknights.tconstruct.tools.TinkerTools;

import java.util.List;

public class weaponHandgun extends TinkerToolCore {
    public weaponHandgun(){
        super(PartMaterialType.handle(moduleTools.part_Grip),
                PartMaterialType.head(moduleTools.part_Muzzle),
                new PartMaterialType(moduleTools.part_Hammer),
                new PartMaterialType(moduleTools.part_barrelMedium));
        this.addCategory(Category.WEAPON);
        this.setRegistryName("weaponHandgun");
    }

    @Override
    public float damagePotential(){
        return 1.5f;
    }

    @Override
    public double attackSpeed(){
        return 2;
    }

    protected ToolNBT buildTagData(List<Material> materialList){
        ToolNBT NBT = new ToolNBT();
        NBT.head(materialList.get(1).getStatsOrUnknown(MaterialTypes.HEAD));
        NBT.handle(materialList.get(0).getStatsOrUnknown(MaterialTypes.HANDLE));
        //Now for the time being [Extra]
        NBT.extra(materialList.get(2).getStatsOrUnknown("part_hammer"));
        NBT.extra(materialList.get(3).getStatsOrUnknown("part_Slide"));

        return NBT;
    }
}
