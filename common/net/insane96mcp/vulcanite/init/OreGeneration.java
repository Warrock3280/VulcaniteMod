package net.insane96mcp.vulcanite.init;

import java.util.Random;

import net.insane96mcp.vulcanite.Config;
import net.insane96mcp.vulcanite.lib.Stats;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

public class OreGeneration implements IWorldGenerator {

	private final WorldGenMinable worldGenMinableNether;
	
	public OreGeneration() {
		worldGenMinableNether = new WorldGenMinable(ModBlocks.vulcaniteOre.getDefaultState(), Stats.OreGeneration.orePerVein, BlockMatcher.forBlock(Blocks.NETHERRACK));
	}
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {
		BlockPos chunkPos = new BlockPos(chunkX * 16, 0, chunkZ * 16);

		if (world.provider.getDimension() == -1) {
			for (int i = 0; i < Stats.OreGeneration.veinPerChunk; i++) {
				worldGenMinableNether.generate(world, random, chunkPos.add(random.nextInt(16), random.nextInt(Stats.OreGeneration.maxY - Stats.OreGeneration.minY) + Stats.OreGeneration.minY, random.nextInt(16)));
			}
		}
	}

	
}
