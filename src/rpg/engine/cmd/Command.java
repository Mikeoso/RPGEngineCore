package rpg.engine.cmd;

import org.bukkit.command.CommandSender;

/**
 * @author Daniel Dieckmann aka Mike0so
 * 
 * Basic Command interface to handle commands via the CommandHandler
 *
 */
public interface Command {
	
	/**
	 * @param sender Player that executed the Command
	 * @param identifier The identifier of the Command
	 * @param realArgs The real Command arguments
	 * @return
	 */
	public boolean execute(CommandSender sender, String identifier, String[] realArgs);
	
	/**
	 * @return The description of a command
	 */
	public String getDescription();
	
	
	/**
	 * @return The identifiers of a command to find the right command automaticly
	 */
	public String[] getIdentifiers();
	
	
	/**
	 * @return The maximum arguments of a command
	 */
	public int getMaxArguments();
	
	
	/**
	 * @return The minimum arguments of a command
	 */
	public int getMinArguments();
	
	/**
	 * @return Name of the command (used for translation later)
	 */
	public String getName();
	
	
	/**
	 * @return Custom notes for commands
	 */
	public String[] getNotes();
	
	
	/**
	 * @return Permission needed to use the command
	 */
	public String getPermission();
	
	
	
	/**
	 * @return Usage pattern for a user
	 */
	public String getUsage();
	
	
	/**
	 * @param sender player that executed the command
	 * @param input the identifiers /<ident1> <ident2>...
	 * @return True if the input is a identifier for any command otherwise false
	 */
	public boolean isIdentifier(CommandSender sender, String input);
	
	
	/**
	 * @param executor player that executed the command
	 * Used for the later implemented interactioncommand to cancel a interaction
	 */
	void cancelInteraction(CommandSender executor);
	
	/**
	 * @return True if this command will be shown on help command otherwise false
	 */
	boolean isShownOnHelpMenu();
	
	
	/**
	 * @return true if this command is an interactive command
	 */
	boolean isInteractive();
	
	
	/**
	 * @param executor player that executed the command
	 * @return True if this interaction command is in progress otherwise false
	 */
	boolean isInProgress(CommandSender executor);
	
}
