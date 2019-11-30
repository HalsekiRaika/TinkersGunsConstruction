package reirokusanami.modules;

import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;
import reirokusanami.handler.TGCItemRegister;

public class ModuleItems {
    public static Item itemMagazine;

    public static void InitializationItems(IForgeRegistry<Item> event) {
        itemMagazine = TGCItemRegister.RegisterItems("magazine", 16, event);
    }
}
