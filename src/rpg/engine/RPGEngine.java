package rpg.engine;

import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import net.milkbowl.vault.permission.Permission;
import rpg.engine.cmd.CommandExec;
import rpg.engine.item.RPGItemManager;
import rpg.engine.listener.EntityDamageByEntityListener;
import rpg.engine.listener.EntityRegainHealthListener;
import rpg.engine.listener.InventoryCloseListener;
import rpg.engine.listener.PlayerChangedMainListener;
import rpg.engine.listener.PlayerDropItemListener;
import rpg.engine.listener.PlayerExperienceChangeListener;
import rpg.engine.listener.PlayerHeldItemChangeListener;
import rpg.engine.listener.PlayerJoinListener;
import rpg.engine.listener.PlayerLevelChanceListener;
import rpg.engine.listener.PlayerRespawnListener;

public class RPGEngine extends JavaPlugin {

	private FileConfiguration config;
	private static Permission perms;
	
	private static RPGEngine engine;
	private static RPGItemManager itemManager;
	
	@Override
	public void onEnable() {
		engine = this;
		
		if (config == null) {
			config = getConfig();
			config.options().copyDefaults(true);
			saveConfig();
		}
		if (!setupPermissions()) {
			getLogger().warning("§c[ArathosRPG] Error! Vault could not be initialized. Please install Vault to use ArathosRPG");
			this.getPluginLoader().disablePlugin(this);
			return;
		}
		if (itemManager == null) {
			itemManager = new RPGItemManager();
		}
		registerEvents();
		
		getCommand("rpg").setExecutor(new CommandExec());
	}
	
	public RPGItemManager getItemManager() {
		return itemManager;
	}

	@Override
	public void onDisable() {
		
	}
	
	/**
	 * @return The instance of this Plugin
	 */
	public static RPGEngine getInstance() {
		return engine;
	}
	
	/**
	 * @param sender command executor
	 * @param permission permission needed
	 * @return true if the executor has the needed permission
	 */
	public static boolean hasPerm(CommandSender sender, String permission) {
		return perms.has(sender, permission);
	}
	
    /**
     * @return registers Vault as Permission hook
     */
    private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
        return perms != null;
    }
    
    /**
     * Registers all Listeners needed by the plugin
     */
    private void registerEvents() {
    	engine.getServer().getPluginManager().registerEvents(new InventoryCloseListener(), this);
    	engine.getServer().getPluginManager().registerEvents(new PlayerChangedMainListener(), this);
    	engine.getServer().getPluginManager().registerEvents(new PlayerDropItemListener(), this);
    	engine.getServer().getPluginManager().registerEvents(new PlayerHeldItemChangeListener(), this);
    	engine.getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
    	engine.getServer().getPluginManager().registerEvents(new PlayerLevelChanceListener(), this);
    	engine.getServer().getPluginManager().registerEvents(new PlayerRespawnListener(), this);
    	engine.getServer().getPluginManager().registerEvents(new EntityRegainHealthListener(), this);
    	engine.getServer().getPluginManager().registerEvents(new EntityDamageByEntityListener(), this);
    	engine.getServer().getPluginManager().registerEvents(new PlayerExperienceChangeListener(), this);
    }
}
