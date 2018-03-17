package rpg.engine.player;

import java.util.Random;

import org.bukkit.entity.Player;

import rpg.engine.RPGEngine;
import rpg.engine.config.ConfigEntries;
import rpg.engine.item.RPGItemAttributes;

public class RPGPlayer {

	private Player player;
	
	private double baseHealth;
	private double baseDamage;
	private double baseArmor;
	private double baseHealthRegen;
	private double baseDodgeChance;
	private double baseCritChance;
	
	private RPGItemAttributes attributes;
	private Random chanceGenerator;
	
	public RPGPlayer(Player player) {
		this.player = player;
		
		this.baseHealth = RPGEngine.getInstance().getConfig().getDouble(ConfigEntries.PLAYER_TEMPLATE_BASE_HEALTH);
		this.baseDamage = RPGEngine.getInstance().getConfig().getDouble(ConfigEntries.PLAYER_TEMPLATE_BASE_DAMAGE);
		this.baseArmor = RPGEngine.getInstance().getConfig().getDouble(ConfigEntries.PLAYER_TEMPLATE_BASE_ARMOR);
		this.baseHealthRegen = RPGEngine.getInstance().getConfig().getDouble(ConfigEntries.PLAYER_TEMPLATE_BASE_HEALTHREGEN);
		this.baseDodgeChance = RPGEngine.getInstance().getConfig().getDouble(ConfigEntries.PLAYER_TEMPLATE_BASE_DODGE);
		this.baseCritChance = RPGEngine.getInstance().getConfig().getDouble(ConfigEntries.PLAYER_TEMPLATE_BASE_CRIT);
		
		this.attributes = new RPGItemAttributes(this);
		this.chanceGenerator = new Random();
	}
	
	public Player getPlayer() {
		return this.player;
	}	
	public RPGItemAttributes getItemAttributes() {
		return this.attributes;
	}
	
	public double getMaxHealth() {
		return this.baseHealth + attributes.getItemHealth();
	}
	public double getMaxHealthRegen() {
		return this.baseHealthRegen + attributes.getItemHealthRegen();
	}
	public double getMaxArmor() {
		return this.baseArmor + attributes.getItemArmor();
	}
	public double getMaxDodge() {
		return this.baseDodgeChance + attributes.getItemDodge();
	}
	public double getMaxCriticalChance() {
		return this.baseCritChance + attributes.getItemCrit();
	}
	public double getMaxDamage() {
		return getBaseDamage() + attributes.getItemDamage();
	}
	
	public double getBaseHealth() {
		return this.baseHealth;
	}
	public double getBaseDamage() {
		return this.baseDamage;
	}
	public double getBaseAmor() {
		return this.baseArmor;
	}
	public double getBaseHealthRegen() {
		return this.baseHealthRegen;
	}
	public double getBaseDodgeChance() {
		return this.baseDodgeChance;
	}
	public double getCritChance() {
		return this.baseCritChance;
	}

	public boolean canDodge() {
		double chance = getMaxDodge();
		double roll = chanceGenerator.nextInt(100)+1;
		
		if (chance >= roll) {
			return true;
		}
		return false;
	}

	public boolean canCrit() {
		double chance = getMaxCriticalChance();
		double roll = chanceGenerator.nextInt(100)+1;
		
		if (chance >= roll) {
			return true;
		}
		return false;
	}
}
