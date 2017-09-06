package net.insane96mcp.vulcanite.item;

import java.util.List;

import javax.annotation.Nullable;

import net.insane96mcp.vulcanite.Vulcanite;
import net.insane96mcp.vulcanite.init.ModItems;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemVulcanitePickaxe extends ItemPickaxe {
	public ItemVulcanitePickaxe(String name, ToolMaterial material, CreativeTabs tab) {
		super(material);
		setRegistryName(name);
		setCreativeTab(tab);
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return "item." + Vulcanite.RESOURCE_PREFIX + net.insane96mcp.vulcanite.lib.Names.VULCANITE_PICKAXE;
	}
	
	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		for (int i = 0; i < ModItems.armorLore.length; i++) {
			tooltip.add(ModItems.armorLore[i]);
		}
	}
}
