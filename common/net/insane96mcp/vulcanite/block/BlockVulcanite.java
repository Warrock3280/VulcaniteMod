package net.insane96mcp.vulcanite.block;

import net.insane96mcp.vulcanite.Vulcanite;
import net.insane96mcp.vulcanite.lib.Names;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockVulcanite extends Block{

	public BlockVulcanite() {
		super(Material.IRON);
		
	}
	
	@Override
	public String getUnlocalizedName() {
		return "tile." + Vulcanite.RESOURCE_PREFIX + Names.VULCANITE_BLOCK;
	}
	
	@Override
	public boolean isBeaconBase(IBlockAccess worldObj, BlockPos pos, BlockPos beacon) {
		return true;
	}
	
	@Override
	public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
		entityIn.setFire(3);
	}
}
