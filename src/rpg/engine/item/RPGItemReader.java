package rpg.engine.item;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.inventory.ItemStack;

import rpg.engine.config.ConfigEntries;
import rpg.engine.player.RPGPlayer;
import rpg.engine.RPGEngine;
import rpg.engine.attributes.RPGAttributes;

public class RPGItemReader {

	private RPGEngine engine;
	
	public enum ItemReaderAttribute {
		NORMAL,
		ONLY_WORD,
		RANGE
	}
	
	public RPGItemReader(RPGEngine engine) {
		this.engine = engine;
	}
	
	public String buildPatternString(ItemReaderAttribute attr, RPGAttributes rpgAttributes, boolean procent) {
		boolean isOnlyWord = false;
		
		StringBuilder patternSb = new StringBuilder();
		
		switch(attr) {
		case NORMAL:
			if (procent) {
				patternSb.append("[+](\\d+)[%][ ](");
			} else {
				patternSb.append("[+](\\d+)[ ](");				
			}
			break;
		case RANGE:
			patternSb.append("(\\d+)(-)(\\d+)[ ](");
			break;
		case ONLY_WORD:
			patternSb.append("<attr>: (\\d+)(");
			isOnlyWord = true;
			break;
		}
		
		switch(rpgAttributes) {
		case Health:
			patternSb.append(engine.getConfig().getString(ConfigEntries.ITEM_ATTR_HEALTH_KEYWORD));
			break;
		case Health_Regen:
			patternSb.append(engine.getConfig().getString(ConfigEntries.ITEM_ATTR_HEALTHREGEN_KEYWORD));
			break;
		case Armor:
			patternSb.append(engine.getConfig().getString(ConfigEntries.ITEM_ATTR_ARMOR_KEYWORD));
			break;
		case Crit_Chance:
			patternSb.append(engine.getConfig().getString(ConfigEntries.ITEM_ATTR_ARMOR_KEYWORD));
			break;
		case Damage:
			patternSb.append(engine.getConfig().getString(ConfigEntries.ITEM_ATTR_DAMAGE_KEYWORD));
			break;
		case Dodge:
			patternSb.append(engine.getConfig().getString(ConfigEntries.ITEM_ATTR_DODGE_KEYWORD));
			break;
		case EXP:
			patternSb.append(engine.getConfig().getString(ConfigEntries.ITEM_ATTR_EXP_KEYWORD));
			break;
		}
		
		patternSb.append(")");
		
		if (isOnlyWord) {
			//here we will return stuff like level 10 or permission stuff
		}
		return patternSb.toString();
	}
	
	public double getBonusByAttribute (RPGPlayer player, ItemReaderAttribute attr, RPGAttributes rpgAttributes, boolean procent) {
		
		double bonusAmount = 0;
		List<String> itemLore;
		
		if (player == null) {
			engine.getLogger().warning("#getBonusByAttribute: Player cannot be null!");
			return 0;
		}
		
		Pattern genericPattern = Pattern.compile(buildPatternString(attr, rpgAttributes, procent));
		
		for(ItemStack item : player.getPlayer().getInventory().getArmorContents()) {
			if (!(ItemUtils.hasLore(item))) {
				continue;
			}
			itemLore = item.getItemMeta().getLore();
			Matcher matcher = genericPattern.matcher(itemLore.toString().toLowerCase());
			
			if (matcher.find()) {
				bonusAmount += Double.valueOf(matcher.group(1));
			}
		}
		if((ItemUtils.hasLore(player.getPlayer().getInventory().getItemInMainHand()))) {
			itemLore = player.getPlayer().getInventory()
					.getItemInMainHand().getItemMeta().getLore();
			Matcher matcher = genericPattern.matcher(itemLore.toString().toLowerCase());
			if (matcher.find()) {
				bonusAmount += Double.valueOf(matcher.group(1));
			}
		}
		if((ItemUtils.hasLore(player.getPlayer().getInventory().getItemInOffHand()))) {
			itemLore = player.getPlayer().getInventory()
					.getItemInOffHand().getItemMeta().getLore();
			Matcher matcher = genericPattern.matcher(itemLore.toString().toLowerCase());
			if (matcher.find()) {
				bonusAmount += Double.valueOf(matcher.group(1));
			}
		}
		return bonusAmount;
	}

}
