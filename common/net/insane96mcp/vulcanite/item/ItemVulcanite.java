package net.insane96mcp.vulcanite.item;

import net.insane96mcp.vulcanite.Vulcanite;
import net.insane96mcp.vulcanite.item.*;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class ItemVulcanite extends Item {
	
	public ItemVulcanite(String name, CreativeTabs tab) {
		setRegistryName(name);
		setCreativeTab(tab);
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return "item." + Vulcanite.RESOURCE_PREFIX + net.insane96mcp.lib.Names.VULCANITE_ITEM;
	}
}
