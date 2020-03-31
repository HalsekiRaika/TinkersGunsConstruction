package reirokusanami.materials.gunstats;

import net.minecraft.nbt.NBTTagCompound;
import slimeknights.tconstruct.library.tools.ToolNBT;

@Deprecated
public class GeneralGunsNBT extends ToolNBT {
    // -- Base Stats
    public float reloadTime;
    public float range;
    public float bonusDamage;
    public float recoil;

    // -- Weapon Category
    public boolean isHandGun;
    public boolean isAssaultRifle;
    public boolean isShotGun;
    public boolean isSniperRifle;
    public boolean isDesignatedMarksmanRifle;
    public boolean isSubMachineGun;
    public boolean isLightMachineGun;
    public boolean isAntiMaterialRifle;
    public boolean isGrenadeLauncher;

    // -- Extra
    public boolean isAkimbo;

    public GeneralGunsNBT(){
        this.reloadTime = 3.0f;
        this.range = 1.0f;
        this.bonusDamage = 0.0f;
        this.recoil = 1.0f;
        this.isHandGun = false;
        this.isAssaultRifle = false;
        this.isShotGun = false;
        this.isSniperRifle = false;
        this.isDesignatedMarksmanRifle = false;
        this.isSubMachineGun = false;
        this.isLightMachineGun = false;
        this.isAntiMaterialRifle = false;
        this.isGrenadeLauncher = false;
        this.isAkimbo = false;
    }

    public GeneralGunsNBT(NBTTagCompound tagCompound){
        super(tagCompound);
    }

    public GeneralGunsNBT grip(){
        return this;
    }
}
