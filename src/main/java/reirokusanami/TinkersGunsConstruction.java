package reirokusanami;

import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;
import org.apache.logging.log4j.Logger;
import reirokusanami.handler.GunsItems;


@Mod(modid = TinkersGunsConstruction.MODID, name = TinkersGunsConstruction.MODNAME, version = TinkersGunsConstruction.VERSION)
public class TinkersGunsConstruction {
    public static final String MODID = "tinkersgunsconstruction";
    public static final String MODNAME = "TinkersGunsConstruction";
    public static final String VERSION = "0.1.0";
    public static Logger LOGGER;

    @Mod.Instance(TinkersGunsConstruction.MODID)
    public static TinkersGunsConstruction INSTANCE;

    @Mod.EventHandler
    public void construct(FMLConstructionEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void registerItems(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> registry = event.getRegistry();
        //GunsBlocks.registerItemBlocks(registry);
        GunsItems.registerItems(registry);
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void registerModels(ModelRegistryEvent event) {
        //GunsBlocks.registerModels();
        GunsItems.registerModels();
    }

    @Mod.EventHandler
    public void PreInit(FMLPreInitializationEvent event) {
        LOGGER = event.getModLog();
    }

    @Mod.EventHandler
    public void Init(FMLInitializationEvent event) {
        LOGGER.info("Initialization [Tinkers Guns Construction]");
    }

}
