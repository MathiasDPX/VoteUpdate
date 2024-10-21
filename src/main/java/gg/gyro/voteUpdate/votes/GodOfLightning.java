package gg.gyro.voteUpdate.votes;

import gg.gyro.localeAPI.Locales;
import gg.gyro.voteUpdate.VoteUpdate;
import gg.gyro.voteUpdate.utils.Vote;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class GodOfLightning extends Vote {
    @Override
    public ItemStack getIcon() {
        return new ItemStack(Material.LIGHTNING_ROD);
    }

    @Override
    public String getName() {
        return Locales.getInstance().get("options.god_of_lightning.name");
    }

    @Override
    public String getDescription() {
        return Locales.getInstance().get("options.god_of_lightning.description");
    }

    @Override
    public void apply() {
        VoteUpdate.registerEvents(new gg.gyro.voteUpdate.listeners.GodOfLightning());
    }
}
