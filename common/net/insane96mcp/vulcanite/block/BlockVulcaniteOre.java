package net.insane96mcp.vulcanite.block;

import net.insane96mcp.lib.Names;
import net.insane96mcp.vulcanite.Vulcanite;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockVulcaniteOre extends Block{
	
	public BlockVulcaniteOre() {
		super(Material.ROCK);
		
	}
	
	@Override
	public String getUnlocalizedName() {
		return "tile." + Vulcanite.RESOURCE_PREFIX + Names.VULCANITE_ORE;
	}
}
