package gg.gyro.voteUpdate.votes;

import gg.gyro.localeAPI.Locales;
import gg.gyro.voteUpdate.utils.Vote;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

public class PermaEffect extends Vote {
    @Override
    public String getId() {
        return "perma_effect";
    }

    @Override
    public ItemStack getIcon() {
        return new ItemStack(Material.GLASS_BOTTLE);
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
        PotionEffectType type = PotionEffectType.values()[new Random().nextInt(PotionEffectType.values().length)];
        int amplifier = new Random().nextInt(3);
        PotionEffect effect = new PotionEffect(type, -1, amplifier, true, false, false);
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.addPotionEffect(effect);
        }

        Bukkit.getServer().broadcast(Component.text(Locales.get(getLocaleRoot()+"broadcast").replace("%effect%", type.getKey().toString()).replace("%level%", Integer.toString(amplifier))));
    }
}
