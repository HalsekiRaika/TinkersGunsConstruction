package reirokusanami.materials.stats;

import com.google.common.collect.ImmutableList;
import reirokusanami.materials.TGCMaterialTypes;
import slimeknights.tconstruct.library.Util;
import slimeknights.tconstruct.library.client.CustomFontColor;
import slimeknights.tconstruct.library.materials.AbstractMaterialStats;

import java.util.List;

public class MuzzleMaterialStats extends AbstractMaterialStats {
    public static final String LOC_RECOIL_CORRECTION_VALUE = "stat.generalgungrip.recoilcorrectionvalue.name";
    public static final String COLOR_RECOIL_CORRECTION_VALUE = CustomFontColor.encodeColor(123, 199, 133);

    public final float RECOIL_CORRECTION_VALUE;

    public MuzzleMaterialStats(float recoilCorrectionValue){
        super(TGCMaterialTypes.MUZZLE);
        this.RECOIL_CORRECTION_VALUE = recoilCorrectionValue;
    }

    public static String formatRecoilCorrectionValue(float recoilCorrectionValue){
        return formatNumber(LOC_RECOIL_CORRECTION_VALUE, COLOR_RECOIL_CORRECTION_VALUE, recoilCorrectionValue);
    }

    @Override
    public List<String> getLocalizedInfo() {
        return ImmutableList.of(formatRecoilCorrectionValue(RECOIL_CORRECTION_VALUE));
    }

    @Override
    public List<String> getLocalizedDesc() {
        return ImmutableList.of(Util.translate(LOC_RECOIL_CORRECTION_VALUE));
    }
}
