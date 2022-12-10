package me.acablade.listener;

import org.bukkit.Chunk;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

import me.acablade.db.OzDatabase;
import me.acablade.manager.ClaimManager;
import me.acablade.objects.Claim;
import me.acablade.utils.ChunkUtils;

public class ClaimListener implements Listener{

    private ClaimManager claimManager;
    private OzDatabase database;

    public ClaimListener(ClaimManager claimManager, OzDatabase database){
        this.claimManager = claimManager;
        this.database = database;
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

    @EventHandler
    private void onInteract(PlayerInteractEvent event){
        Block block = event.getClickedBlock();
        if(block==null) return;
        Chunk chunk = block.getChunk();
        long id = ChunkUtils.getChunkId(chunk.getX(), chunk.getZ());
        if(!claimManager.contains(id)) return;

        Claim claim = claimManager.get(id);
        if(claim.getOwner().equals(event.getPlayer().getUniqueId())) return;

        event.setCancelled(true);
    }

    @EventHandler
    private void onInteractEntity(PlayerInteractEntityEvent event){
        Entity entity = event.getRightClicked();
        Chunk chunk = entity.getLocation().getChunk();
        long id = ChunkUtils.getChunkId(chunk.getX(), chunk.getZ());
        if(!claimManager.contains(id)) return;

        Claim claim = claimManager.get(id);
        if(claim.getOwner().equals(event.getPlayer().getUniqueId())) return;

        event.setCancelled(true);
    }

    @EventHandler
    private void onInventoryClose(InventoryCloseEvent event){

        Inventory inventory = event.getInventory();

        if(!(inventory.getHolder() instanceof Claim)) return;
        Claim claim = (Claim) inventory.getHolder();

        if(!event.getPlayer().getUniqueId().equals(claim.getOwner())) return;

        database.updateClaim(claim);
        

    }

    
}
