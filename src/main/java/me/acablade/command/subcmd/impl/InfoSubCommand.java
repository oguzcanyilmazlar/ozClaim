package me.acablade.command.subcmd.impl;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import lombok.AllArgsConstructor;
import me.acablade.command.subcmd.ClaimSubCommand;
import me.acablade.manager.ClaimManager;
import me.acablade.objects.Claim;
import me.acablade.utils.ChatUtils;
import me.acablade.utils.ChunkUtils;

@AllArgsConstructor
public class InfoSubCommand implements ClaimSubCommand{

    private ClaimManager claimManager;


    @Override
    public void run(CommandSender sender, String[] args) {
        // TODO Auto-generated method stub
        Player player = (Player) sender;
        Chunk chunk = player.getLocation().getChunk();
        long id = ChunkUtils.getChunkId(chunk.getX(), chunk.getZ());
        if(!claimManager.contains(id)){
            failMessage(player, "Chunk içerisinde değilsin!");
            return;
        }

        Claim claim = claimManager.get(id);

        long millis = claim.getTotalPower();

        sendMessage(player, "&eBitiş süresi:");
        sendMessage(player, "   &a" + ChatUtils.makeReadable(millis));
        sendMessage(player, "&eSahibi:");
        successMessage(player, "   " + Bukkit.getPlayer(claim.getOwner()).getName());

    }

    @Override
    public String getPermission() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean canConsoleRun() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean needsPermission() {
        // TODO Auto-generated method stub
        return false;
    }
    
}
