package rpg.engine.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRegainHealthEvent;

import rpg.engine.RPGEngine;
import rpg.engine.player.RPGPlayer;

public class EntityRegainHealthListener implements Listener {

	@EventHandler
	public void onEntityRegainHealth(EntityRegainHealthEvent e) {
		if (e.getEntity() instanceof Player) {
			RPGPlayer player = new RPGPlayer((Player)e.getEntity());
			e.setAmount(player.getMaxHealthRegen());
			RPGEngine.getInstance().getServer().getScheduler().runTaskLater(RPGEngine.getInstance(), new Runnable() {
				
				@Override
				public void run() {
					RPGEngine.getInstance().getItemManager().updateStats(player);
				}
			}, 10L);
		}
	}
}
