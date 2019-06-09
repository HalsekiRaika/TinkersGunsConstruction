package reirokusanami.blocks.Relic;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockOre extends Block {

	public BlockOre(String name) {
		super(Material.ROCK);
		this.setUnlocalizedName(name);

		// this.setHarvestLevel("pickaxe", 20);
		// this.setHardness(8);
		// this.setResistance(Float.MAX_VALUE);
	}
}
