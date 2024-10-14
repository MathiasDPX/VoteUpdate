package gg.gyro.voteUpdate.votes;

import gg.gyro.localeAPI.Locales;
import gg.gyro.voteUpdate.VoteUpdate;
import gg.gyro.voteUpdate.customitems.Tricolore;
import gg.gyro.voteUpdate.utils.Vote;
import org.bukkit.inventory.ItemStack;

public class FrenchMode extends Vote {
    @Override
    public ItemStack getIcon() {
        return new Tricolore().getBest();
    }

    @Override
    public String getName() {
        return Locales.getInstance().get("options.french_mode.name");
    }

    @Override
    public String getDescription() {
        return Locales.getInstance().get("options.french_mode.description");
    }

    @Override
    public void apply() {
        new Locales(VoteUpdate.getInstance(), "fr_fr");
    }
}
