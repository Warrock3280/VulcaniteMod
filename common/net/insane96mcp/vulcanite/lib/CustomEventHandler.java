package net.insane96mcp.vulcanite.lib;

import javax.swing.text.html.HTML.Tag;

import net.insane96mcp.vulcanite.init.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import scala.collection.generic.BitOperations.Int;

public class CustomEventHandler {
	
	@SubscribeEvent
	public static void LivingHurtEvent(LivingHurtEvent event) {
		if (event.getEntityLiving() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.getEntityLiving();
			DamageSource source = event.getSource();
			DamageSource[] validSources = new DamageSource[] {
				DamageSource.IN_FIRE, 
				DamageSource.ON_FIRE, 
				DamageSource.HOT_FLOOR, 
				DamageSource.LAVA
			};
			
			for (DamageSource damageSource : validSources) {
				if (source == damageSource) {
					float amount = event.getAmount();
					
					ItemStack[] armorList = new ItemStack[] {new ItemStack(ModItems.vulcaniteHelmetItem), new ItemStack(ModItems.vulcaniteChestplateItem), new ItemStack(ModItems.vulcaniteLeggingsItem), new ItemStack(ModItems.vulcaniteBootsItem)};
				    int gearCounter = 0;
				    Iterable<ItemStack> playerArmor = player.getArmorInventoryList();
				    for (ItemStack armorPiece : playerArmor) {
				    	for (ItemStack armorL : armorList) {
					        if (ItemStack.areItemsEqualIgnoreDurability(armorPiece, armorL)) {
								gearCounter++;
								break;
							}
						}
					}
				    if(gearCounter >= 1) {
				    	float reductionPerPiece = 22.5f;
				    	float percentageReduction = gearCounter * reductionPerPiece;
				    	amount -= (amount / 100 * percentageReduction);
				        event.setAmount(amount);
				    }
				}
			}
		}
	}
	
	
	private static ItemStack[] fireTools = new ItemStack[] {
		new ItemStack(ModItems.vulcaniteAxeItem),
		new ItemStack(ModItems.vulcaniteHoeItem),
		new ItemStack(ModItems.vulcanitePickaxeItem),
		new ItemStack(ModItems.vulcaniteShovelItem),
		new ItemStack(ModItems.vulcaniteSwordItem)
	};
	
	@SubscribeEvent
	public static void AttackEntityEvent(AttackEntityEvent event) {
		for (ItemStack itemStack : fireTools) {
			EntityPlayer player = event.getEntityPlayer();
			ItemStack heldItem = player.getHeldItemMainhand();
			if (ItemStack.areItemsEqualIgnoreDurability(heldItem, itemStack)) {
				Entity target = event.getTarget();
				target.setFire(3);
			}
		}
	}
}
