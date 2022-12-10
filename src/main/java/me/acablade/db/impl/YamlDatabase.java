package me.acablade.db.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import lombok.SneakyThrows;
import me.acablade.Main;
import me.acablade.db.OzDatabase;
import me.acablade.objects.Claim;
import me.acablade.objects.ConfigurationFile;
import me.acablade.utils.Base64Utils;
import me.acablade.utils.ChunkUtils;

public class YamlDatabase implements OzDatabase {

    private ConfigurationFile configFile;

    /*
     * claims:
     *  id: long
     *   owner: uuid
     *   create-time: long
     *   current-time: long
     *   inventory: base64 string
     * 
     */

    public YamlDatabase(Main plugin){
        this.configFile = new ConfigurationFile(plugin, "data");
        init();
    }

    private void init(){

        YamlConfiguration config = configFile.getConfiguration();

        if(config.isConfigurationSection("claims")) return;

        config.createSection("claims");
    }


    @Override
    public Collection<Claim> getAllClaims() {

        YamlConfiguration config = configFile.getConfiguration();
        if(!config.isConfigurationSection("claims")) return Collections.emptyList();

        List<Claim> claimList = new ArrayList<>();

        for(String key: config.getConfigurationSection("claims").getKeys(false)){
            claimList.add(getClaim(Long.parseLong(key)));
        }

        return claimList;
    }

    @Override
    @SneakyThrows
    public Claim getClaim(long id) {
        // TODO Auto-generated method stub
        YamlConfiguration config = configFile.getConfiguration();
        if(!config.isConfigurationSection("claims")) return null;

        ConfigurationSection allClaimsSection = config.getConfigurationSection("claims");
        if(!allClaimsSection.isConfigurationSection(id + "")) return null;

        ConfigurationSection claimSection = allClaimsSection.getConfigurationSection(id + "");

        UUID owner = UUID.fromString(claimSection.getString("owner"));
        long createTime = claimSection.getLong("create-time");
        long currentTime = claimSection.getLong("current-time");
        ItemStack[] items = Base64Utils.itemStackArrayFromBase64(claimSection.getString("inventory"));
        int[] coords = ChunkUtils.idToChunk(id);

        Claim claim = new Claim(owner, coords[0], coords[1]);
        claim.setCurrentTime(currentTime);
        claim.setStartTime(createTime);
        claim.getInventory().setContents(items);

        return claim;
    }

    @Override
    public void addClaim(Claim claim) {
        // TODO Auto-generated method stub
        YamlConfiguration config = configFile.getConfiguration();
        if(!config.isConfigurationSection("claims")) return;

        ConfigurationSection allClaimsSection = config.getConfigurationSection("claims");
        allClaimsSection.set(claim.getId() + "", null);

        ConfigurationSection claimSection = allClaimsSection.createSection(claim.getId() + "");

        claimSection.set("owner", claim.getOwner().toString());
        claimSection.set("current-time", claim.getCurrentTime());
        claimSection.set("create-time", claim.getStartTime());
        claimSection.set("inventory", Base64Utils.itemStackArrayToBase64(claim.getInventory().getContents()));

        configFile.save();

    }

    @Override
    public void removeClaim(long id) {
        YamlConfiguration config = configFile.getConfiguration();
        if(!config.isConfigurationSection("claims")) return;

        ConfigurationSection allClaimsSection = config.getConfigurationSection("claims");
        allClaimsSection.set(id + "", null);

        configFile.save();
    }

    @Override
    public void updateClaim(Claim claim) {
        YamlConfiguration config = configFile.getConfiguration();
        if(!config.isConfigurationSection("claims")) return;

        ConfigurationSection allClaimsSection = config.getConfigurationSection("claims");
        if(!allClaimsSection.contains(claim.getId() + "")) return;

        ConfigurationSection claimSection = allClaimsSection.getConfigurationSection(claim.getId() + "");

        claimSection.set("owner", claim.getOwner().toString());
        claimSection.set("current-time", claim.getCurrentTime());
        claimSection.set("create-time", claim.getStartTime());
        claimSection.set("inventory", Base64Utils.itemStackArrayToBase64(claim.getInventory().getContents()));

        configFile.save();
        
    }
    
}
