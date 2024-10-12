package gg.gyro.voteUpdate.votes;

import gg.gyro.voteUpdate.VoteUpdate;
import gg.gyro.voteUpdate.utils.Vote;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Random;

public class AdvancedAI extends Vote {

    @Override
    public ItemStack getIcon() {
        return new ItemStack(Material.CREEPER_HEAD);
    }

    @Override
    public String getName() {
        return "Advanced AI";
    }

    @Override
    public String getDescription() {
        return "Replace a random player with an advanced AI bot";
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
        int delay = random.nextInt(300);

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

        Bukkit.getServer().broadcast(player.displayName().append(Component.text(" has became a advanced AI bot")));

        player.displayName(Component.text("[BOT] ").append(player.displayName()));

        startBipBoopTask();
    }
}
