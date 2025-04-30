package gg.gyro.voteUpdate.votes;

import gg.gyro.localeAPI.Locales;
import gg.gyro.voteUpdate.VoteUpdate;
import gg.gyro.voteUpdate.utils.Vote;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class DefaultSheep extends Vote {

    @Override
    public ItemStack getIcon() {
        return new ItemStack(Material.SHEEP_SPAWN_EGG);
    }

    @Override
    public String getName() {
        return Locales.get("options.default_sheep.name");
    }

    @Override
    public String getDescription() {
        return Locales.get("options.default_sheep.description");
    }

    @Override
    public void apply() {
        VoteUpdate.registerEvents(new gg.gyro.voteUpdate.listeners.DefaultSheep());
    }
}
