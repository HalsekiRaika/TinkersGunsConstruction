package reirokusanami.handler;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;
import reirokusanami.TinkersGunsConstruction;
import reirokusanami.proxy.CommonProxy;
import reirokusanami.tools.Ammo.AmmoBullet;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.tools.Pattern;
import slimeknights.tconstruct.library.tools.ToolCore;
import slimeknights.tconstruct.library.tools.ToolPart;
import slimeknights.tconstruct.tools.TinkerTools;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TGCToolRegister {

	private static final List<ToolPart>	TOOL_PARTS		= new ArrayList<>();
	private static final List<ToolCore>	TOOL_CORES		= new ArrayList<>();
	private static final String			CLIENT_PROXY	= "reirokusanami.proxy.ClientProxy";
	private static final String			SERVER_PROXY	= "reirokusanami.proxy.CommonProxy";

	@SidedProxy(clientSide = CLIENT_PROXY)
	public static CommonProxy proxy;

	/**
	 * @param name     Enter the ToolPart name.
	 * @param cost     Cost required to cast ToolPart (ingot conversion)
	 * @param registry RegistryEvent.Register<Item>
	 */
	public static ToolPart registerParts(String name, int cost, IForgeRegistry<Item> registry) {
		ToolPart toolPart = new ToolPart(Material.VALUE_Ingot * cost);
		toolPart.setUnlocalizedName(name);
		toolPart.setRegistryName(new ResourceLocation(TinkersGunsConstruction.MODID, toolPart.getUnlocalizedName().substring(5)));
		registry.register(toolPart);
		TinkerRegistry.registerToolPart(toolPart);
		TOOL_PARTS.add(toolPart);

		ItemStack stencil = new ItemStack(TinkerTools.pattern);
		Pattern.setTagForPart(stencil, toolPart);
		TinkerRegistry.registerStencilTableCrafting(stencil);

		return toolPart;
	}

	public static ToolPart registerOrbParts(String name, int cost, IForgeRegistry<Item> registry) {
		ToolPart toolPart = new ToolPart(Material.VALUE_Ingot * cost);
		toolPart.setUnlocalizedName(name);
		toolPart.setRegistryName(new ResourceLocation(TinkersGunsConstruction.MODID, toolPart.getUnlocalizedName().substring(5)));
		registry.register(toolPart);
		TinkerRegistry.registerToolPart(toolPart);
		TOOL_PARTS.add(toolPart);

		TinkerRegistry.registerStencilTableCrafting(Pattern.setTagForPart(new ItemStack(TinkerTools.pattern), toolPart));

		return toolPart;
	}

	/**
	 * @param isAllowConfig Reference from TGCConfig
	 * @param toolCore      EXAMPLE: public static ToolCore tool_weaponHandgun = new weaponHandgun();
	 * @param registry      RegistryEvent.Register<Item>
	 */
	public static ToolCore registerTools(Boolean isAllowConfig, ToolCore toolCore, IForgeRegistry<Item> registry) {
		if (isAllowConfig == true) {
			registry.register(toolCore);
			TinkerRegistry.registerTool(toolCore);
			TOOL_CORES.add(toolCore);
			return toolCore;
		}
		return null;
	}

	public static AmmoBullet registerBullets(AmmoBullet toolCore, IForgeRegistry<Item> registry) {

		registry.register(toolCore);
		TinkerRegistry.registerTool(toolCore);
		TOOL_CORES.add(toolCore);
		return toolCore;
	}


	public static List<ToolPart> getTGCPart() {
		return Collections.unmodifiableList(TOOL_PARTS);
	}

	public static List<ToolCore> getTGCTool() {
		return Collections.unmodifiableList(TOOL_CORES);
	}


}
