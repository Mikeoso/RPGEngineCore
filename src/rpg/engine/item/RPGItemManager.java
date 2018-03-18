package rpg.engine.item;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

import rpg.engine.player.RPGPlayer;

/**
 * @author Daniel Dieckmann aka Mike0so This Class holds the logic for any
 *         attribute (have to exclude the logic of the other attributes from the
 *         listeners to make the code cleaner)
 */
public class RPGItemManager {

	public RPGItemManager() {

	}

	/**
	 * @param player
	 *            The Player to update
	 * 
	 *            Sets the maximum health attribute
	 */
	private void updateMaxHealth(RPGPlayer player) {
		Player playerObject = player.getPlayer();

		double maxHealth = player.getMaxHealth();
		if (!playerObject.isValid()) {
			return;
		}

		if (playerObject.getHealth() > maxHealth) {
			playerObject.setHealth(maxHealth);
		}
		if (player.getMaxHealth() > 1024) {
			maxHealth = 1024;
		}
		playerObject.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(maxHealth);
	}

	private void updateAttackSpeed(RPGPlayer player) {
		Player playerObject = player.getPlayer();
		double attackSpeedAttr = 1024.0;
		if (!playerObject.isValid()) {
			return;
		}
		double baseAttackSpeed = player.getBasicAttackSpeed();
		double itemAttackSpeed = player.getItemAttributes().getItemAttackSpeed();
		
		double bonus = baseAttackSpeed * itemAttackSpeed / 100.0D;
		double maxAttr = (baseAttackSpeed + bonus);

		if (maxAttr > attackSpeedAttr) {
			maxAttr = attackSpeedAttr;
		}
		player.getPlayer().getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(maxAttr);
	}
	
	private void updateMovementSpeed(RPGPlayer player) {
		Player playerObject = player.getPlayer();
		
		if (!playerObject.isValid()) {
			return;
		}
		
		double baseMovementSpeed = player.getBaseMovementSpeed();
		double itemMovementSpeed = player.getItemAttributes().getItemMovementSpeed();
		double bonus = baseMovementSpeed * itemMovementSpeed / 100.0D;
		
		if (((baseMovementSpeed + itemMovementSpeed) + bonus) > 1024) {
			player.getPlayer().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(1024);
		}
		player.getPlayer().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue((baseMovementSpeed + bonus));
	}
	
	public void updateStats(RPGPlayer player) {
		updateMovementSpeed(player);
		updateAttackSpeed(player);
		updateMaxHealth(player);
	}
}
