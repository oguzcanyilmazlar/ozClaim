package me.acablade.command.subcmd;

import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.acablade.utils.ChatUtils;

public interface ClaimSubCommand {

    public void run(CommandSender sender, String[] args);
    public String getPermission();
    public boolean canConsoleRun();
    public boolean needsPermission();
    public String getName();
    public String getDescription();

    default void sendMessage(CommandSender sender, String message){
        ChatUtils.sendMessage(sender, message);
    }

    default void successMessage(Player player, String message){
        sendMessage(player, "&a"+message);

        player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.5f, 0);
    }

    default void failMessage(Player player, String message){
        sendMessage(player, "&c"+message);

        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 0.5f, 0);
    }
    
}
