package me.acablade.command.subcmd.impl;

import java.util.Map;

import org.bukkit.command.CommandSender;

import lombok.AllArgsConstructor;
import me.acablade.command.subcmd.ClaimSubCommand;

@AllArgsConstructor
public class HelpSubCommand implements ClaimSubCommand{

    private Map<String, ClaimSubCommand> map;

    @Override
    public void run(CommandSender sender, String[] args) {
        // TODO Auto-generated method stub
        sendMessage(sender, "&6Yardım Menüsü:");
        map.values().stream()
        .filter(c -> !c.needsPermission() || sender.hasPermission(c.getPermission()))
        .forEach(c -> {
            sendMessage(sender, "&e/claim " + c.getName() + " &7--> &e" + c.getDescription());
        });
        
    }

    @Override
    public String getPermission() {
        return null;
    }

    @Override
    public boolean canConsoleRun() {
        return true;
    }

    @Override
    public boolean needsPermission() {
        return false;
    }

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return "help";
    }

    @Override
    public String getDescription() {
        // TODO Auto-generated method stub
        return "Bu menüyü gösterir";
    }
    
}
