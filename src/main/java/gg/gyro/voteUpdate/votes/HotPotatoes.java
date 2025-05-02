package gg.gyro.voteUpdate.votes;

import gg.gyro.localeAPI.Locales;
import gg.gyro.voteUpdate.VoteUpdate;
import gg.gyro.voteUpdate.utils.Vote;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class HotPotatoes extends Vote {
    @Override
    public String getId() {
        return "hotpotatoes";
    }

    @Override
    public ItemStack getIcon() {
        return new ItemStack(Material.BAKED_POTATO);
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
        Bukkit.getScheduler().runTaskTimer(VoteUpdate.getInstance(), () -> {
            Bukkit.getOnlinePlayers().forEach(player -> {
                if (player.getInventory().contains(Material.BAKED_POTATO)) {
                    player.damage(1);
                }
            });
        }, 0, 20);
    }
}
