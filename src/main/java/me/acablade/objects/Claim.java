package me.acablade.objects;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import lombok.Data;
import me.acablade.manager.ClaimManager;
import me.acablade.utils.ChatUtils;
import me.acablade.utils.ChunkUtils;
import me.acablade.utils.ItemUtils;

@Data
public class Claim implements InventoryHolder {

    private UUID owner;
    private long id;
    private long startTime;
    private long currentTime;
    private Inventory inventory;

    // 86400 = 24 saat

    public Claim(UUID owner, int x, int z){
        this.id = ChunkUtils.getChunkId(x, z);
        this.startTime = System.currentTimeMillis();
        this.currentTime = 86400 * 1000;
        this.inventory = Bukkit.createInventory(this, 27);
        this.owner = owner;
    }

    private long calculateTime(){

        long time = 0;

        for (ItemStack itemStack : inventory.getStorageContents()) {
            if(itemStack==null) continue;
            itemStack.setAmount(itemStack.getAmount() - 1);
            time += ItemUtils.getTimeForType(itemStack.getType());
        }

        return time;

    }

    private long getItemPower(){
        long time = 0;

        for (ItemStack itemStack : inventory) {
            if(itemStack==null) continue;
            time += ItemUtils.getTimeForType(itemStack.getType()) * itemStack.getAmount();
        }

        return time;
    }

    public long getTotalPower(){
        return getItemPower() + currentTime;
    }

    public boolean shouldRemove(){

        return getTotalPower() <= 0;

    }

    public void tick(){
        setCurrentTime(currentTime+calculateTime());
        currentTime = currentTime - ClaimManager.PERIOD * 1000;
        if(shouldRemove()){
            ChatUtils.broadcast("&c" +this.id + " adlı claimin süresi bitmiştir");
        }
        
    }



    
}
