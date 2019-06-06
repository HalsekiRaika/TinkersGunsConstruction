package reirokusanami.blocks.Relic;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class oreAdamantite extends Block {
    public oreAdamantite() {
        super(Material.ROCK);
        this.setHarvestLevel("pickaxe", 20);
        this.setHardness(8);
        this.setResistance(Float.MAX_VALUE);
        this.setRegistryName("Adamantite");
    }
}
