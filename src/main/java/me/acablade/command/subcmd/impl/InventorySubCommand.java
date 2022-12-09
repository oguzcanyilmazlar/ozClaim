package me.acablade.command.subcmd.impl;

import org.bukkit.Chunk;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import lombok.AllArgsConstructor;
import me.acablade.command.subcmd.ClaimSubCommand;
import me.acablade.container.ClaimContainer;
import me.acablade.manager.ClaimManager;
import me.acablade.utils.ChunkUtils;

@AllArgsConstructor
public class InventorySubCommand implements ClaimSubCommand{

    private ClaimManager claimManager;

    @Override
    public void run(CommandSender sender, String[] args) {
        // TODO Auto-generated method stub
        Player player = (Player) sender;
        Chunk chunk = player.getLocation().getChunk();
        long id = ChunkUtils.getChunkId(chunk.getX(), chunk.getZ());
        if(!claimManager.contains(id)){
            failMessage(player, "Claim içerisinde değilsin!");
            return;
        }

        ClaimContainer container = new ClaimContainer(claimManager.get(id));

        player.openInventory(container.getInventory());
        
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
