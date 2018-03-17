package rpg.engine.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLevelChangeEvent;

import rpg.engine.RPGEngine;
import rpg.engine.player.RPGPlayer;

public class PlayerLevelChanceListener implements Listener {

	@EventHandler
	public void onLevelChange(PlayerLevelChangeEvent e) {
		RPGPlayer player = new RPGPlayer(e.getPlayer());
		RPGEngine.getInstance().getItemManager().updateMaxHealth(player);
	}
}
