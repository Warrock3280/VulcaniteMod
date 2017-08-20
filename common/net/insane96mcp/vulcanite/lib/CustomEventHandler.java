package net.insane96mcp.vulcanite.lib;

import net.insane96mcp.vulcanite.init.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import scala.reflect.internal.Trees.New;

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
					
			ItemStack[] armorList = new ItemStack[] {
				new ItemStack(ModItems.vulcaniteHelmetItem), 
				new ItemStack(ModItems.vulcaniteChestplateItem), 
				new ItemStack(ModItems.vulcaniteLeggingsItem), 
				new ItemStack(ModItems.vulcaniteBootsItem)
			};
			
			float[] materialPerPiece = new float[] { 5, 8, 7, 4 };
			
			for (DamageSource damageSource : validSources) {
				if (source == damageSource) {
					float amount = event.getAmount();
					
				    int materialsUsed = 0;
				    Iterable<ItemStack> playerArmor = player.getArmorInventoryList();
				    for (ItemStack armorPiece : playerArmor) {
				    	for (int i = 0; i < armorList.length; i++) {
					        if (ItemStack.areItemsEqualIgnoreDurability(armorPiece, armorList[i])) {
								materialsUsed += materialPerPiece[i];
								break;
							}
						}
					}
				    
				    if(materialsUsed >= 1) {
				    	float maxReduction = 0.9f;
				    	float reductionPerMaterial = maxReduction / 24f;
				    	float percentageReduction = reductionPerMaterial * materialsUsed;
				    	amount = amount * (1f - percentageReduction);
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
