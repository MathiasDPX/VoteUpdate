package gg.gyro.voteUpdate.votes;

import gg.gyro.localeAPI.Locales;
import gg.gyro.voteUpdate.utils.Skull;
import gg.gyro.voteUpdate.utils.Vote;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BinaryGameruleFlip extends Vote {
    List<GameRule> binary_gamerules = new ArrayList<>();

    public BinaryGameruleFlip() {
        for (GameRule rule : GameRule.values()) {
            if (rule.getType().equals(Boolean.class)) {
                binary_gamerules.add(rule);
            }
        }
    }

    @Override
    public ItemStack getIcon() {
        return Skull.getCustomSkull("https://textures.minecraft.net/texture/dcafbac5068197fda18636dfc4ce7f9df5af9b2a06e6f91e38ae35e4c435b2df");
    }

    @Override
    public String getName() {
        return Locales.getInstance().get("options.binary_gamerule_flip.name");
    }

    @Override
    public String getDescription() {
        return Locales.getInstance().get("options.binary_gamerule_flip.description");
    }

    @Override
    public void apply() {
        GameRule choosed = binary_gamerules.get(new Random().nextInt(binary_gamerules.size()));

        World ow = Bukkit.getWorld("world");
        assert ow != null;
        Boolean def = (Boolean) ow.getGameRuleValue(choosed);
        ow.setGameRule(choosed, !def);

        String msg = Locales.getInstance().get("options.binary_gamerule_flip.broadcast")
                        .replace("%gr%", choosed.getName());

        if (!def) {
            msg = msg.replace("%value%", Locales.getInstance().get("options.binary_gamerule_flip.true"));
        } else {
            msg = msg.replace("%value%", Locales.getInstance().get("options.binary_gamerule_flip.false"));
        }

        Bukkit.getServer().broadcast(Component.text(msg));
    }
}
