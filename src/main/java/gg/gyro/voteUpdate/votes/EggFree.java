package gg.gyro.voteUpdate.votes;

import gg.gyro.localeAPI.Locales;
import gg.gyro.voteUpdate.VoteUpdate;
import gg.gyro.voteUpdate.utils.Vote;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class EggFree extends Vote {
    @Override
    public ItemStack getIcon() {
        return new ItemStack(Material.EGG);
    }

    @Override
    public String getName() {
        return Locales.getInstance().get("options.egg_free.name");
    }

    @Override
    public String getDescription() {
        return Locales.getInstance().get("options.egg_free.description");
    }

    @Override
    public void apply() {
        Material material = Material.values()[new Random().nextInt(Material.values().length)];
        VoteUpdate.registerEvents(new gg.gyro.voteUpdate.listeners.EggFree(material));
    }
}
