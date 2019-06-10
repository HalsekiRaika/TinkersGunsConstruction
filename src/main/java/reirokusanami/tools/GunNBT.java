package reirokusanami.tools;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import reirokusanami.materials.OrbMaterialStats;
import slimeknights.tconstruct.library.tools.ProjectileLauncherNBT;
import slimeknights.tconstruct.library.tools.ToolNBT;
import slimeknights.tconstruct.library.utils.TagUtil;

public class GunNBT extends ProjectileLauncherNBT {
    public GunNBT() {

    }

    public GunNBT(NBTTagCompound tag) {
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

    public static GunNBT from(ItemStack itemStack) {
        return new GunNBT(TagUtil.getToolTag(itemStack));
    }
}
