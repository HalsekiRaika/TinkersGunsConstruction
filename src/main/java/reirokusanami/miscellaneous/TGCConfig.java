package reirokusanami.miscellaneous;

import java.util.HashMap;
import java.util.Map;

import net.minecraftforge.common.config.Config;
import reirokusanami.TinkersGunsConstruction;

@Config(modid = TinkersGunsConstruction.MODID, type = Config.Type.INSTANCE, name = "TinkersGunsConstruction")
public class TGCConfig {

	@Config.Ignore
	private static final String InfomationText = "Please rewrite to False to disable ";

	public static class MaterialCategory {
		@Config.RequiresMcRestart
		@Config.Comment({ InfomationText + "Platinum" })
		public static boolean platinum = true;
	}

	public static class WeaponCategoly {
		@Config.RequiresMcRestart
		@Config.Comment({ InfomationText + "HandGun" })
		public static boolean handGun = true;
	}

	public static boolean isAllowedMaterial(String material) {
		if (typesMaterial().containsKey(material))
		{
			return typesMaterial().get(material);
		}
		return false;
	}

	private static Map<String, Boolean> typesMaterial() {
		Map<String, Boolean> map = new HashMap<>();
		map.put("platinum", MaterialCategory.platinum);
		return map;
	}
}
