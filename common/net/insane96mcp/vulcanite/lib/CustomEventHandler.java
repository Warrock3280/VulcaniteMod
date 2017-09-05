package net.insane96mcp.vulcanite.lib;

import net.insane96mcp.vulcanite.Vulcanite;
import net.insane96mcp.vulcanite.init.ModBlocks;
import net.insane96mcp.vulcanite.init.ModItems;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagFloat;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = Vulcanite.MOD_ID)
public class CustomEventHandler {
	
	@SubscribeEvent
	public static void LivingHurtEvent(LivingHurtEvent event) {
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
		    	float maxReduction = 0.9f;
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
	
	private static final float bonusDamage = 0.2f;
	
	@SubscribeEvent
	public static void AttackEntityEvent(AttackEntityEvent event) {
		for (ItemStack itemStack : fireTools) {
			EntityPlayer player = event.getEntityPlayer();
			ItemStack heldItem = player.getHeldItemMainhand();
			if (!ItemStack.areItemsEqualIgnoreDurability(heldItem, itemStack))
				continue;
			
			Entity target = event.getTarget();
			if (!(target instanceof EntityLivingBase))
				return;
			
			EntityLivingBase entity = (EntityLivingBase)target;
			if (!entity.isImmuneToFire())
				return;
			
			float currentHealth = entity.getHealth();
			if (!entity.getEntityData().hasKey("Health"))
				return;
			
			float entityHealth = ((NBTTagFloat) entity.getEntityData().getTag("Health")).getInt();;
			float damageDealth = entityHealth - currentHealth;
			float bonusDamageDealth = damageDealth * bonusDamage;
			entity.attackEntityFrom(DamageSource.causePlayerDamage(player), bonusDamageDealth);
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
			if (entityLivingBase instanceof EntityCreeper) {
				NBTTagCompound ignited = new NBTTagCompound();
				ignited.setByte("ignited", (byte)1);
				entityLivingBase.readEntityFromNBT(ignited);
			}
			if (ItemStack.areItemsEqualIgnoreDurability(mainHand, flintAndVulcanite)) {
				player.swingArm(EnumHand.MAIN_HAND);
			}
			else { 
				player.swingArm(EnumHand.OFF_HAND);
			}
			
			event.getWorld().playSound(player, entityLivingBase.getPosition(), SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.PLAYERS, 1.0f, event.getWorld().rand.nextFloat() * 0.4F + 0.8F);
				
		}
	}
	
	
}
