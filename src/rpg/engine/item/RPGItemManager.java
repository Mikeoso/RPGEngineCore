package rpg.engine.item;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

import rpg.engine.player.RPGPlayer;

/**
 * @author Daniel Dieckmann aka Mike0so
 * This Class holds the logic for any attribute (have to exclude the logic of the other attributes from the listeners to make the code cleaner)
 */
public class RPGItemManager {

	public RPGItemManager() {
		
	}
	
	/**
	 * @param player The Player to update
	 * 
	 * Sets the maximum health attribute
	 */
	public void updateMaxHealth(RPGPlayer player) {
		Player playerObject = player.getPlayer();
		
		if (!playerObject.isValid()) {
			return;
		}
		
		if (playerObject.getHealth() > player.getMaxHealth()) {
			playerObject.setHealth(player.getMaxHealth());
		}
		playerObject.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(player.getMaxHealth());
	}
}
