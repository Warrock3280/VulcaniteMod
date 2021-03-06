package net.insane96mcp.vulcanite.item;

import java.util.List;

import net.insane96mcp.vulcanite.Vulcanite;
import net.insane96mcp.vulcanite.init.ModItems;
import net.insane96mcp.vulcanite.lib.Names;
import net.insane96mcp.vulcanite.lib.Properties;
import net.insane96mcp.vulcanite.lib.Tooltips;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

public class ItemVulcaniteSword extends ItemSword{
	public ItemVulcaniteSword(String name, ToolMaterial material, CreativeTabs tab) {
		super(material);
		setRegistryName(name);
		setCreativeTab(tab);
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return "item." + Vulcanite.RESOURCE_PREFIX + Names.VULCANITE_SWORD;
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced) {
		if (GuiScreen.isShiftKeyDown()) {
			tooltip.add(I18n.format(Tooltips.Weapon.adv_moreDamage, Properties.Tools.bonusDamagePerFALevel));
		}
		else {
			tooltip.add(I18n.format(Tooltips.Weapon.base_moreDamage));
			tooltip.add(I18n.format(Tooltips.General.shiftForMore));
		}
	}
	
	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		return ItemStack.areItemsEqualIgnoreDurability(repair, new ItemStack(ModItems.vulcaniteItem)) ? true : super.getIsRepairable(toRepair, repair);
	}
}
