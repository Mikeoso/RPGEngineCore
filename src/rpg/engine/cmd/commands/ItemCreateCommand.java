package rpg.engine.cmd.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import rpg.engine.RPGEngine;
import rpg.engine.cmd.BasicCommand;

public class ItemCreateCommand extends BasicCommand {
	
	public ItemCreateCommand(RPGEngine engine) {
		super("ItemCreateCommand");
		setDescription("Create a new item with the specified parameters. <materialId> required.");
		setUsage("/rpg item create <materialid> <name> <lore1> <lore2> <lore3> <lore4>");
		setArgumentRange(1, 6);
		setIdentifiers("rpg item create");
		setPermission("rpg.item.create");
	}

	@Override
	public boolean execute(CommandSender sender, String identifier, String[] realArgs) {
		int itemId = 0;
		Player player = (Player)sender;
		try {
			itemId = Integer.valueOf(realArgs[0]);
		} catch (Exception e) {
		}
		
		ItemStack stack = null;
		
		try {
			stack = new ItemStack(Material.getMaterial(itemId));			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		if (stack == null) {
			try {
				stack = new ItemStack(Material.getMaterial(realArgs[0]));			
			}
			catch (Exception e) {
				e.printStackTrace();
				sender.sendMessage("§cYou need to add a valid material id or name");
				return false;
			}			
		}
		
		if (realArgs.length <= 1) {
			player.getInventory().addItem(stack);
			sender.sendMessage("§cAdded new Item to your inventory of Type: §e" + stack.getType().toString());
			return true;
		}
		
		ItemMeta stackMeta = stack.getItemMeta();
		stackMeta.setDisplayName(realArgs[1].replaceAll("_", " ").replaceAll("&", "§"));
		
		if (realArgs.length >= 3) {
			List<String> loreList = new ArrayList<String>();
			
			for (int loreArgs = 2; loreArgs < realArgs.length; loreArgs++) {
				loreList.add(realArgs[loreArgs].replaceAll("_", " ").replaceAll("&", "§"));
			}
			stackMeta.setLore(loreList);
		}
		stack.setItemMeta(stackMeta);
		player.getInventory().addItem(stack);
		sender.sendMessage("§cAdded new Item to your inventory of Type:§e " + stack.getType().toString());
		return true;
	}
}
