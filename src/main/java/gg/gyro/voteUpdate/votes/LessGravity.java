package gg.gyro.voteUpdate.votes;

import gg.gyro.localeAPI.Locales;
import gg.gyro.voteUpdate.utils.Vote;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class LessGravity extends Vote {
    @Override
    public String getId() {
        return "less_gravity";
    }

    @Override
    public ItemStack getIcon() {
        return new ItemStack(Material.RED_BED);
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
        for (Player player: Bukkit.getOnlinePlayers()) {
            player.getAttribute(Attribute.GRAVITY).setBaseValue(0.005);
        }
    }
}
