package rpg.engine.item;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

import rpg.engine.player.RPGPlayer;

public class RPGItemManager {

	public RPGItemManager() {
		
	}
	
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
