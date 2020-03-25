package reirokusanami.materials.stats;

import com.google.common.collect.Lists;
import net.minecraft.util.text.TextFormatting;
import reirokusanami.materials.TGCMaterialTypes;
import slimeknights.tconstruct.library.Util;
import slimeknights.tconstruct.library.client.CustomFontColor;
import slimeknights.tconstruct.library.materials.AbstractMaterialStats;

import java.util.List;

public class OrbMaterialStats extends AbstractMaterialStats {

    public final static String LOC_Durability = "stat.orb.durability.name";
    public final static String LOC_Attack = "stat.orb.attack.name";

    public final static String LOC_DurabilityDesc = "stat.orb.durability.desc";
    public final static String LOC_AttackDesc = "stat.orb.attack.desc";

    public final static String COLOR_Durability = CustomFontColor.valueToColorCode(1f);
    public final static String COLOR_Attack = CustomFontColor.encodeColor(215, 100, 100);

    public final int durability; // usually between 1 and 1000
    public final float attack; // usually between 0 and 10 (in 1/2 hearts, so divide by 2 for damage in hearts)

    public OrbMaterialStats(int durability, float attack) {
        super(TGCMaterialTypes.ORB);
        this.durability = durability;
        this.attack = attack;
    }

    @Override
    public List<String> getLocalizedInfo() {
        List<String> info = Lists.newArrayList();

        info.add(formatDurability(durability));
        info.add(formatAttack(attack));

        return info;
    }

    public static String formatDurability(int durability) {
        return formatNumber(LOC_Durability, COLOR_Durability, durability);
    }

    public static String formatDurability(int durability, int ref) {
        return String.format("%s: %s",
                Util.translate(LOC_Durability),
                CustomFontColor.formatPartialAmount(durability, ref))
                + TextFormatting.RESET;
    }

    public static String formatAttack(float attack) {
        return formatNumber(LOC_Attack, COLOR_Attack, attack);
    }

    @Override
    public List<String> getLocalizedDesc() {
        List<String> info = Lists.newArrayList();

        info.add(Util.translate(LOC_DurabilityDesc));
        info.add(Util.translate(LOC_AttackDesc));

        return info;
    }
}