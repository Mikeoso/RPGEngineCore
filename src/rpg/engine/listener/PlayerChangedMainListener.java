package rpg.engine.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;

import rpg.engine.RPGEngine;
import rpg.engine.player.RPGPlayer;

public class PlayerChangedMainListener implements Listener {
	
	@EventHandler
	public void onMainHandChanged(PlayerSwapHandItemsEvent e) {
		RPGPlayer player = new RPGPlayer(e.getPlayer());
		
		RPGEngine.getInstance().getServer().getScheduler().runTaskLater(RPGEngine.getInstance(), new Runnable() {
			
			@Override
			public void run() {
				RPGEngine.getInstance().getItemManager().updateStats(player);
			}
		}, 10L);
		}
	
}
