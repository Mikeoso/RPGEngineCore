package rpg.engine.item;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import rpg.engine.RPGEngine;

public class RPGItemFileManager {

	private File itemsFolder;
	private HashMap<String, ItemStack> customFiles;
	
	public RPGItemFileManager() {
		itemsFolder = new File(RPGEngine.getInstance().getDataFolder() + "\\items");
		createFolderIfNotExists();
		
		customFiles = new HashMap<String, ItemStack>();
		loadExistingFiles();
	}
	
	private void createFolderIfNotExists() {
		if (!itemsFolder.exists()) {
			try {
				itemsFolder.mkdirs();
			} catch (Exception e) {
				RPGEngine.getInstance().getLogger().warning("[ArathosRPG] Error while create ItemDb!");
				return;
			}
		}
	}
	
	public void loadExistingFiles() {
		for (File file : itemsFolder.listFiles()) {
			if (file.isFile()) {
				try {
					YamlConfiguration itemFileConfig = YamlConfiguration.loadConfiguration(file);
					ItemStack loadedItem = itemFileConfig.getItemStack("Item");
					if (customFiles.containsKey(file.getName())) {
						customFiles.remove(file.getName());
					}
					customFiles.put(file.getName(), loadedItem);
				} catch (Exception e) {
					RPGEngine.getInstance().getLogger().warning("Error while loading Item in File: " + file.getName());
					return;
				}
			}
		}
	}
	
	public HashMap<String, ItemStack> getDb() {
		return customFiles;
	}
	
	public ItemStack loadItemByName(String itemname) {
		File loadItemFile = new File(itemsFolder + "\\" + itemname + ".yml");
		
		if (!loadItemFile.exists()) {
			return null;
		}
		
		YamlConfiguration itemFileConfig = YamlConfiguration.loadConfiguration(loadItemFile);
		ItemStack stack = itemFileConfig.getItemStack("Item");
		
		return stack;
	}
	public void saveItemToFile(ItemStack stack) {
		File newItemFile = new File(itemsFolder + "\\" + stack.getItemMeta().getDisplayName() + ".yml");
		
		if (!newItemFile.exists()) {
			try {
				newItemFile.createNewFile();
				
				YamlConfiguration configuration = new YamlConfiguration();
				configuration.set("Item", stack);
				configuration.save(newItemFile);
				customFiles.put(newItemFile.getName(), stack);
			} catch (Exception e) {
				RPGEngine.getInstance().getLogger().warning("[ArathosRPG] Error while saving file!");
				return;
			}
		} else {
			YamlConfiguration configuration = YamlConfiguration.loadConfiguration(newItemFile);
			configuration.set("Item", stack);
			try {
				configuration.save(newItemFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return;
			}
			RPGEngine.getInstance().getLogger().warning(newItemFile.getName());
			customFiles.put(newItemFile.getName(), stack);
		}
		RPGEngine.getInstance().getLogger().info("Successfuly saved item to file");
		return;
	}
}
