package me.acablade.command.subcmd.impl;

import org.bukkit.Chunk;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import lombok.AllArgsConstructor;
import me.acablade.command.subcmd.ClaimSubCommand;
import me.acablade.manager.ClaimManager;
import me.acablade.objects.Claim;
import me.acablade.utils.ChunkUtils;

@AllArgsConstructor
public class ClaimAreaSubCommand implements ClaimSubCommand {

    private ClaimManager claimManager;

    @Override
    public void run(CommandSender sender, String[] args) {
        // TODO Auto-generated method stub
        Player player = (Player) sender;

        Chunk chunk = player.getLocation().getChunk();
        if(claimManager.contains(ChunkUtils.getChunkId(chunk.getX(), chunk.getZ()))){
            failMessage(player, "Bu chunk zaten claimli!");
            return;
        }

        claimManager.add(new Claim(player.getUniqueId(), chunk.getX(), chunk.getZ()));
        successMessage(player, "Başarıyla claimledin");

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

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return "claim";
    }

    @Override
    public String getDescription() {
        // TODO Auto-generated method stub
        return "Alan claimlemeni sağlar";
    }


    
}
