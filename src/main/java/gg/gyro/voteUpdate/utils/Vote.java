package gg.gyro.voteUpdate.utils;

import org.bukkit.inventory.ItemStack;

public abstract class Vote {
    public Vote() {}

    public String getLocaleRoot() {
        return "options." + getId() + ".";
    }

    public abstract String getId();
    public abstract ItemStack getIcon();
    public abstract String getName();
    public abstract String getDescription();

    public abstract void apply();
}
