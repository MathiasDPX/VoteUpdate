package gg.gyro.voteUpdate.votes;

import gg.gyro.localeAPI.Locales;
import gg.gyro.voteUpdate.utils.Vote;
import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.inventory.ItemStack;

public class RemovePhantoms extends Vote {

    @Override
    public String getId() {
        return "remove_phantoms";
    }

    @Override
    public ItemStack getIcon() {
        return new ItemStack(Material.PHANTOM_MEMBRANE);
    }

    @Override
    public String getName() {
        return Locales.get(getLocaleRoot()+"name");
    }

    @Override
    public String getDescription() {
        return Locales.get(getLocaleRoot()+"description");
    }

    @Override
    public void apply() {
        World world = Bukkit.getWorld("world");
        world.setGameRule(GameRule.DO_INSOMNIA, false);
    }
}
