package net.insane96mcp.vulcanite.lib;

import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.common.util.EnumHelper;

public class MaterialHandler {
	public static ToolMaterial Vulcanite = EnumHelper.addToolMaterial("vulcanite", 4, 859, 7f, 2.5f, 16);
	public static ArmorMaterial vulcaniteArmorMaterial = EnumHelper.addArmorMaterial("vulcanite", "vulcanite:vulcanite_armor", 24, new int[]{3, 6, 5, 2}, 17, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 0f);
}
