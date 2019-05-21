package reirokusanami;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = TinkersGunsConstruction.MODID, name = TinkersGunsConstruction.MODNAME, version = TinkersGunsConstruction.VERSION)
public class TinkersGunsConstruction {
    public static final String MODID = "tinkersgunsconstruction";
    public static final String MODNAME = "Tinkers Guns Construction";
    public static final String VERSION = "0.1.0";
    public static Logger LOGGER;

    @Mod.Instance(TinkersGunsConstruction.MODID)
    public static TinkersGunsConstruction INSTANCE;

    @Mod.EventHandler
    public void PreInit(FMLPreInitializationEvent event) {
        LOGGER = event.getModLog();
    }

    @Mod.EventHandler
    public void Init(FMLInitializationEvent event) {
        LOGGER.info("Initialization [Tinkers Guns Construction]");
    }

}
