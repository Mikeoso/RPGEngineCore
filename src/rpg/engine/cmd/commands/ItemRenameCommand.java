package rpg.engine.cmd.commands;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import rpg.engine.RPGEngine;
import rpg.engine.cmd.BasicCommand;
import rpg.engine.item.RPGItemFileManager;
import rpg.engine.player.RPGPlayer;

public class ItemRenameCommand extends BasicCommand{

	public ItemRenameCommand(RPGEngine engine) {
		super("ItemRenameCommand");
		setDescription("Adds/Changes the name of the item your holding in your hand.");
		setUsage("/rpg item rename <newName>");
		setArgumentRange(1, 1);
		setIdentifiers("rpg item rename");
		setPermission("rpg.item.rename");
	}

	@Override
	public boolean execute(CommandSender sender, String identifier, String[] realArgs) {
		RPGPlayer player = new RPGPlayer((Player)sender);
		
		if (player.getPlayer().getInventory().getItemInMainHand() == null ||
				player.getPlayer().getInventory().getItemInMainHand().getType() == Material.AIR) {
			player.getPlayer().sendMessage("�cYou need a item in your hand to add/change a name");
			return false;
		}
		
		ItemStack itemInHand = player.getPlayer().getInventory().getItemInMainHand().clone();
		ItemMeta itemMeta = itemInHand.getItemMeta();
		
		String oldName = itemMeta.getDisplayName();

		RPGItemFileManager manager = new RPGItemFileManager();
		itemMeta.setDisplayName(realArgs[0].replaceAll("&", "�"));
		itemInHand.setItemMeta(itemMeta);
		player.getPlayer().getInventory().setItemInMainHand(itemInHand);
		manager.renameFile(oldName, realArgs[0]);
		player.getPlayer().sendMessage("�cSuccessfuly changed displayname from: �e" + oldName + "�c to: �e" + realArgs[0]);
		return true;
	}
}
