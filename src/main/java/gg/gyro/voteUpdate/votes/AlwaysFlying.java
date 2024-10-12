package gg.gyro.voteUpdate.votes;

import gg.gyro.voteUpdate.VoteUpdate;
import gg.gyro.voteUpdate.utils.Vote;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class AlwaysFlying extends Vote {
    @Override
    public ItemStack getIcon() {
        return new ItemStack(Material.PHANTOM_MEMBRANE);
    }

    @Override
    public String getName() {
        return "Always Flying";
    }

    @Override
    public String getDescription() {
        return "According to all known laws of aviation, there is no way a mob should be able to walk";
    }

    @Override
    public void apply() {
        VoteUpdate.registerEvents(new gg.gyro.voteUpdate.listeners.AlwaysFlying());
    }
}
