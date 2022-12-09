package me.acablade;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import me.acablade.manager.ClaimManager;
import me.acablade.utils.ItemUtils;


public class Main extends JavaPlugin {

	private ClaimManager claimManager;

    @Override
    public void onEnable() {

		this.claimManager = new ClaimManager();

		Bukkit.getScheduler().runTaskTimerAsynchronously(this, claimManager, 0, 5);

		ItemUtils.init(null);
    }

}
