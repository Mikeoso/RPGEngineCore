package rpg.engine.item;

import org.bukkit.inventory.ItemStack;

public class ItemUtils {

	public static boolean hasMeta(ItemStack stack) {
		if (stack != null && stack.getItemMeta() != null) {
			return true;
		}
		return false;
	}
	
	public static boolean hasLore(ItemStack stack) {
		if (hasMeta(stack) && stack.getItemMeta().hasLore()) {
			return true;
		}
		return false;
	}
}
