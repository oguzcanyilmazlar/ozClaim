package me.acablade;

import org.bukkit.plugin.java.JavaPlugin;

import me.acablade.command.ClaimCommand;
import me.acablade.listener.ClaimListener;
import me.acablade.manager.ClaimManager;
import me.acablade.utils.ItemUtils;


public class Main extends JavaPlugin {

	private ClaimManager claimManager;

    @Override
    public void onEnable() {

		this.claimManager = new ClaimManager();

		getServer().getScheduler().runTaskTimerAsynchronously(this, claimManager, 0, 200);

		getServer().getPluginManager().registerEvents(new ClaimListener(claimManager), this);

		getCommand("claim").setExecutor(new ClaimCommand(claimManager));

		ItemUtils.init(null);
    }

}
