package net.insane96mcp.vulcanite;

import net.insane96mcp.vulcanite.init.ModBlocks;
import net.insane96mcp.vulcanite.init.ModItems;
import net.insane96mcp.vulcanite.lib.CustomEventHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {
	public void PreInit(FMLPreInitializationEvent event) {
		ModItems.Init();
		ModBlocks.Init();
		MinecraftForge.EVENT_BUS.register(CustomEventHandler.class);
	}
	
	public void Init(FMLInitializationEvent event) {
		ModItems.PostInit();
		ModBlocks.PostInit();
	}
	
	public void PostInit(FMLPostInitializationEvent event) {
		
	}
}
