package reirokusanami.proxy;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import reirokusanami.TinkersGunsConstruction;
import slimeknights.tconstruct.common.ModelRegisterUtil;
import slimeknights.tconstruct.library.tools.IToolPart;
import slimeknights.tconstruct.library.tools.ToolCore;

@SideOnly(Side.CLIENT)
public class ClientProxy extends UsualProxy {
    @Override
    public void PreInit(FMLPreInitializationEvent event) {
        super.PreInit(event);
    }

    public void registerModels(ModelRegistryEvent event) {
    }

    @Override
    public void registerItemRenderer(Item _Item, int Meta, String ID) {
        ModelLoader.setCustomModelResourceLocation(_Item, Meta, new ModelResourceLocation(TinkersGunsConstruction.MODID + ":" + ID, "inventory"));
    }

    @Override
    public <T extends Item & IToolPart> void registerToolPartModel(T part) {
        ModelRegisterUtil.registerPartModel(part);
    }

    @Override
    public void registerToolModel(ToolCore toolCore) {
        ModelRegisterUtil.registerToolModel(toolCore);
    }
}
