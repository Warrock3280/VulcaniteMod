package net.insane96mcp.vulcanite.lib;

import net.insane96mcp.vulcanite.init.ModItems;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Enchantments;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CustomEventHandler {
	
	@SubscribeEvent
	public static void LivingHurtEvent(LivingHurtEvent event) {
		OnPlayerHurt(event);
		OnPlayerDamageEntity(event);
	}
	
	public static void OnPlayerHurt(LivingHurtEvent event) {
		if (!(event.getEntityLiving() instanceof EntityPlayer))
			return;
		
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
			if (source != damageSource) 
				continue;
			
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
		    
		    if (materialsUsed >= 1) {
		    	float maxReduction = Stats.Armor.hotSourceDamageReduction / 100f;
		    	float reductionPerMaterial = maxReduction / 24f;
		    	float percentageReduction = reductionPerMaterial * materialsUsed;
		    	amount = amount * (1f - percentageReduction);
		        event.setAmount(amount);
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
	
	public static void OnPlayerDamageEntity(LivingHurtEvent event) {
		if (event.getSource().damageType != "player")
			return;
		
		for (ItemStack itemStack : fireTools) {
			EntityPlayerMP player = (EntityPlayerMP) event.getSource().getEntity();
			ItemStack heldItem = player.getHeldItemMainhand();
			if (!ItemStack.areItemsEqualIgnoreDurability(heldItem, itemStack))
				continue;
			
			NBTTagList enchantments = heldItem.getEnchantmentTagList();
			
			if (enchantments == null)
				return;
			
			int fireAspectLevel = 0;
			for (int i = 0; i < enchantments.tagCount(); i++) {
				if (enchantments.getCompoundTagAt(i).getShort("id") == Enchantment.getEnchantmentID(Enchantments.FIRE_ASPECT))
					fireAspectLevel = enchantments.getCompoundTagAt(i).getShort("lvl");
			}
			
			if (fireAspectLevel == 0)
				return;
			
			Entity target = event.getEntity();
			if (!(target instanceof EntityLivingBase))
				return;
			
			EntityLivingBase entity = (EntityLivingBase)target;
			if (!entity.isImmuneToFire())
				return;
			
			float damageDealth = event.getAmount();
			float bonusDamageDealth = damageDealth * ((Stats.Tools.bonusDamagePerFALevel / 100f) * fireAspectLevel);
			
			event.setAmount(damageDealth + bonusDamageDealth);
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
			
			entityLivingBase.setFire(Stats.FlintAndVulcanite.secondsOnFire);
			if (entityLivingBase instanceof EntityCreeper) {
				NBTTagCompound ignited = new NBTTagCompound();
				ignited.setByte("ignited", (byte)1);
				entityLivingBase.readEntityFromNBT(ignited);
			}
			if (ItemStack.areItemsEqualIgnoreDurability(mainHand, flintAndVulcanite)) {
				player.swingArm(EnumHand.MAIN_HAND);
				mainHand.damageItem(Stats.FlintAndVulcanite.damageOnUse, player);
			}
			else { 
				player.swingArm(EnumHand.OFF_HAND);
				offHand.damageItem(Stats.FlintAndVulcanite.damageOnUse, player);
			}
			
			event.getWorld().playSound(player, entityLivingBase.getPosition(), SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.PLAYERS, 1.0f, event.getWorld().rand.nextFloat() * 0.4F + 0.8F);
				
		}
	}
	
	
}
