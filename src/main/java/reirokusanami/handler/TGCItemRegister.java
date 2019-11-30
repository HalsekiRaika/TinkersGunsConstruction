package reirokusanami.handler;


import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistry;
import reirokusanami.TinkersGunsConstruction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TGCItemRegister {
    private static final List<Item> ITEMS = new ArrayList<>();

    public static Item RegisterItems(String name, int stackNum, IForgeRegistry<Item> event) {
        Item item = new Item();
        item.setUnlocalizedName(name);
        item.setMaxStackSize(stackNum);
        item.setRegistryName(new ResourceLocation(TinkersGunsConstruction.MODID, item.getUnlocalizedName().substring(5)));
        event.register(item);
        ITEMS.add(item);

        return item;
    }

    public static List<Item> getTGCItems() { return Collections.unmodifiableList(ITEMS); }
}
