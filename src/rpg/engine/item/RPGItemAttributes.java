package rpg.engine.item;

import rpg.engine.RPGEngine;
import rpg.engine.attributes.RPGAttributes;
import rpg.engine.item.RPGItemReader.ItemReaderAttribute;
import rpg.engine.player.RPGPlayer;

public class RPGItemAttributes {

	private RPGItemReader reader;
	private RPGPlayer player;
	
	public RPGItemAttributes(RPGPlayer player) {
		this.player = player;
		this.reader = new RPGItemReader(RPGEngine.getInstance());
	}
	
	public double getItemHealth() {
		return reader.getBonusByAttribute(player, ItemReaderAttribute.NORMAL, RPGAttributes.Health, false);
	}
	
	public double getItemHealthRegen() {
		return reader.getBonusByAttribute(player, ItemReaderAttribute.NORMAL, RPGAttributes.Health_Regen, false);
	}
	
	public double getItemDamage() {
		return reader.getBonusByAttribute(player, ItemReaderAttribute.NORMAL, RPGAttributes.Damage, false);
	}
	
	public double getItemDamageRange() {
		return 0;
	}
	
	public double getItemDodge() {
		return reader.getBonusByAttribute(player, ItemReaderAttribute.NORMAL, RPGAttributes.Dodge, true);
	}
	
	public double getItemCrit() {
		return reader.getBonusByAttribute(player, ItemReaderAttribute.NORMAL, RPGAttributes.Crit_Chance, true);
	}
	
	public double getItemArmor() {
		return reader.getBonusByAttribute(player, ItemReaderAttribute.NORMAL, RPGAttributes.Armor, false);
	}
	
	public double getItemExpIncrease() {
		return reader.getBonusByAttribute(player, ItemReaderAttribute.NORMAL, RPGAttributes.EXP, true);
	}
}
