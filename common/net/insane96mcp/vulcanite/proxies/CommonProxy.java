package net.insane96mcp.vulcanite.proxies;

import net.insane96mcp.vulcanite.Config;
import net.insane96mcp.vulcanite.events.LivingHurt;
import net.insane96mcp.vulcanite.events.PlayerBreakSpeed;
import net.insane96mcp.vulcanite.events.PlayerEntityInteract;
import net.insane96mcp.vulcanite.init.ModBlocks;
import net.insane96mcp.vulcanite.init.ModItems;
import net.insane96mcp.vulcanite.lib.Stats;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {
	public void PreInit(FMLPreInitializationEvent event) {
		Config.config = new Configuration(event.getSuggestedConfigurationFile());
		Config.SyncConfig();
		Stats.Init();
		
		ModItems.Init();
		ModBlocks.Init();
	}
	
	public void Init(FMLInitializationEvent event) {
		ModItems.PostInit();
		ModBlocks.PostInit();
		MinecraftForge.EVENT_BUS.register(LivingHurt.class);
		MinecraftForge.EVENT_BUS.register(PlayerBreakSpeed.class);
		MinecraftForge.EVENT_BUS.register(PlayerEntityInteract.class);
	}
	
	public void PostInit(FMLPostInitializationEvent event) {
		Config.SaveConfig();
	}
}
