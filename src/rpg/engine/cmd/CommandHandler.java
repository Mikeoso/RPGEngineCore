package rpg.engine.cmd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import rpg.engine.RPGEngine;

public class CommandHandler {

	protected LinkedHashMap<String, Command> commands;
	protected HashMap<String, Command> identifiers;

	public CommandHandler() {
		commands = new LinkedHashMap<String, Command>();
		identifiers = new HashMap<String, Command>();
	}

	public void addCommand(Command cmd) {
		commands.put(cmd.getName().toLowerCase(), cmd);
		for (String ident : cmd.getIdentifiers()) {
			identifiers.put(ident.toLowerCase(), cmd);
		}
	}

	public boolean dispatch(CommandSender sender, String label, String[] args) {
		for (int argsIncluded = args.length; argsIncluded >= 0; argsIncluded--) {
			StringBuilder identifierBuilder = new StringBuilder(label);

			for (int i = 0; i < argsIncluded; i++) {
				identifierBuilder.append(' ').append(args[i]);
			}

			String identifier = identifierBuilder.toString();
			Command cmd = getCmdFromIdent(identifier, sender);

			if (cmd == null) {
				continue;
			}

			String[] realArgs = Arrays.copyOfRange(args, argsIncluded, args.length);
			if (!(cmd.isInProgress(sender))) {
				if (realArgs.length < cmd.getMinArguments() || realArgs.length > cmd.getMaxArguments()) {
					displayCommandHelp(cmd, sender);
					return true;
				} else if (realArgs.length > 0 && realArgs[0].equals("?")) {
					displayCommandHelp(cmd, sender);
				}
			}

			if (!hasPermission(sender, cmd.getPermission())) {
				sender.sendMessage("§c[ARPG] You are not allowed to use this command. Insufficent Permission!");
				return true;
			}

			cmd.execute(sender, identifier, realArgs);
			return true;
		}
		return true;
	}

	public void displayCommandHelp(Command cmd, CommandSender sender) {
		sender.sendMessage("§cCommand: §e " + cmd.getName());
		sender.sendMessage("§cDescription:§e " + cmd.getDescription());
		sender.sendMessage("§cUsage: §e" + cmd.getUsage());

		if (cmd.getNotes() != null) {
			for (String note : cmd.getNotes()) {
				sender.sendMessage("§e" + note);
			}
		}
	}

	public Command getCmdFromIdent(String identifier, CommandSender sender) {
		if (identifiers.get(identifier.toLowerCase()) == null) {
			for (Command cmd : commands.values()) {
				if (cmd.isIdentifier(sender, identifier)) {
					return cmd;
				}
			}
		}
		return identifiers.get(identifier.toLowerCase());
	}

	public Command getCommand(String name) {
		return commands.get(name.toLowerCase());
	}

	public List<Command> getCommands() {
		return new ArrayList<Command>(commands.values());
	}

	public void removeCommand(Command command) {

		commands.remove(command.getName().toLowerCase());

		for (String ident : command.getIdentifiers()) {
			identifiers.remove(ident.toLowerCase());
		}
	}

	public static boolean hasPermission(CommandSender sender, String permission) {

		if (!(sender instanceof Player) || permission == null || permission.isEmpty()) {
			return true;
		}
		Player player = (Player) sender;

		RPGEngine.getInstance();
		return player.isOp() || RPGEngine.hasPerm(sender, permission);
	}
	
}
