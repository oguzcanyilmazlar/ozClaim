package me.acablade.container;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import lombok.Getter;
import me.acablade.objects.Claim;

@Getter
public class ClaimContainer implements InventoryHolder {

    private Inventory inventory;
    private Claim claim;

    public ClaimContainer(Claim claim){
        this.claim = claim;
        this.inventory = Bukkit.createInventory(this, 27, "Claim inventory");
        inventory.setContents(claim.getInventory());
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }

}
