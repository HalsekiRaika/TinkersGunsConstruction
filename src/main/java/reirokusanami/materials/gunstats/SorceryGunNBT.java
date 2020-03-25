package reirokusanami.materials.gunstats;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import reirokusanami.materials.stats.OrbMaterialStats;
import slimeknights.tconstruct.library.tools.ProjectileLauncherNBT;
import slimeknights.tconstruct.library.tools.ToolNBT;
import slimeknights.tconstruct.library.utils.TagUtil;

public class SorceryGunNBT extends ProjectileLauncherNBT {
    public SorceryGunNBT() {

    }

    public SorceryGunNBT(NBTTagCompound tag) {
        super(tag);
    }

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

    public static SorceryGunNBT from(ItemStack itemStack) {
        return new SorceryGunNBT(TagUtil.getToolTag(itemStack));
    }
}
