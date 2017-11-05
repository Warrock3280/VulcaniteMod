package net.insane96mcp.vulcanite.item.material;

import net.insane96mcp.vulcanite.lib.Stats;
import net.insane96mcp.vulcanite.lib.Stats.Armor;
import net.insane96mcp.vulcanite.lib.Stats.Tools;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class ModMaterial {
	public static ToolMaterial tool = EnumHelper.addToolMaterial("vulcanite", Stats.Tools.harvestLevel, Stats.Tools.maxUses, Stats.Tools.efficency, Stats.Tools.baseDamage, Stats.Tools.enchantability);
	public static ArmorMaterial armor = EnumHelper.addArmorMaterial("vulcanite", "vulcanite:vulcanite_armor", Stats.Armor.baseDurability, Stats.Armor.armorPoints, Stats.Armor.enchantability, SoundEvents.ITEM_ARMOR_EQUIP_IRON, Stats.Armor.toughness);
}