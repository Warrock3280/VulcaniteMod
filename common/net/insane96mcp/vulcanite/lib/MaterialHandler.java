package net.insane96mcp.vulcanite.lib;

import java.awt.datatransfer.FlavorTable;

import net.insane96mcp.vulcanite.Config;
import net.insane96mcp.vulcanite.Vulcanite;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class MaterialHandler {
	public static int harvestLevel = Config.LoadIntProperty("tools_and_weapon", "harvest_level", "Harvest level for Vulcanite Tools and Sword\n(0 for wood, 1 for stone, 2 for iron and 3 for diamond)\n", 3);
	public static int maxUses = Config.LoadIntProperty("tools_and_weapon", "max_uses", "Durability for Vulcanite Tools and Sword", 859);
	public static float efficency = (float) Config.LoadDoubleProperty("tools_and_weapon", "efficency", "Efficency for Vulcanite Tools and Sword", 7d);
	public static float damage = (float) Config.LoadDoubleProperty("tools_and_weapon", "damage", "Base Damage for Vulcanite Tools and Sword", 2.5d);
	public static int enchantability = Config.LoadIntProperty("tools_and_weapon", "enchantability", "Enchantability for Vulcanite Tools and Sword", 16);
	
	public static ToolMaterial Vulcanite = EnumHelper.addToolMaterial("vulcanite", harvestLevel, maxUses, efficency, damage, enchantability);
	
	public static int baseDurability = Config.LoadIntProperty("armor", "base_durability", "Base durability for Vulcanite Armor\n(this value is multiplied by [11, 16, 15, 13] respectively from helmet to boots)\n", 24);
	public static int[] armorPoints = Config.LoadIntListProperty("armor", "armor_points", "Armor Points given by Vulcanite Armor", new int[] {3, 5, 4, 2});
	public static int armorEnchantability = Config.LoadIntProperty("armor", "enchantability", "Enchantability for Vulcanite Armor", 17);
	public static float toughness = (float) Config.LoadDoubleProperty("armor", "toughness", "Toughness for Vulcanite Armor", 0d);
	
	public static ArmorMaterial vulcaniteArmorMaterial = EnumHelper.addArmorMaterial("vulcanite", "vulcanite:vulcanite_armor", baseDurability, armorPoints, armorEnchantability, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, toughness);
	
	/*
	 * Bonus Damage Per Fire Aspect Level
	 */
	public static float bonusDamagePerFALevel = (float)Config.LoadDoubleProperty("tools_and_weapon", "bonus_damage", "Bonus damage % dealt to Fire Immune mobs per Fire Aspect Level", 15d);
	
	public static float hotSourcedamageReduction = (float)Config.LoadDoubleProperty("armor", "damage_reduction", "Percentage damage reduction from Fire sources with Vulcanite Armor", 90d);

	public static int flintVulcaniteSecondsOnFire = Config.LoadIntProperty("flint_and_vulcanite", "fire_seconds", "The number of seconds an entity will be set on fire when right clicked with Flint and Vulcanite", 3);
	public static int flintVulcaniteMaxUses = Config.LoadIntProperty("flint_and_vulcanite", "max_uses", "The durability of Flint and Vulcanite", 222);
}
