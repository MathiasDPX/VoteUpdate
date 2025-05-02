package gg.gyro.voteUpdate.votes;

import gg.gyro.localeAPI.Locales;
import gg.gyro.voteUpdate.VoteUpdate;
import gg.gyro.voteUpdate.listeners.PlayerDropSkull;
import gg.gyro.voteUpdate.utils.Skull;
import gg.gyro.voteUpdate.utils.Vote;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class PlayerSkull extends Vote {
    @Override
    public String getId() {
        return "playerskull";
    }

    @Override
    public ItemStack getIcon() {
        return Skull.getCustomSkull("https://textures.minecraft.net/texture/eb7f5761f4ca72452dc18ea218db149020f7fcb148431547a0aab44d54b6cc74");
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
        VoteUpdate.registerEvents(new PlayerDropSkull());
    }
}
