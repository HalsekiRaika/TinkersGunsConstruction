package reirokusanami;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.logging.log4j.Logger;
import reirokusanami.Entity.EntityMagicProjectile;
import reirokusanami.Entity.EntityProjectile;
import reirokusanami.client.render.EntityRenderHandler;
import reirokusanami.handler.TGCItemRegister;
import reirokusanami.handler.TGCToolRegister;
import reirokusanami.modules.ModuleItems;
import reirokusanami.modules.ModuleTools;
import reirokusanami.proxy.CommonProxy;
import reirokusanami.tools.modifier.TGCModifier;
import reirokusanami.utils.EnumEntityIDs;
import slimeknights.mantle.client.book.repository.FileRepository;
import slimeknights.tconstruct.common.ModelRegisterUtil;
import slimeknights.tconstruct.library.book.TinkerBook;

@Mod(modid = TinkersGunsConstruction.MODID, name = TinkersGunsConstruction.MODNAME, version = TinkersGunsConstruction.VERSION, dependencies = TinkersGunsConstruction.DEPENDENCIES)
public class TinkersGunsConstruction {

	public static final String MODID		= "tgc";
	public static final String MODNAME		= "TinkersGunsConstruction";
	public static final String VERSION		= "0.1.0";
	public static final String DEPENDENCIES	= "required-after:mantle;" + "required-after:tconstruct@[1.12.2-2.12.0.135,);" + "required-after:forge@[14.23.5.2836,)";
	public static final String CLIENT_PROXY	= "reirokusanami.proxy.ClientProxy";
	public static final String SERVER_PROXY	= "reirokusanami.proxy.CommonProxy";
	public static Logger       logger;

	@Instance(TinkersGunsConstruction.MODID)
	public static TinkersGunsConstruction	instance;

	@SidedProxy(clientSide = CLIENT_PROXY, serverSide = SERVER_PROXY)
	public static CommonProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		logger = event.getModLog();

		if (event.getSide().isClient()) {
			EntityRenderHandler.renderEntity();
		}
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		logger.info("Initialization [Tinkers Guns Construction]");
	}

	@EventHandler
	public void construct(FMLConstructionEvent event) {
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void register(RegistryEvent.Register<Item> event) {
		// GunsBlocks.registerItemBlocks(registry);
		ModuleItems.InitializationItems(event.getRegistry());
		TGCModifier.setupModifier();
		ModuleTools.initializationTools(event.getRegistry());
	}

	@SubscribeEvent
	public void registerEntity(RegistryEvent.Register<EntityEntry> event) {
        EntityRegistry.registerModEntity(new ResourceLocation(MODID, "bullet"), EntityProjectile.class, "bullet", EnumEntityIDs.BULLET.ordinal(), instance, 64, 1, true);
        EntityRegistry.registerModEntity(new ResourceLocation(MODID, "magicbullet"), EntityMagicProjectile.class, "magicbullet", EnumEntityIDs.MAGICBULLET.ordinal(), instance, 64, 1, true);
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void registerModels(ModelRegistryEvent event) {
		// GunsBlocks.registerModels();
		// GunsItems.registerModels();
		TGCItemRegister.getTGCItems().forEach(item -> ModelRegisterUtil.registerItemModel(item));
		TGCToolRegister.getTGCPart().forEach(part -> ModelRegisterUtil.registerPartModel(part));
		TGCToolRegister.getTGCTool().forEach(tool -> ModelRegisterUtil.registerToolModel(tool));
		TinkerBook.INSTANCE.addRepository(new FileRepository(MODID + ":book"));
	}
}
