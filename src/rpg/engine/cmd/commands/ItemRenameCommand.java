package rpg.engine.cmd.commands;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import rpg.engine.RPGEngine;
import rpg.engine.cmd.BasicCommand;
import rpg.engine.player.RPGPlayer;

public class ItemRenameCommand extends BasicCommand{

	public ItemRenameCommand(RPGEngine engine) {
		super("ItemRenameCommand");
		setDescription("Adds/Changes the name of the item your holding in your hand.");
		setUsage("/rpg item rename");
		setArgumentRange(1, 1);
		setIdentifiers("rpg item rename");
		setPermission("rpg.item.rename");
	}

	@Override
	public boolean execute(CommandSender sender, String identifier, String[] realArgs) {
		RPGPlayer player = new RPGPlayer((Player)sender);
		
		if (player.getPlayer().getInventory().getItemInMainHand() == null ||
				player.getPlayer().getInventory().getItemInMainHand().getType() == Material.AIR) {
			player.getPlayer().sendMessage("§cYou need a item in your hand to add/change a name");
			return false;
		}
		
		ItemStack itemInHand = player.getPlayer().getInventory().getItemInMainHand();
		ItemMeta itemMeta = itemInHand.getItemMeta();
		
		itemMeta.setDisplayName(realArgs[0].replaceAll("&", "§"));
		player.getPlayer().getInventory().setItemInMainHand(itemInHand);
		player.getPlayer().sendMessage("§fSuccessfuly changed lore!");
		return true;
	}
}
