package gg.gyro.voteUpdate.utils;

import org.bukkit.inventory.ItemStack;

public abstract class Vote {
    private final String id;
    public Vote() {
        this.id = getName().toLowerCase().replace(" ", "_");
    }

    public String getId() { return id; }

    public abstract ItemStack getIcon();
    public abstract String getName();
    public abstract String getDescription();

    public abstract void apply();
}
