package net.insane96mcp.vulcanite.init;

import java.util.Random;

import net.insane96mcp.vulcanite.Config;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

public class NetherGenOres implements IWorldGenerator {

	private final WorldGenMinable worldGenMinableNether;
	
	public static int blockCount = Config.LoadIntProperty("ore_generation", "block_per_vein", "Number of blocks generated per vein", 3);
	public static int perChunk = Config.LoadIntProperty("ore_generation", "vein_per_chunk", "Number of veins that have to try to spawn per chunk", 40);
	public static int minY = Config.LoadIntProperty("ore_generation", "min_Y", "The minimum height (Y) to try to generate Veins", 0);
	public static int maxY = Config.LoadIntProperty("ore_generation", "max_Y", "The maximum height (Y) to try to generate Veins", 32);
	
	public NetherGenOres() {
		worldGenMinableNether = new WorldGenMinable(ModBlocks.vulcaniteOre.getDefaultState(), NetherGenOres.blockCount, BlockMatcher.forBlock(Blocks.NETHERRACK));
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,
			net.minecraft.world.gen.IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		BlockPos chunkPos = new BlockPos(chunkX * 16, 0, chunkZ * 16);

		if (world.provider.getDimension() == -1) {
			for (int i = 0; i < NetherGenOres.perChunk; i++) {
				worldGenMinableNether.generate(world, random, chunkPos.add(random.nextInt(16), random.nextInt(NetherGenOres.maxY - NetherGenOres.minY) + NetherGenOres.minY, random.nextInt(16)));
			}
		}
	}

	
}
