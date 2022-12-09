package me.acablade.listener;

import org.bukkit.Chunk;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import me.acablade.manager.ClaimManager;
import me.acablade.objects.Claim;
import me.acablade.utils.ChunkUtils;

public class ClaimListener implements Listener{

    private ClaimManager claimManager;

    public ClaimListener(ClaimManager claimManager){
        this.claimManager = claimManager;
    }

    @EventHandler
    private void onBlockBreak(BlockBreakEvent event){
        Block block = event.getBlock();
        Chunk chunk = block.getChunk();
        long id = ChunkUtils.getChunkId(chunk.getX(), chunk.getZ());
        if(!claimManager.contains(id)) return;

        Claim claim = claimManager.get(id);
        if(claim.getOwner().equals(event.getPlayer().getUniqueId())) return;

        event.setCancelled(true);
    }

    @EventHandler
    private void onBlockPlace(BlockPlaceEvent event){
        Block block = event.getBlock();
        Chunk chunk = block.getChunk();
        long id = ChunkUtils.getChunkId(chunk.getX(), chunk.getZ());
        if(!claimManager.contains(id)) return;

        Claim claim = claimManager.get(id);
        if(claim.getOwner().equals(event.getPlayer().getUniqueId())) return;

        event.setCancelled(true);
    }

    
}
