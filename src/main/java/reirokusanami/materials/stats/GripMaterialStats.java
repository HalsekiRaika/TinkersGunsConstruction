package reirokusanami.materials.stats;

import com.google.common.collect.ImmutableList;
import reirokusanami.materials.TGCMaterialTypes;
import slimeknights.tconstruct.library.Util;
import slimeknights.tconstruct.library.client.CustomFontColor;
import slimeknights.tconstruct.library.materials.AbstractMaterialStats;

import java.util.List;

public class GripMaterialStats extends AbstractMaterialStats {
    public static final String LOC_RELOAD_SPEED = "stat.generalgungrip.reloadspeed.name";
    public static final String LOC_RECOIL       = "stat.generalgungrip.recoil.name";
    public static final String COLOR_RELOAD_SPEED = CustomFontColor.encodeColor(238,130,238);
    public static final String COLOR_RECOIL       = CustomFontColor.encodeColor(30,144,255);

    public final float RELOAD_SPEED;
    public final float RECOIL;

    public GripMaterialStats(float reloadSpeed, float recoil) {
        super(TGCMaterialTypes.GRIP);
        this.RELOAD_SPEED = reloadSpeed;
        this.RECOIL = recoil;
    }

    public static String formatReloadSpeed(float reloadSpeed){
        return formatNumber(LOC_RELOAD_SPEED, COLOR_RELOAD_SPEED, reloadSpeed);
    }

    public static String formatRecoil(float recoil){
        return formatNumber(LOC_RECOIL, COLOR_RECOIL, recoil);
    }

    @Override
    public List<String> getLocalizedInfo() {
        return ImmutableList.of(
                formatReloadSpeed(1f/RELOAD_SPEED),
                formatRecoil(RECOIL)
        );
    }

    @Override
    public List<String> getLocalizedDesc() {
        return ImmutableList.of(
                Util.translate(LOC_RELOAD_SPEED),
                Util.translate(LOC_RECOIL)
        );
    }
}
