package net.insane96mcp.vulcanite.block;

import net.insane96mcp.lib.Names;
import net.insane96mcp.vulcanite.Vulcanite;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockVulcanite extends Block{

	public BlockVulcanite() {
		super(Material.IRON);
		
	}
	
	@Override
	public String getUnlocalizedName() {
		return "tile." + Vulcanite.RESOURCE_PREFIX + Names.VULCANITE_BLOCK;
	}
}
