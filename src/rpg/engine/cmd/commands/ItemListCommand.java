package rpg.engine.cmd.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.inventory.ItemStack;

import rpg.engine.cmd.BasicCommand;
import rpg.engine.item.RPGItemFileManager;

public class ItemListCommand extends BasicCommand {
	
	public ItemListCommand() {
		super("ItemListCommand");
		setDescription("Lists all saved items");
		setUsage("/rpg item list");
		setArgumentRange(0, 0);
		setIdentifiers("rpg item list");
		setPermission("rpg.item.list");
	}

	@Override
	public boolean execute(CommandSender sender, String identifier, String[] realArgs) {		
		RPGItemFileManager manager = new RPGItemFileManager();

		//I know its ugly, but its 4 a.m
		manager.loadExistingFiles();
		sender.sendMessage("§c Found Items: §e" + manager.getDb().values().size());
		int counter = 0;
		for(ItemStack stack : manager.getDb().values()) {
			counter++;
			sender.sendMessage("§c" + counter + ".§e " + stack.getItemMeta().getDisplayName());
		}
		return true;
	}
}
