package reirokusanami.materials.stats;

import com.google.common.collect.ImmutableList;
import reirokusanami.materials.TGCMaterialTypes;
import slimeknights.tconstruct.library.Util;
import slimeknights.tconstruct.library.client.CustomFontColor;
import slimeknights.tconstruct.library.materials.AbstractMaterialStats;

import java.util.List;

public class HammerMaterialStats extends AbstractMaterialStats {
    public static final String LOC_TRIGGER_WEIGHT = "stat.generalgungrip.triggerweight.name";
    public static final String COLOR_TRIGGER_WEIGHT = CustomFontColor.encodeColor(218, 96, 114);

    public final int TRIGGER_WEIGHT;

    public HammerMaterialStats(int triggerWeight){
        super(TGCMaterialTypes.HAMMER);
        this.TRIGGER_WEIGHT = triggerWeight;
    }

    public static String formatTriggerWeight(int triggerWeight) {
        return formatNumber(LOC_TRIGGER_WEIGHT, COLOR_TRIGGER_WEIGHT, triggerWeight);
    }

    @Override
    public List<String> getLocalizedInfo() {
        return ImmutableList.of(formatTriggerWeight(TRIGGER_WEIGHT));
    }

    @Override
    public List<String> getLocalizedDesc() {
        return ImmutableList.of(Util.translate(LOC_TRIGGER_WEIGHT));
    }
}
