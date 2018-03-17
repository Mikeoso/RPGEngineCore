package rpg.engine.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

import rpg.engine.RPGEngine;
import rpg.engine.player.RPGPlayer;

public class InventoryCloseListener implements Listener {

	public void onInventoryClose(InventoryCloseEvent e) {
		RPGPlayer player = new RPGPlayer((Player)e.getPlayer());
		
		RPGEngine.getInstance().getServer().getScheduler().runTaskLater(RPGEngine.getInstance(), new Runnable() {
			
			@Override
			public void run() {
				RPGEngine.getInstance().getItemManager().updateMaxHealth(player);
			}
		}, 10L);
	}
}
