package rpg.engine.item;

import org.bukkit.inventory.ItemStack;

/**
 * @author Daniel Dieckmann aka Mike0so
 * 
 */
public class ItemUtils {
	
	/**
	 * @param stack The item to check
	 * @return True if the item is not null and item has meta data otherwise false
	 */
	public static boolean hasMeta(ItemStack stack) {
		if (stack != null && stack.getItemMeta() != null) {
			return true;
		}
		return false;
	}
	
	/**
	 * @param stack The item to check
	 * @return True if the item has a lore otherwise false
	 */
	public static boolean hasLore(ItemStack stack) {
		if (hasMeta(stack) && stack.getItemMeta().hasLore()) {
			return true;
		}
		return false;
	}
}
