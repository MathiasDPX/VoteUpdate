package gg.gyro.voteUpdate.customitems;

import org.bukkit.Bukkit;

public class CustomItemsUtils {
    public static boolean hasItemsAdder() {
        return Bukkit.getPluginManager().getPlugin("ItemsAdder") != null;
    }
}
