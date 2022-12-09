package me.acablade.utils;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.NotImplementedException;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;

public class ItemUtils {

    private static Map<Material, Long> map = new HashMap<>();

    public static void init(ConfigurationSection section){
        throw new NotImplementedException("Configden okumayı ayarla");
    }

    public static long getTimeForType(Material material){
        return map.getOrDefault(material, 0L);
    }

    
}
