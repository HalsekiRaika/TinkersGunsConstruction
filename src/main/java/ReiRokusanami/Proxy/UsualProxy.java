package ReiRokusanami.Proxy;

import ReiRokusanami.TinkersGunsConstruction;
import ReiRokusanami.events.TGCEventRegistry;
import net.minecraft.item.*;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.LoaderState;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;
import slimeknights.tconstruct.common.ModelRegisterUtil;
import slimeknights.tconstruct.library.tools.IToolPart;
import slimeknights.tconstruct.library.tools.ToolCore;

public class UsualProxy {
    public void PreInit(FMLPreInitializationEvent event){
        MinecraftForge.EVENT_BUS.register(new TGCEventRegistry());
        //This Area After Create TGCEntities.init
    }

    public void registerModels() {
        if(Loader.instance().hasReachedState(LoaderState.INITIALIZATION)){
            TinkersGunsConstruction.LOGGER.error("Proxy.registerModels has to be called during preInit.");
        }
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> Registry = event.getRegistry();
    }

    public <T extends Item & IToolPart> void registerToolPartModel(T part) {
    }

    public void registerToolModel(ToolCore toolCore) {
    }
}
