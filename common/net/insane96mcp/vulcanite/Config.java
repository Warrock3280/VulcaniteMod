package net.insane96mcp.vulcanite;

import net.minecraftforge.common.config.Property;

public class Config {

	public static int LoadIntProperty(String category, String key, String description, int defaultValue) {
		Property property = Vulcanite.config.get(category, key, defaultValue);
		property.setComment(description + " (default: " + defaultValue + ")");
		
		return property.getInt();
	}
	public static int[] LoadIntListProperty(String category, String key, String description, int[] defaultValue) {
		Property property = Vulcanite.config.get(category, key, defaultValue);
		description += " (default: [";
		for (int i = 0; i < property.getIntList().length; i++) {
			description += property.getIntList()[i];
			if (i != property.getIntList().length - 1)
				description += ", ";
		}
		description += "])";
		
		property.setComment(description);
		
		return property.getIntList();
	}
	public static double LoadDoubleProperty(String category, String key, String description, double defaultValue) {
		Property property = Vulcanite.config.get(category, key, defaultValue);
		property.setComment(description + " (default: " + defaultValue + ")");
		
		return property.getDouble();
	}
	public static String LoadStringProperty(String category, String key, String description, String defaultValue) {
		Property property = Vulcanite.config.get(category, key, defaultValue);
		property.setComment(description + "(default: " + defaultValue + ")");
		
		return property.getString();
	}
	public static String[] LoadStringListProperty(String category, String key, String description, String[] defaultValue) {
		Property property = Vulcanite.config.get(category, key, defaultValue);
		property.setComment(description + " (default: " + defaultValue + ")");
		
		return property.getStringList();
	}
	public static boolean LoadBoolProperty(String category, String key, String description, boolean defaultValue) {
		Property property = Vulcanite.config.get(category, key, defaultValue);
		property.setComment(description + " (default: " + defaultValue + ")");
		
		return property.getBoolean();
	}
	
	public static void syncConfig() {
		try {
			Vulcanite.config.load();
			
		}
		catch (Exception e) {
			
		}
	}
}
