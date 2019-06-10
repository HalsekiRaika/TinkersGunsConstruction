package reirokusanami.tools;

import reirokusanami.materials.OrbMaterialStats;
import slimeknights.tconstruct.library.tools.ToolNBT;

public class GunNBT extends ToolNBT {
    public ToolNBT orbextra(OrbMaterialStats... extras) {
        int dur = 0;
        int attack = 0;
        for (OrbMaterialStats extra : extras) {
            if (extra != null) {
                dur += extra.durability;
                attack += extra.attack;
            }
        }
        this.durability += Math.round((float) dur / (float) extras.length);

        return this;
    }
}
