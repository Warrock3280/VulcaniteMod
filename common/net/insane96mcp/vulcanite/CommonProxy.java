package net.insane96mcp.vulcanite;

import net.insane96mcp.vulcanite.init.ModBlocks;
import net.insane96mcp.vulcanite.init.ModItems;
import net.insane96mcp.vulcanite.lib.CustomEventHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {
	public void PreInit(FMLPreInitializationEvent event) {
		Vulcanite.config = new Configuration(event.getSuggestedConfigurationFile());
		Config.syncConfig();
		
		ModItems.Init();
		ModBlocks.Init();
	}
	
	public void Init(FMLInitializationEvent event) {
		ModItems.PostInit();
		ModBlocks.PostInit();
		MinecraftForge.EVENT_BUS.register(CustomEventHandler.class);
	}
	
	public void PostInit(FMLPostInitializationEvent event) {
		if(Vulcanite.config.hasChanged()) Vulcanite.config.save();
	}
}
