package rpg.engine.cmd.commands;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import rpg.engine.RPGEngine;
import rpg.engine.cmd.BasicCommand;
import rpg.engine.player.RPGPlayer;

public class ItemDuplicateCommand extends BasicCommand {
	
	public ItemDuplicateCommand(RPGEngine engine) {
		super("ItemDuplicateCommand");
		setDescription("Duplicates the item your holding in your hand");
		setUsage("/rpg item duplicate");
		setArgumentRange(0, 0);
		setIdentifiers("rpg item duplicate");
		setPermission("rpg.item.duplicate");
	}

	@Override
	public boolean execute(CommandSender sender, String identifier, String[] realArgs) {
		RPGPlayer player = new RPGPlayer((Player)sender);
		
		if (player.getPlayer().getInventory().getItemInMainHand() == null ||
				player.getPlayer().getInventory().getItemInMainHand().getType() == Material.AIR) {
			player.getPlayer().sendMessage("§cYou need a item in your hand to duplicate it");
			return false;
		}
		
		ItemStack duplicated = player.getPlayer().getInventory().getItemInMainHand().clone();
		player.getPlayer().getInventory().addItem(duplicated);
		player.getPlayer().sendMessage("$fSuccessfuly duplicated!");
		return true;
	}
}
