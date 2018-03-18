package rpg.engine.player;

import java.util.Random;

import org.bukkit.entity.Player;

import rpg.engine.RPGEngine;
import rpg.engine.config.ConfigEntries;
import rpg.engine.item.RPGItemAttributes;

/**
 * @author Daniel Dieckmann aka Mike0so
 * This Class is used to connect the RPGEngine logic to the player
 */
public class RPGPlayer {

	private Player player;
	
	private double baseHealth;
	private double baseDamage;
	private double baseArmor;
	private double baseHealthRegen;
	private double baseDodgeChance;
	private double baseCritChance;
	private double baseCritMultiplier;
	private double baseAttackSpeed;
	private double baseMovementSpeed;
	
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
		this.baseCritMultiplier = RPGEngine.getInstance().getConfig().getDouble(ConfigEntries.PLAYER_TEMPLATE_BASE_CRIT_DMG_MULTI);
		this.baseAttackSpeed = RPGEngine.getInstance().getConfig().getDouble(ConfigEntries.PLAYER_TEMPLATE_BASE_ATTACK_SPEED);
		this.baseMovementSpeed = RPGEngine.getInstance().getConfig().getDouble(ConfigEntries.PLAYER_TEMPLATE_BASE_MOVEMENT_SPEED);
		
		this.attributes = new RPGItemAttributes(this);
		this.chanceGenerator = new Random();
	}
	
	/**
	 * @return The connected bukkit player
	 */
	public Player getPlayer() {
		return this.player;
	}
	
	/**
	 * @return The ItemAttributes conntected to the player
	 */
	public RPGItemAttributes getItemAttributes() {
		return this.attributes;
	}
	
	/**
	 * @return The baseHealth and the ItemHealth calculated
	 */
	public double getMaxHealth() {
		return this.baseHealth + attributes.getItemHealth();
	}
	
	/**
	 * @return The baseHealthRegen and the ItemHealthRegen calculated
	 */
	public double getMaxHealthRegen() {
		return this.baseHealthRegen + attributes.getItemHealthRegen();
	}
	/**
	 * @return The baseArmor and the ItemArmor calculated
	 */
	public double getMaxArmor() {
		return this.baseArmor + attributes.getItemArmor();
	}
	/**
	 * @return The baseDodge and the ItemDodge calculated
	 */
	public double getMaxDodge() {
		return this.baseDodgeChance + attributes.getItemDodge();
	}
	/**
	 * @return The basrCrit and the ItemCrit calculated
	 */
	public double getMaxCriticalChance() {
		return this.baseCritChance + attributes.getItemCrit();
	}
	/**
	 * @return The baseDamage and the ItemDamage calculated
	 */
	public double getMaxDamage() {
		return getBaseDamage() + attributes.getItemDamage();
	}
	
	public double getBasicAttackSpeed() {
		return this.baseAttackSpeed;
	}
	
	public double getBaseMovementSpeed() {
		return this.baseMovementSpeed;
	}
	/**
	 * @return the multiplier to calc critical damage
	 */
	public double getBaseCritMultiplier() {
		return this.baseCritMultiplier;
	}
	/**
	 * @return The baseHealth of the Player
	 */
	public double getBaseHealth() {
		return this.baseHealth;
	}
	/**
	 * @return The baseDamage of the Player
	 */
	public double getBaseDamage() {
		return this.baseDamage;
	}
	/**
	 * @return The baseArmor of the Player
	 */
	public double getBaseAmor() {
		return this.baseArmor;
	}
	/**
	 * @return The baseHealthRegen of the Player
	 */
	public double getBaseHealthRegen() {
		return this.baseHealthRegen;
	}
	/**
	 * @return The baseDodge of the Player
	 */
	public double getBaseDodgeChance() {
		return this.baseDodgeChance;
	}
	/**
	 * @return The baseCrit
	 */
	public double getCritChance() {
		return this.baseCritChance;
	}

	/**
	 * @return rolls for the players dodge chance
	 */
	public boolean canDodge() {
		double chance = getMaxDodge();
		double roll = chanceGenerator.nextInt(100)+1;
		
		if (chance >= roll) {
			return true;
		}
		return false;
	}

	/**
	 * @return rolls for the players crit chance
	 */
	public boolean canCrit() {
		double chance = getMaxCriticalChance();
		double roll = chanceGenerator.nextInt(100)+1;
		
		if (chance >= roll) {
			return true;
		}
		return false;
	}
}
