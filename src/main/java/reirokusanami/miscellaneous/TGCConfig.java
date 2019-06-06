package reirokusanami.miscellaneous;

import net.minecraftforge.common.config.Config;
import reirokusanami.TinkersGunsConstruction;

import java.util.HashMap;
import java.util.Map;

@Config(modid = TinkersGunsConstruction.MODID, type = Config.Type.INSTANCE, name = "TinkersGunsConstruction")
public class TGCConfig {
    @Config.Ignore
    private static final String InfomationText = "Please rewrite to False to disable ";

    public static class MaterialCategory {
        @Config.RequiresMcRestart
        @Config.Comment({InfomationText + "Platinum"})
        public static boolean Platinum = true;
    }
    public static class WeaponCategoly {
        @Config.RequiresMcRestart
        @Config.Comment({InfomationText + "HandGun"})
        public static boolean HandGun = true;
    }

    public static boolean isAllowedMaterial(String Material){
        if(TypesMaterial().containsKey(Material)){
            return TypesMaterial().get(Material);
        }
        return false;
    }

    private static Map<String, Boolean> TypesMaterial(){
        Map<String, Boolean> _map = new HashMap<>();
        _map.put("platinum", MaterialCategory.Platinum);
        return _map;
    }
}
