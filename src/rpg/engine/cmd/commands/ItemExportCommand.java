package rpg.engine.cmd.commands;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import rpg.engine.cmd.BasicCommand;
import rpg.engine.item.RPGItemFileManager;
import rpg.engine.player.RPGPlayer;

public class ItemExportCommand extends BasicCommand {

	public ItemExportCommand() {
		super("ItemExportCommand");
		setDescription("Saves the item your holding to a yml file");
		setUsage("/rpg item save");
		setArgumentRange(0, 0);
		setIdentifiers("rpg item save");
		setPermission("rpg.item.save");
	}

	@Override
	public boolean execute(CommandSender sender, String identifier, String[] realArgs) {
		RPGPlayer player = new RPGPlayer((Player)sender);
		
		if (player.getPlayer().getInventory().getItemInMainHand() == null ||
				player.getPlayer().getInventory().getItemInMainHand().getType() == Material.AIR) {
			player.getPlayer().sendMessage("§cYou need a item in your hand to save");
			return false;
		}
		
		if (!(player.getPlayer().getInventory().getItemInMainHand().hasItemMeta()) || !(player.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasDisplayName())) {
			player.getPlayer().sendMessage("§cYou can only save items with a custom name, as this is used as a filename");
			return false;
		}
		
		RPGItemFileManager manager = new RPGItemFileManager();
		manager.saveItemToFile(player.getPlayer().getInventory().getItemInMainHand());
		return true;
	}
}
