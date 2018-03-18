package rpg.engine.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;

import rpg.engine.RPGEngine;
import rpg.engine.player.RPGPlayer;

public class PlayerHeldItemChangeListener implements Listener {
	
	@EventHandler
	public void onHeldItemChange(PlayerItemHeldEvent e) {
		RPGPlayer player = new RPGPlayer(e.getPlayer());
		
		RPGEngine.getInstance().getServer().getScheduler().runTaskLater(RPGEngine.getInstance(), new Runnable() {
			
			@Override
			public void run() {
				RPGEngine.getInstance().getItemManager().updateStats(player);
			}
		}, 10L);
	}

}
