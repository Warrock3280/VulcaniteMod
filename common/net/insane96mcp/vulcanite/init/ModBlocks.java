package net.insane96mcp.vulcanite.init;

import net.insane96mcp.vulcanite.Vulcanite;
import net.insane96mcp.vulcanite.block.BlockVulcanite;
import net.insane96mcp.vulcanite.block.BlockVulcaniteOre;
import net.insane96mcp.vulcanite.lib.Names;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModBlocks {
	
	public static BlockVulcanite vulcaniteBlock;
	public static BlockVulcaniteOre vulcaniteOre;
	public static void Init() {
		ResourceLocation location = new ResourceLocation(Vulcanite.MOD_ID, Names.VULCANITE_BLOCK);
		vulcaniteBlock = new BlockVulcanite();
		vulcaniteBlock.setRegistryName(location);
		vulcaniteBlock.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
		vulcaniteBlock.setHardness(10.0f);
		vulcaniteBlock.setResistance(20f);
		vulcaniteBlock.setHarvestLevel("pickaxe", 3);
		GameRegistry.register(vulcaniteBlock);
		GameRegistry.register(new ItemBlock(vulcaniteBlock), location);

		location = new ResourceLocation(Vulcanite.MOD_ID, Names.VULCANITE_ORE);
		vulcaniteOre = new BlockVulcaniteOre();
		vulcaniteOre.setRegistryName(location);
		vulcaniteOre.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
		vulcaniteOre.setHardness(6.0f);
		vulcaniteOre.setResistance(10f);
		vulcaniteOre.setHarvestLevel("pickaxe", 3);
		GameRegistry.register(vulcaniteOre);
		GameRegistry.register(new ItemBlock(vulcaniteOre), location);
		
		GameRegistry.registerWorldGenerator(new OreGeneration(), 0);
	}
	
	public static void PostInit() {

		GameRegistry.addRecipe(new ItemStack(vulcaniteBlock, 1), "xxx", "xxx", "xxx", 'x', ModItems.vulcaniteItem);
	}
	
	@SideOnly(Side.CLIENT)
	public static void InitClient(ItemModelMesher mesher) {
		Item item = Item.getItemFromBlock(vulcaniteBlock);
		ModelResourceLocation model = new ModelResourceLocation(Vulcanite.RESOURCE_PREFIX + Names.VULCANITE_BLOCK);
		ModelLoader.registerItemVariants(item, model);
		mesher.register(item, 0, model);
		
		item = Item.getItemFromBlock(vulcaniteOre);
		model = new ModelResourceLocation(Vulcanite.RESOURCE_PREFIX + Names.VULCANITE_ORE);
		ModelLoader.registerItemVariants(item, model);
		mesher.register(item, 0, model);
	}
}
