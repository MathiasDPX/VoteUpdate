package gg.gyro.voteUpdate.votes;

import gg.gyro.localeAPI.Locales;
import gg.gyro.voteUpdate.VoteUpdate;
import gg.gyro.voteUpdate.customitems.Baguette;
import gg.gyro.voteUpdate.customitems.Tricolore;
import gg.gyro.voteUpdate.utils.Vote;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class FrenchMode extends Vote {
    @Override
    public ItemStack getIcon() {
        return new Tricolore().getBest();
    }

    @Override
    public String getName() {
        return Locales.get("options.french_mode.name");
    }

    @Override
    public String getDescription() {
        return Locales.get("options.french_mode.description");
    }

    @Override
    public void apply() {
        Locales.initialize(VoteUpdate.getInstance(), "fr_fr");
        ItemStack baguette = new Baguette().getBest();
        ItemStack tricolore = new Tricolore().getBest();

        for (Player player: Bukkit.getOnlinePlayers()) {
            player.getInventory().addItem(baguette.clone(), tricolore.clone());
        }
    }
}
