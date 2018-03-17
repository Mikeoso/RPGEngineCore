package rpg.engine.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import rpg.engine.RPGEngine;
import rpg.engine.player.RPGPlayer;

public class PlayerRespawnListener implements Listener {
	
	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent e) {
		RPGPlayer player = new RPGPlayer(e.getPlayer());
		
		RPGEngine.getInstance().getServer().getScheduler().runTaskLater(RPGEngine.getInstance(), new Runnable() {
			
			@Override
			public void run() {
				RPGEngine.getInstance().getItemManager().updateMaxHealth(player);
			}
		}, 10L);
	}

}
