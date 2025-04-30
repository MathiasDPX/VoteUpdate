package gg.gyro.voteUpdate.votes;

import gg.gyro.localeAPI.Locales;
import gg.gyro.voteUpdate.VoteUpdate;
import gg.gyro.voteUpdate.utils.Vote;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class KeepFriendsClose extends Vote {

    @Override
    public ItemStack getIcon() {
        return new ItemStack(Material.LEAD);
    }

    @Override
    public String getName() {
        return Locales.get("options.keep_friends_close.name");
    }

    @Override
    public String getDescription() {
        return Locales.get("options.keep_friends_close.description");
    }

    @Override
    public void apply() {
        // Schedule random teleportations
        new BukkitRunnable() {
            @Override
            public void run() {
                List<Player> onlinePlayers = new ArrayList<>(Bukkit.getOnlinePlayers());

                for (Player player : onlinePlayers) {
                    List<Player> otherPlayers = onlinePlayers.stream()
                            .filter(p -> !p.equals(player))
                            .collect(Collectors.toList());

                    if (!otherPlayers.isEmpty()) {
                        Player closestPlayer = findRandomPlayer(otherPlayers);

                        if (closestPlayer != null) {
                            player.teleport(closestPlayer.getLocation());
                        }
                    }
                }
            }
        }.runTaskTimer(VoteUpdate.getInstance(), 0L, getRandomInterval());
    }

    private Player findRandomPlayer(List<Player> players) {
        return players.get(ThreadLocalRandom.current().nextInt(players.size()));
    }

    private long getRandomInterval() {
        // Random interval between 30 seconds and 2 minutes (in server ticks)
        // 1 second = 20 ticks
        int minSeconds = 30;
        int maxSeconds = 120;
        int randomSeconds = ThreadLocalRandom.current().nextInt(minSeconds, maxSeconds + 1);
        return randomSeconds * 20L;
    }
}