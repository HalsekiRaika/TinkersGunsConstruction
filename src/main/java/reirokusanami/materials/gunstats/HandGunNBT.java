package reirokusanami.materials.gunstats;

import net.minecraft.nbt.NBTTagCompound;
import reirokusanami.materials.stats.GripMaterialStats;
import reirokusanami.materials.stats.MediumBarrelMaterialStats;
import slimeknights.tconstruct.library.tools.ToolNBT;

public class HandGunNBT extends ToolNBT {
    public float reloadSpeed;
    public float recoil;
    public float airTightness;
    public float accuracy;
    public float recoilCorrectionValue;
    public int   triggerWeight;

    public HandGunNBT() {
        reloadSpeed = 1f;
        recoil = 1f;
        airTightness = 1f;
        accuracy = 1f;
        recoilCorrectionValue = 0f;
        triggerWeight = 1;
    }

    public HandGunNBT(NBTTagCompound tagCompound){
        super(tagCompound);
    }

    public HandGunNBT grip(GripMaterialStats... grips){
        reloadSpeed = 0f;
        recoil = 0f;

        for (GripMaterialStats gripStat : grips) {
            if (gripStat != null) {
                reloadSpeed += gripStat.RELOAD_SPEED;
                recoil += gripStat.RECOIL;
            }
        }

        reloadSpeed /= (float) grips.length;
        recoil /= (float) grips.length;

        reloadSpeed = Math.max(0.001f, reloadSpeed);
        recoil = Math.max(0.001f, recoil);

        return this;
    }

    public HandGunNBT barrel(MediumBarrelMaterialStats... barrels) {
        airTightness = 0f;
        accuracy = 0f;
        float modifier = 0f;

        for (MediumBarrelMaterialStats barrel : barrels) {
            if (barrel != null) {
                airTightness += barrel.AIR_TIGHTNESS;
                accuracy += barrel.ACCURACY;
                modifier += barrel.MODIFIER;
            }
        }

        airTightness /= (float) barrels.length;
        accuracy /= (float) barrels.length;
        modifier /= (float) barrels.length;

        this.accuracy = Math.min(1f, Math.max(0, accuracy));
        this.durability = Math.round((float) this.durability * modifier);
        this.durability = Math.max(1, this.durability);

        return this;
    }
}
