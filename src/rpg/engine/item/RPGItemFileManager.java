package rpg.engine.item;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import rpg.engine.RPGEngine;

/**
 * @author Daniel Dieckmann aka Mike0so
 * This class is used as Item Repository
 */
public class RPGItemFileManager {

	private File itemsFolder;
	private HashMap<String, ItemStack> customFiles;
	
	public RPGItemFileManager() {
		itemsFolder = new File(RPGEngine.getInstance().getDataFolder() + "\\items");
		createFolderIfNotExists();
		
		customFiles = new HashMap<String, ItemStack>();
		loadExistingFiles();
	}
	
	
	/**
	 * Creates the Items folder withing the Plugin/Folder
	 */
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
	
	/**
	 * Loads all Itemfiles as ItemStack into the memory
	 */
	public void loadExistingFiles() {
		customFiles.clear();
		for (File file : itemsFolder.listFiles()) {
			if (file.isFile()) {
				try {
					YamlConfiguration itemFileConfig = YamlConfiguration.loadConfiguration(file);
					ItemStack loadedItem = itemFileConfig.getItemStack("Item");
					customFiles.put(file.getName(), loadedItem);
				} catch (Exception e) {
					RPGEngine.getInstance().getLogger().warning("Error while loading Item in File: " + file.getName());
					return;
				}
			}
		}
	}
	
	public void renameFile(String oldName, String newName) {
		if (oldName == null) {
			RPGEngine.getInstance().getLogger().warning("Error while renaming file");
			return;
		}
		
		File oldItem = new File(itemsFolder + "\\" + oldName + ".yml");
		
		if ((!oldItem.exists())) {
			return;
		}
		if (newName == null) {
			return;
		}
		
		File newFile = new File(itemsFolder + "\\" + newName + ".yml");
		ItemStack stack = null;
		
		oldItem.renameTo(newFile);
		loadExistingFiles();
	}
	
	public HashMap<String, ItemStack> getDb() {
		return customFiles;
	}
	
	/**
	 * @param Itemname filename of the item
	 * @return The loaded Item
	 */
	public ItemStack loadItemByName(String itemname) {
		if (customFiles.containsKey(itemname)) {
			return customFiles.get(itemname);
		}
		File loadItemFile = new File(itemsFolder + "\\" + itemname + ".yml");		
		if (!loadItemFile.exists()) {
			return null;
		}
		
		YamlConfiguration itemFileConfig = YamlConfiguration.loadConfiguration(loadItemFile);
		ItemStack stack = itemFileConfig.getItemStack("Item");
		return stack;
	}
	
	
	/**
	 * @param stack The item to save as file
	 */
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
