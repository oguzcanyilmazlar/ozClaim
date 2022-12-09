package me.acablade.objects;

import java.util.UUID;

import org.bukkit.inventory.ItemStack;

import lombok.Data;
import me.acablade.utils.ChunkUtils;
import me.acablade.utils.ItemUtils;

@Data
public class Claim {

    private UUID owner;
    private long id;
    private long startTime;
    private long finishTime;
    private ItemStack[] inventory;

    public Claim(UUID owner, int x, int z){
        this.id = ChunkUtils.getChunkId(x, z);
        this.startTime = System.currentTimeMillis();
        this.inventory = new ItemStack[27];
        this.owner = owner;
    }

    private long timeLeft(){
        long determinedTime = calculateTime();

        return determinedTime + System.currentTimeMillis();
    
    }

    private long calculateTime(){

        long time = 0;

        for (ItemStack itemStack : inventory) {
            time += ItemUtils.getTimeForType(itemStack.getType());
        }

        return time;

    }

    public boolean shouldRemove(){

        return System.currentTimeMillis() > this.finishTime;

    }

    public boolean refresh(){
        setFinishTime(timeLeft());
        return shouldRemove();
    }



    
}
