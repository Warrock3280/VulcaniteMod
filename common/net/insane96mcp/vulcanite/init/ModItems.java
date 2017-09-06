package net.insane96mcp.vulcanite.init;

import net.insane96mcp.vulcanite.Vulcanite;
import net.insane96mcp.vulcanite.item.ItemFlintAndVulcanite;
import net.insane96mcp.vulcanite.item.ItemVulcanite;
import net.insane96mcp.vulcanite.item.ItemVulcaniteArmor;
import net.insane96mcp.vulcanite.item.ItemVulcaniteAxe;
import net.insane96mcp.vulcanite.item.ItemVulcaniteHoe;
import net.insane96mcp.vulcanite.item.ItemVulcanitePickaxe;
import net.insane96mcp.vulcanite.item.ItemVulcaniteShovel;
import net.insane96mcp.vulcanite.item.ItemVulcaniteSword;
import net.insane96mcp.vulcanite.lib.MaterialHandler;
import net.insane96mcp.vulcanite.lib.Names;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModItems {
	
	public static String[] itemsLore = new String[] {
		TextFormatting.GOLD + "Deals " + TextFormatting.RED + "15%" + TextFormatting.GOLD + " more damage per level",
		TextFormatting.GOLD + "of Fire Aspect to fire immune mobs"
	};
	
	public static String[] armorLore = new String[] {
		TextFormatting.GOLD + "Reduces damage from hot sources",
		TextFormatting.GOLD + "Full Armor Set damage reduction: " + TextFormatting.RED + "90%"
	};
	
	public static ItemVulcanite vulcaniteItem;
	
	public static ItemVulcanitePickaxe vulcanitePickaxeItem;
	public static ItemVulcaniteShovel vulcaniteShovelItem;
	public static ItemVulcaniteAxe vulcaniteAxeItem;
	public static ItemVulcaniteHoe vulcaniteHoeItem;
	public static ItemVulcaniteSword vulcaniteSwordItem;
	
	public static ItemFlintAndVulcanite flintAndVulcaniteItem;

	public static ItemVulcaniteArmor vulcaniteHelmetItem;
	public static ItemVulcaniteArmor vulcaniteChestplateItem;
	public static ItemVulcaniteArmor vulcaniteLeggingsItem;
	public static ItemVulcaniteArmor vulcaniteBootsItem;
	
	public static void Init() {
		vulcaniteItem = new ItemVulcanite(Names.VULCANITE_ITEM, CreativeTabs.MATERIALS);
		GameRegistry.register(vulcaniteItem);

		vulcanitePickaxeItem = new ItemVulcanitePickaxe(Names.VULCANITE_PICKAXE, MaterialHandler.Vulcanite, CreativeTabs.TOOLS);
		GameRegistry.register(vulcanitePickaxeItem);

		vulcaniteShovelItem = new ItemVulcaniteShovel(Names.VULCANITE_SHOVEL, MaterialHandler.Vulcanite, CreativeTabs.TOOLS);
		GameRegistry.register(vulcaniteShovelItem);

		vulcaniteAxeItem = new ItemVulcaniteAxe(Names.VULCANITE_AXE, MaterialHandler.Vulcanite, CreativeTabs.TOOLS);
		GameRegistry.register(vulcaniteAxeItem);
		
		vulcaniteHoeItem = new ItemVulcaniteHoe(Names.VULCANITE_HOE, MaterialHandler.Vulcanite, CreativeTabs.TOOLS);
		GameRegistry.register(vulcaniteHoeItem);
		
		vulcaniteSwordItem = new ItemVulcaniteSword(Names.VULCANITE_SWORD, MaterialHandler.Vulcanite, CreativeTabs.COMBAT);
		GameRegistry.register(vulcaniteSwordItem);
		
		flintAndVulcaniteItem = new ItemFlintAndVulcanite(Names.FLINT_AND_VULCANITE, MaterialHandler.Vulcanite, CreativeTabs.TOOLS);
		GameRegistry.register(flintAndVulcaniteItem);

		vulcaniteHelmetItem = new ItemVulcaniteArmor(Names.VULCANITE_HELMET, MaterialHandler.vulcaniteArmorMaterial, 0, EntityEquipmentSlot.HEAD);
		GameRegistry.register(vulcaniteHelmetItem);

		vulcaniteChestplateItem = new ItemVulcaniteArmor(Names.VULCANITE_CHESTPLATE, MaterialHandler.vulcaniteArmorMaterial, 0, EntityEquipmentSlot.CHEST);
		GameRegistry.register(vulcaniteChestplateItem);

		vulcaniteLeggingsItem = new ItemVulcaniteArmor(Names.VULCANITE_LEGGINGS, MaterialHandler.vulcaniteArmorMaterial, 1, EntityEquipmentSlot.LEGS);
		GameRegistry.register(vulcaniteLeggingsItem);

		vulcaniteBootsItem = new ItemVulcaniteArmor(Names.VULCANITE_BOOTS, MaterialHandler.vulcaniteArmorMaterial, 0, EntityEquipmentSlot.FEET);
		GameRegistry.register(vulcaniteBootsItem);
	}
	
	public static void PostInit() {
		GameRegistry.addShapelessRecipe(new ItemStack(vulcaniteItem, 9), ModBlocks.vulcaniteBlock);
		GameRegistry.addSmelting(ModBlocks.vulcaniteOre, new ItemStack(vulcaniteItem), 3.0f);

		GameRegistry.addRecipe(new ItemStack(vulcanitePickaxeItem), "vvv", " s ", " s ", 'v', vulcaniteItem, 's', Items.STICK);
		GameRegistry.addRecipe(new ItemStack(vulcaniteShovelItem), " v ", " s ", " s ", 'v', vulcaniteItem, 's', Items.STICK);
		GameRegistry.addRecipe(new ItemStack(vulcaniteAxeItem), " vv", " sv", " s ", 'v', vulcaniteItem, 's', Items.STICK);
		GameRegistry.addRecipe(new ItemStack(vulcaniteHoeItem), " vv", " s ", " s ", 'v', vulcaniteItem, 's', Items.STICK);
		GameRegistry.addRecipe(new ItemStack(vulcaniteSwordItem), " v ", " v ", " s ", 'v', vulcaniteItem, 's', Items.STICK);
		
		GameRegistry.addShapelessRecipe(new ItemStack(flintAndVulcaniteItem), vulcaniteItem, Items.FLINT);

		GameRegistry.addRecipe(new ItemStack(vulcaniteHelmetItem), "vvv", "v v", 'v', vulcaniteItem);
		GameRegistry.addRecipe(new ItemStack(vulcaniteChestplateItem), "v v", "vvv", "vvv", 'v', vulcaniteItem);
		GameRegistry.addRecipe(new ItemStack(vulcaniteLeggingsItem), "vvv", "v v", "v v", 'v', vulcaniteItem);
		GameRegistry.addRecipe(new ItemStack(vulcaniteBootsItem), "v v", "v v", 'v', vulcaniteItem);
	}
	
	@SideOnly(Side.CLIENT)
	public static void InitClient(ItemModelMesher mesher) {
		ModelResourceLocation modelResourceLocation = new ModelResourceLocation(Vulcanite.RESOURCE_PREFIX + Names.VULCANITE_ITEM);
		ModelLoader.registerItemVariants(vulcaniteItem, modelResourceLocation);
		mesher.register(vulcaniteItem, 0, modelResourceLocation);
		
		modelResourceLocation = new ModelResourceLocation(Vulcanite.RESOURCE_PREFIX + Names.VULCANITE_PICKAXE);
		ModelLoader.registerItemVariants(vulcanitePickaxeItem, modelResourceLocation);
		mesher.register(vulcanitePickaxeItem, 0, modelResourceLocation);
		
		modelResourceLocation = new ModelResourceLocation(Vulcanite.RESOURCE_PREFIX + Names.VULCANITE_SHOVEL);
		ModelLoader.registerItemVariants(vulcaniteShovelItem, modelResourceLocation);
		mesher.register(vulcaniteShovelItem, 0, modelResourceLocation);
		
		modelResourceLocation = new ModelResourceLocation(Vulcanite.RESOURCE_PREFIX + Names.VULCANITE_AXE);
		ModelLoader.registerItemVariants(vulcaniteAxeItem, modelResourceLocation);
		mesher.register(vulcaniteAxeItem, 0, modelResourceLocation);
		
		modelResourceLocation = new ModelResourceLocation(Vulcanite.RESOURCE_PREFIX + Names.VULCANITE_HOE);
		ModelLoader.registerItemVariants(vulcaniteHoeItem, modelResourceLocation);
		mesher.register(vulcaniteHoeItem, 0, modelResourceLocation);
		
		modelResourceLocation = new ModelResourceLocation(Vulcanite.RESOURCE_PREFIX + Names.VULCANITE_SWORD);
		ModelLoader.registerItemVariants(vulcaniteSwordItem, modelResourceLocation);
		mesher.register(vulcaniteSwordItem, 0, modelResourceLocation);
		
		modelResourceLocation = new ModelResourceLocation(Vulcanite.RESOURCE_PREFIX + Names.FLINT_AND_VULCANITE);
		ModelLoader.registerItemVariants(flintAndVulcaniteItem, modelResourceLocation);
		mesher.register(flintAndVulcaniteItem, 0, modelResourceLocation);

		
		modelResourceLocation = new ModelResourceLocation(Vulcanite.RESOURCE_PREFIX + Names.VULCANITE_HELMET);
		ModelLoader.registerItemVariants(vulcaniteHelmetItem, modelResourceLocation);
		mesher.register(vulcaniteHelmetItem, 0, modelResourceLocation);
		
		modelResourceLocation = new ModelResourceLocation(Vulcanite.RESOURCE_PREFIX + Names.VULCANITE_CHESTPLATE);
		ModelLoader.registerItemVariants(vulcaniteChestplateItem, modelResourceLocation);
		mesher.register(vulcaniteChestplateItem, 0, modelResourceLocation);
		
		modelResourceLocation = new ModelResourceLocation(Vulcanite.RESOURCE_PREFIX + Names.VULCANITE_LEGGINGS);
		ModelLoader.registerItemVariants(vulcaniteLeggingsItem, modelResourceLocation);
		mesher.register(vulcaniteLeggingsItem, 0, modelResourceLocation);
		
		modelResourceLocation = new ModelResourceLocation(Vulcanite.RESOURCE_PREFIX + Names.VULCANITE_BOOTS);
		ModelLoader.registerItemVariants(vulcaniteBootsItem, modelResourceLocation);
		mesher.register(vulcaniteBootsItem, 0, modelResourceLocation);
	}
}
