package rpg.engine.listener;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import rpg.engine.RPGEngine;
import rpg.engine.config.ConfigEntries;
import rpg.engine.player.RPGPlayer;

public class EntityDamageByEntityListener implements Listener {
	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent e) {
		if(e.isCancelled() || !(e.getEntity() instanceof LivingEntity)) {
			return;
		}
		
		//Damage calculation
		double damageOfAttacker = 0;
		double armorOfDefender = 0;
		double critChanceMultiplier = RPGEngine.getInstance().getConfig().getDouble(ConfigEntries.PLAYER_TEMPLATE_BASE_CRIT_DMG_MULTI);
		
		//Case Entity Attacks Player
		if (e.getDamager() instanceof Player) {
			RPGPlayer player = new RPGPlayer((Player)e.getDamager());
			damageOfAttacker = player.getMaxDamage();
		}
		if (e.getEntity() instanceof Player) {
			RPGPlayer player = new RPGPlayer((Player)e.getEntity());
			armorOfDefender =  player.getMaxArmor();
			
			if (player.canDodge()) {
				e.setDamage(0);
				return;
			}
		}
		if (e.getDamager() instanceof Arrow) {
			Arrow arrow = (Arrow)e.getDamager();
			
			if (arrow.getShooter() != null && arrow.getShooter() instanceof Player) {
				RPGPlayer player = (RPGPlayer)arrow.getShooter();
				damageOfAttacker = player.getMaxDamage();
			}
		}
		
		damageOfAttacker = damageOfAttacker - armorOfDefender;
		
		if (e.getDamager() instanceof Player) {
			RPGPlayer player = new RPGPlayer((Player)e.getDamager());
			if (player.canCrit()) {
				damageOfAttacker = (int) (damageOfAttacker * critChanceMultiplier);
			}
			if (damageOfAttacker > 0) {
				e.setDamage(damageOfAttacker);
			}
		} else {
			e.setDamage(e.getDamage() - armorOfDefender);
		}
	}
}
