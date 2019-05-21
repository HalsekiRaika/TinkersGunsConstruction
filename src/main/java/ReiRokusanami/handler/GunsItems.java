package reirokusanami.handler;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.NonNullList;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;
import reirokusanami.TinkersGunsConstruction;

public class GunsItems {
    private static final NonNullList<Item> ITEMS = NonNullList.create();


    public static void register(IForgeRegistry<Item> registry, Item item) {

        if (item instanceof ItemBlock && item.getRegistryName() == null) {
            item.setRegistryName(((ItemBlock) item).getBlock().getRegistryName());
        }

        registry.register(item);
    }

    public static void registerItems(IForgeRegistry<Item> registry) {
        //test
        //register(registry, japariman.setRegistryName("japariman"));
    }

    @SideOnly(Side.CLIENT)
    public static void registerModels() {
        //test
        //registerModel(japariman, "japariman");
    }

    @SideOnly(Side.CLIENT)
    public static void registerModel(Item item, String modelName) {
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(TinkersGunsConstruction.MODID + ":" + modelName, "inventory"));
    }

    @SideOnly(Side.CLIENT)
    public static void registerModel(Item item) {
        registerModel(item, item.getRegistryName().getResourcePath());
    }
}
