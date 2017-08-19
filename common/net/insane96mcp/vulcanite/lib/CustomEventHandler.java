package net.insane96mcp.vulcanite.lib;

import net.insane96mcp.vulcanite.init.ModItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
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
					
					ItemStack[] armorList = new ItemStack[] {new ItemStack(ModItems.vulcaniteHelmetItem, 1), new ItemStack(ModItems.vulcaniteChestplateItem, 1), new ItemStack(ModItems.vulcaniteLeggingsItem, 1), new ItemStack(ModItems.vulcaniteBootsItem, 1)};
				    int gearCounter = 0;
			        player.sendMessage(new TextComponentString("Gear counter " + Integer.toString(gearCounter)));
				    Iterable<ItemStack> playerArmor = player.getArmorInventoryList();
				    for (ItemStack armorPiece : playerArmor) {
				    	for (ItemStack armorL : armorList) {
					        player.sendMessage(new TextComponentString("Checking " + armorPiece.getItem() + " " + armorL.getItem()));
					        if (ItemStack.areItemsEqualIgnoreDurability(armorPiece, armorL)) {
								gearCounter++;
						        player.sendMessage(new TextComponentString("ArmorPiece found"));
								break;
							}
						}
					}
			        player.sendMessage(new TextComponentString("Gear counter " + Integer.toString(gearCounter)));
				    if(gearCounter >= 1) {
				    	float reductionPerPiece = 20f;
				    	float percentageReduction = gearCounter * reductionPerPiece;
				    	amount -= (amount / 100 * percentageReduction);
				        event.setAmount(amount);
				    }
				    player.sendMessage(new TextComponentString("Damage amount " + Float.toString(amount)));
				}
			}
		}
	}
}
