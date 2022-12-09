package me.acablade.command.subcmd.impl;

import org.bukkit.command.CommandSender;

import me.acablade.command.subcmd.ClaimSubCommand;

public class HelpSubCommand implements ClaimSubCommand{

    @Override
    public void run(CommandSender sender, String[] args) {
        // TODO Auto-generated method stub
        sendMessage(sender, "&etest");
        
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
    
}
