package reirokusanami.proxy;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;
import reirokusanami.tools.modifier.TGCModifier;

@Mod.EventBusSubscriber
public class CommonProxy {
    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        IForgeRegistry registry = event.getRegistry();
        TGCModifier.setupModifier();
    }
}
