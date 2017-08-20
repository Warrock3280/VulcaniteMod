package net.insane96mcp.vulcanite.item;

import java.util.List;

import net.insane96mcp.vulcanite.Vulcanite;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
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
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced) {
		tooltip.add(TextFormatting.GOLD + "Sets attacked mobs on fire for " + TextFormatting.RED + "3 seconds");
	}
}
