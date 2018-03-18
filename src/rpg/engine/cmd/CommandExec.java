package rpg.engine.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import rpg.engine.RPGEngine;
import rpg.engine.cmd.commands.ItemCreateCommand;
import rpg.engine.cmd.commands.ItemDuplicateCommand;
import rpg.engine.cmd.commands.ItemExportCommand;
import rpg.engine.cmd.commands.ItemImportCommand;
import rpg.engine.cmd.commands.ItemListCommand;
import rpg.engine.cmd.commands.ItemLoreAddCommand;
import rpg.engine.cmd.commands.ItemRenameCommand;

/**
 * @author Daniel Dieckmann aka Mike0so
 * 
 * This Class is used to execute the custom command handler and register the command modules
 *
 */
public class CommandExec implements CommandExecutor {

	private CommandHandler hander;
	
	public CommandExec() {
		hander = new CommandHandler();		
		hander.addCommand(new ItemCreateCommand(RPGEngine.getInstance()));
		hander.addCommand(new ItemDuplicateCommand(RPGEngine.getInstance()));
		hander.addCommand(new ItemLoreAddCommand(RPGEngine.getInstance()));
		hander.addCommand(new ItemRenameCommand(RPGEngine.getInstance()));
		hander.addCommand(new ItemExportCommand());
		hander.addCommand(new ItemImportCommand());
		hander.addCommand(new ItemListCommand());
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		return hander.dispatch(sender, label, args);
	}

}
