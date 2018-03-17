package rpg.engine.cmd;

import org.bukkit.command.CommandSender;

public interface Command {
	
	public boolean execute(CommandSender sender, String identifier, String[] realArgs);
	public String getDescription();
	public String[] getIdentifiers();
	public int getMaxArguments();
	public int getMinArguments();
	public String getName();
	public String[] getNotes();
	public String getPermission();
	public String getUsage();
	public boolean isIdentifier(CommandSender sender, String input);
	void cancelInteraction(CommandSender executor);
	boolean isShownOnHelpMenu();
	boolean isInteractive();
	boolean isInProgress(CommandSender executor);
	
}
