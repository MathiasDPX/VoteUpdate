package gg.gyro.voteUpdate.listeners;

import gg.gyro.voteUpdate.events.VoteEndEvent;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.meta.FireworkMeta;

public class VotingFireworks implements Listener {
    @EventHandler
    void onVoteEnd(VoteEndEvent event) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            Firework firework = (Firework) player.getWorld().spawnEntity(player.getLocation(), EntityType.FIREWORK_ROCKET);
            FireworkMeta meta = firework.getFireworkMeta();
            meta.setPower(1);
            meta.addEffect(FireworkEffect.builder()
                    .withColor(Color.RED)
                    .withFade(Color.WHITE)
                    .with(FireworkEffect.Type.BALL_LARGE)
                    .trail(true)
                    .build());
            firework.setFireworkMeta(meta);
        }
    }
}
