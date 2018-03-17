package rpg.engine.cmd.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import rpg.engine.RPGEngine;
import rpg.engine.cmd.BasicCommand;
import rpg.engine.player.RPGPlayer;

public class ItemLoreAddCommand extends BasicCommand {
	
	public ItemLoreAddCommand(RPGEngine engine) {
		super("ItemLoreAddCommand");
		setDescription("Adds/Changes the whole lore of your Item");
		setUsage("/rpg item lore add <lore1> <lore2> <lore3> <lore4>");
		setArgumentRange(1, 4);
		setIdentifiers("rpg item lore add");
		setPermission("rpg.item.lore.add");
	}

	@Override
	public boolean execute(CommandSender sender, String identifier, String[] realArgs) {
		RPGPlayer player = new RPGPlayer((Player)sender);
		
		if (player.getPlayer().getInventory().getItemInMainHand() == null ||
				player.getPlayer().getInventory().getItemInMainHand().getType() == Material.AIR) {
			player.getPlayer().sendMessage("§cYou need a item in your hand to add a lore");
			return false;
		}
		
		ItemStack itemInHand = player.getPlayer().getInventory().getItemInMainHand();
		ItemMeta itemMeta = itemInHand.getItemMeta();
		
		List<String> lore = new ArrayList<String>();
		
		for (int i = 0; i < realArgs.length; i++) {
			lore.add(realArgs[i].replaceAll("&", "§"));
		}
		itemMeta.setLore(lore);
		itemInHand.setItemMeta(itemMeta);
		player.getPlayer().getInventory().setItemInMainHand(itemInHand);
		player.getPlayer().sendMessage("§fSuccessfuly changed lore!");
		return true;
	}
}
