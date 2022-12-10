package me.acablade;

import org.bukkit.plugin.java.JavaPlugin;

import me.acablade.command.ClaimCommand;
import me.acablade.db.OzDatabase;
import me.acablade.db.impl.YamlDatabase;
import me.acablade.listener.ClaimListener;
import me.acablade.manager.ClaimManager;
import me.acablade.utils.ItemUtils;


public class Main extends JavaPlugin {

	private ClaimManager claimManager;
	private OzDatabase database;

    @Override
    public void onEnable() {

		this.database = new YamlDatabase(this);
		this.claimManager = new ClaimManager(database);

		getServer().getScheduler().runTaskTimerAsynchronously(this, claimManager, 0, 20 * ClaimManager.PERIOD);

		getServer().getPluginManager().registerEvents(new ClaimListener(claimManager, database), this);

		getCommand("claim").setExecutor(new ClaimCommand(claimManager));

		ItemUtils.init(null);
    }

}
