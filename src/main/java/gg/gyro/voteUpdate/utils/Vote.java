package gg.gyro.voteUpdate.utils;

import org.bukkit.inventory.ItemStack;

public abstract class Vote {
    public String getId() {
        return getName().toLowerCase().replace(" ", "_");
    }

    public abstract ItemStack getIcon();
    public abstract String getName();
    public abstract String getDescription();

    public abstract void apply();
}
