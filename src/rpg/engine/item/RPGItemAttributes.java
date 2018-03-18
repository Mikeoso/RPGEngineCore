package rpg.engine.item;

import rpg.engine.RPGEngine;
import rpg.engine.attributes.RPGAttributes;
import rpg.engine.item.RPGItemReader.ItemReaderAttribute;
import rpg.engine.player.RPGPlayer;

/**
 * @author Daniel Dieckmann aka Mike0so
 * This Class hold the ItemAttributes for each player
 */
public class RPGItemAttributes {

	private RPGItemReader reader;
	private RPGPlayer player;
	
	public RPGItemAttributes(RPGPlayer player) {
		this.player = player;
		this.reader = new RPGItemReader(RPGEngine.getInstance());
	}
	
	/**
	 * @return The Item Health of the Player
	 */
	public double getItemHealth() {
		return reader.getBonusByAttribute(player, ItemReaderAttribute.NORMAL, RPGAttributes.Health, false);
	}
	
	/**
	 * @return The Item HealthRegen of the Player
	 */
	public double getItemHealthRegen() {
		return reader.getBonusByAttribute(player, ItemReaderAttribute.NORMAL, RPGAttributes.Health_Regen, false);
	}
	
	/**
	 * @return The Item Damage of the Player
	 */
	public double getItemDamage() {
		return reader.getBonusByAttribute(player, ItemReaderAttribute.NORMAL, RPGAttributes.Damage, false);
	}
	
	/**
	 * @return The Item RangeDamage of the Player
	 */
	public double getItemDamageRange() {
		return 0;
	}
	
	/**
	 * @return The Item Dodge of the Player
	 */
	public double getItemDodge() {
		return reader.getBonusByAttribute(player, ItemReaderAttribute.NORMAL, RPGAttributes.Dodge, true);
	}
	
	/**
	 * @return The Item Crit of the Player
	 */
	public double getItemCrit() {
		return reader.getBonusByAttribute(player, ItemReaderAttribute.NORMAL, RPGAttributes.Crit_Chance, true);
	}
	
	/**
	 * @return The Item Armor of the Player
	 */
	public double getItemArmor() {
		return reader.getBonusByAttribute(player, ItemReaderAttribute.NORMAL, RPGAttributes.Armor, false);
	}
	
	/**
	 * @return The Item ExperienceInc of the Player
	 */
	public double getItemExpIncrease() {
		return reader.getBonusByAttribute(player, ItemReaderAttribute.NORMAL, RPGAttributes.EXP, true);
	}
	
	public double getItemAttackSpeed() {
		return reader.getBonusByAttribute(player, ItemReaderAttribute.NORMAL, RPGAttributes.Attack_Speed, true);
	}
	
	public double getItemMovementSpeed() {
		return reader.getBonusByAttribute(player, ItemReaderAttribute.NORMAL, RPGAttributes.Movement_Speed, true);
	}
}
