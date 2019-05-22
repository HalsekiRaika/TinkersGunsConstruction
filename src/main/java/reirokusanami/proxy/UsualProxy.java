package reirokusanami.proxy;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.LoaderState;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;
import slimeknights.tconstruct.library.tools.IToolPart;
import slimeknights.tconstruct.library.tools.ToolCore;

public class UsualProxy {
    public void PreInit(FMLPreInitializationEvent event){
    }

    public void registerModels() {
        if(Loader.instance().hasReachedState(LoaderState.INITIALIZATION)){
            reirokusanami.TinkersGunsConstruction.LOGGER.error("proxy.registerModels has to be called during preInit.");
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
