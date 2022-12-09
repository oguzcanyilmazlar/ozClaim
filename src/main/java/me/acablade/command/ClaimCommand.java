package me.acablade.command;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

import me.acablade.command.subcmd.ClaimSubCommand;
import me.acablade.command.subcmd.impl.ClaimAreaSubCommand;
import me.acablade.command.subcmd.impl.HelpSubCommand;
import me.acablade.command.subcmd.impl.InfoSubCommand;
import me.acablade.command.subcmd.impl.InventorySubCommand;
import me.acablade.manager.ClaimManager;

import static me.acablade.utils.ChatUtils.sendMessage;

public class ClaimCommand implements CommandExecutor{

    private Map<String, ClaimSubCommand> subCommandMap = new HashMap<>();


    public ClaimCommand(ClaimManager claimManager){
        subCommandMap.put("help", new HelpSubCommand());
        subCommandMap.put("claim", new ClaimAreaSubCommand(claimManager));
        subCommandMap.put("inventory", new InventorySubCommand(claimManager));
        subCommandMap.put("info", new InfoSubCommand(claimManager));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(args.length == 0){
            subCommandMap.get("help").run(sender, args);
            return false;
        }

        String subCommandName = subCommandMap.containsKey(args[0]) ? args[0] : "help";

        ClaimSubCommand subCommand = subCommandMap.get(subCommandName);

        String[] newArgs = new String[0];
        if(args.length > 1)
            newArgs = Arrays.copyOfRange(args, 1, args.length-1);

        if(sender instanceof ConsoleCommandSender && !subCommand.canConsoleRun()){
            sendMessage(sender, "&cBu komutu konsol çalıştıramaz!");
            return false;
        }

        if(subCommand.needsPermission() && !sender.hasPermission(subCommand.getPermission())){
            sendMessage(sender, "&cBu komut için yetkin yok!");
            return false;
        }

        subCommand.run(sender, newArgs);

        return false;
    }
    
}
