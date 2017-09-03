package net.insane96mcp.vulcanite.lib;

import com.google.common.eventbus.Subscribe;

import ibxm.Player;
import net.insane96mcp.vulcanite.init.ModItems;
import net.minecraft.client.audio.SoundRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.client.gui.ForgeGuiFactory.ForgeConfigGui.ModIDEntry;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
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
	
	private static ItemStack flintAndVulcanite = new ItemStack(ModItems.flintAndVulcaniteItem);
	
	@SubscribeEvent
	public static void PlayerEntityInteract(PlayerInteractEvent.EntityInteract event) {
		EntityPlayer player = event.getEntityPlayer();
		ItemStack mainHand = player.getHeldItemMainhand();
		ItemStack offHand = player.getHeldItemOffhand();
		if (ItemStack.areItemsEqualIgnoreDurability(mainHand, flintAndVulcanite) || ItemStack.areItemsEqualIgnoreDurability(offHand, flintAndVulcanite)) {
			Entity target = event.getTarget();
			EntityLivingBase entityLivingBase;
			if (!(target instanceof EntityLivingBase))
				return;
			
			entityLivingBase = (EntityLivingBase)target;
			
			if (entityLivingBase.isImmuneToFire())
				return;
			
			entityLivingBase.setFire(3);
			if (ItemStack.areItemsEqualIgnoreDurability(mainHand, flintAndVulcanite)) {
				player.swingArm(EnumHand.MAIN_HAND);
			}
			else { 
				player.swingArm(EnumHand.OFF_HAND);
			}
			ResourceLocation sound = new ResourceLocation("minecraft","item.flintandsteel.use");
			SoundEvent soundEvent = new SoundEvent(sound);
			
			event.getWorld().playSound(player, entityLivingBase.getPosition(), soundEvent, SoundCategory.PLAYERS, 1.0f, event.getWorld().rand.nextFloat() * 0.4F + 0.8F);
				
		}
	}
}
