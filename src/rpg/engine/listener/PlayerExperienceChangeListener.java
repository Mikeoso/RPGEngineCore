package rpg.engine.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerExpChangeEvent;

import rpg.engine.player.RPGPlayer;

public class PlayerExperienceChangeListener implements Listener {

	@EventHandler
	public void onExperienceGain(PlayerExpChangeEvent e) {
		RPGPlayer player = new RPGPlayer(e.getPlayer());
		
		if (player.getItemAttributes().getItemExpIncrease() > 0.0D) {
			double bonus = 0.0D;
			double itemBonus = player.getItemAttributes().getItemExpIncrease();
			
			bonus = e.getAmount() * itemBonus / 100.0D;
			player.getPlayer().sendMessage("normal: " + e.getAmount() + " mod: " + bonus);
			player.getPlayer().giveExp((int)bonus);
		}
	}
}
