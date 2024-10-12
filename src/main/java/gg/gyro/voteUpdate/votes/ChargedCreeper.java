package gg.gyro.voteUpdate.votes;

import gg.gyro.voteUpdate.VoteUpdate;
import gg.gyro.voteUpdate.utils.Vote;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ChargedCreeper extends Vote {
    @Override
    public ItemStack getIcon() {
        return new ItemStack(Material.CREEPER_HEAD);
    }

    @Override
    public String getName() {
        return "Charged Creeper";
    }

    @Override
    public String getDescription() {
        return "Every new Creeper while be charged";
    }

    @Override
    public void apply() {
        VoteUpdate.registerEvents(new gg.gyro.voteUpdate.listeners.ChargedCreeper());
    }
}
