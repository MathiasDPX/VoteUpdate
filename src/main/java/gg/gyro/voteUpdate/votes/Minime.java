package gg.gyro.voteUpdate.votes;

import gg.gyro.localeAPI.Locales;
import gg.gyro.voteUpdate.VoteUpdate;
import gg.gyro.voteUpdate.utils.Vote;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Minime extends Vote {
    @Override
    public ItemStack getIcon() {
        return new ItemStack(Material.CHERRY_SLAB);
    }

    @Override
    public String getName() {
        return Locales.get("options.minime.name");
    }

    @Override
    public String getDescription() {
        return Locales.get("options.minime.description");
    }

    public static void minimizePlayer(Player player) {
        AttributeInstance attribute = player.getAttribute(Attribute.SCALE);
        if (attribute != null) {
            attribute.setBaseValue(0.5);
        }
    }

    @Override
    public void apply() {
        for (Player player: Bukkit.getOnlinePlayers()) {
            minimizePlayer(player);
        }
        VoteUpdate.registerEvents(new gg.gyro.voteUpdate.listeners.Minime());
    }
}
