package rpg.engine.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import rpg.engine.RPGEngine;
import rpg.engine.player.RPGPlayer;

public class PlayerJoinListener implements Listener {

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		RPGPlayer player = new RPGPlayer(e.getPlayer());
		
		RPGEngine.getInstance().getServer().getScheduler().runTaskLater(RPGEngine.getInstance(), new Runnable() {
			
			@Override
			public void run() {
				RPGEngine.getInstance().getItemManager().updateMaxHealth(player);
			}
		}, 10L);
	}
}
