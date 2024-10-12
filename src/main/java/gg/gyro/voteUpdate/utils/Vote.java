package gg.gyro.voteUpdate.utils;

import gg.gyro.voteUpdate.VoteUpdate;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public class Vote {
    ItemStack icon;
    String name;
    String desc;
    Listener listener;

    public Vote(ItemStack icon, String name, String desc, Listener listener) {
        this.icon = icon.clone();
        this.listener = listener;
        this.name = name;
        this.desc = desc;
    }

    public Vote(Material icon, String name, String desc, Listener listener) {
        this(new ItemStack(icon), name, desc, listener);
    }

    public String getId() {return name.toLowerCase().replace(" ", "_");}
    public ItemStack getIcon() {return icon;}
    public String getName() { return name; }
    public String getDescription() { return desc; }

    public void apply() {
        VoteUpdate.getInstance().registerEvents(listener);
    }
}
