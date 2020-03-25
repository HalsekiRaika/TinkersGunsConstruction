package reirokusanami.materials.stats;

import reirokusanami.materials.TGCMaterialTypes;
import slimeknights.tconstruct.library.materials.AbstractMaterialStats;

import java.util.List;

public class MediumBarrelMaterialStats extends AbstractMaterialStats {
    public static final String LOC_AIR_TIGHTNESS = "stat.mediumbarrel.airtightness.name";
    public static final String LOC_ACCURACY      = "stat.mediumbarrel.accuracy.name";
    public static final String LOC_DURABILITY    = "stat.mediumbarrel.durability.name";

    public MediumBarrelMaterialStats(){
        super(TGCMaterialTypes.MEDIUMBARREL);
    }

    @Override
    public List<String> getLocalizedInfo() {
        return null;
    }

    @Override
    public List<String> getLocalizedDesc() {
        return null;
    }
}
