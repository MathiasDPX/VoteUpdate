package gg.gyro.voteUpdate.votes;

import gg.gyro.localeAPI.Locales;
import gg.gyro.voteUpdate.VoteUpdate;
import gg.gyro.voteUpdate.utils.Skull;
import gg.gyro.voteUpdate.utils.Vote;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Random;

public class AdvancedAI extends Vote {

    @Override
    public String getId() {
        return "advanced_ai";
    }

    @Override
    public ItemStack getIcon() {
        return Skull.getCustomSkull("https://textures.minecraft.net/texture/b369f1369958234d17e6b8c1ebf301475ab992f1a02bb946cf369894f61d1a53");
    }

    @Override
    public String getName() {
        return Locales.get(getLocaleRoot()+"name");
    }

    @Override
    public String getDescription() {
        return Locales.get(getLocaleRoot()+"description");
    }

    private void startBipBoopTask() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    sendBipBoop(player);
                }
            }
        }.runTaskTimer(VoteUpdate.getInstance(), 0L, 20L);
    }

    private void sendBipBoop(Player player) {
        Random random = new Random();
        int delay = random.nextInt(300)+60;

        new BukkitRunnable() {
            @Override
            public void run() {
                player.chat("blip-blop");
            }
        }.runTaskLater(VoteUpdate.getInstance(), delay * 20L);
    }

    @Override
    public void apply() {
        ArrayList<Player> players = new ArrayList<>(Bukkit.getOnlinePlayers());
        int random = new Random().nextInt(players.size());
        Player player = players.get(random);

        Bukkit.getServer().broadcast(Component.text(Locales.get(getLocaleRoot()+"broadcast").replace("%s", ((TextComponent) player.displayName()).content())));

        player.displayName(Component.text("§7[BOT]§r ").append(player.displayName()));

        startBipBoopTask();
    }
}
