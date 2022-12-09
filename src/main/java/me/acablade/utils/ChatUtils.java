package me.acablade.utils;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class ChatUtils {

    private static final String PREFIX = "&6&lOC";

    public static void sendMessage(CommandSender commandSender, String message){
        commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', PREFIX + " &7»&r " + message));
    }
    
}
