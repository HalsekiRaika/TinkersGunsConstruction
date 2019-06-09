package reirokusanami.handler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.registries.IForgeRegistry;
import reirokusanami.TinkersGunsConstruction;
import reirokusanami.proxy.UsualProxy;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.tinkering.PartMaterialType;
import slimeknights.tconstruct.library.tools.IToolPart;
import slimeknights.tconstruct.library.tools.Pattern;
import slimeknights.tconstruct.library.tools.ToolCore;
import slimeknights.tconstruct.library.tools.ToolPart;
import slimeknights.tconstruct.tools.TinkerTools;

public class TGCRegister {

	private static final List<ToolPart>	TOOL_PARTS		= new ArrayList<>();
	private static final List<ToolCore>	TOOL_CORES		= new ArrayList<>();
	private static final String			CLIENT_PROXY	= "reirokusanami.proxy.ClientProxy";
	private static final String			SERVER_PROXY	= "reirokusanami.proxy.UsualProxy";

	@SidedProxy(clientSide = CLIENT_PROXY)
	public static UsualProxy	proxy;

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

	public static void registerToolBuilding() {
		for (final IToolPart part : getTGCPart()) {
			for (final ToolCore tool : getTGCTool()) {
				for (final PartMaterialType types : tool.getRequiredComponents()) {
					if (types.getPossibleParts().contains(part)) {
						TinkerRegistry.registerStencilTableCrafting(Pattern.setTagForPart(new ItemStack(TinkerTools.pattern), (Item) part));
					}
				}
			}
		}
	}

	public static List<ToolPart> getTGCPart() {
		return Collections.unmodifiableList(TOOL_PARTS);
	}

	public static List<ToolCore> getTGCTool() {
		return Collections.unmodifiableList(TOOL_CORES);
	}

}
