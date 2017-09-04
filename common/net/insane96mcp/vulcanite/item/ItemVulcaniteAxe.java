package net.insane96mcp.vulcanite.item;

import java.util.List;

import net.insane96mcp.vulcanite.Vulcanite;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;

public class ItemVulcaniteAxe extends ItemAxe{
	public ItemVulcaniteAxe(String name, ToolMaterial material, CreativeTabs tab) {
		super(material, 7f, -3.1f);
		setRegistryName(name);
		setCreativeTab(tab);
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return "item." + Vulcanite.RESOURCE_PREFIX + net.insane96mcp.vulcanite.lib.Names.VULCANITE_AXE;
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced) {
		tooltip.add(TextFormatting.GOLD + "Deals " + TextFormatting.RED + "20%" + TextFormatting.GOLD + " more damage to");
		tooltip.add(TextFormatting.GOLD + "fire immune mobs");
	}
}
