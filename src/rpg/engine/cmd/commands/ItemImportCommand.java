package rpg.engine.cmd.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import rpg.engine.cmd.BasicCommand;
import rpg.engine.item.RPGItemFileManager;
import rpg.engine.player.RPGPlayer;

public class ItemImportCommand extends BasicCommand {

	public ItemImportCommand() {
		super("ItemImportCommand");
		setDescription("Loads a item by the given name");
		setUsage("/rpg item load <itemName>");
		setArgumentRange(1, 1);
		setIdentifiers("rpg item load");
		setPermission("rpg.item.load");
	}

	@Override
	public boolean execute(CommandSender sender, String identifier, String[] realArgs) {
		RPGPlayer player = new RPGPlayer((Player)sender);
		
		
		RPGItemFileManager manager = new RPGItemFileManager();
		ItemStack stack = manager.loadItemByName(realArgs[0]);
		
		if (stack == null) {
			player.getPlayer().sendMessage("§cThe entered Itemname cannot be found! Enter a valid name.");
			return false;
		}
		player.getPlayer().getInventory().addItem(stack);
		player.getPlayer().sendMessage("§cSuccessfuly loaded item:§e " + realArgs[0]);
		return true;
	}

}
