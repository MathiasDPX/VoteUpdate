package gg.gyro.voteUpdate;

import gg.gyro.voteUpdate.utils.TextReducer;
import gg.gyro.voteUpdate.utils.Vote;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class VotesManager implements Listener {
    private final JavaPlugin plugin;
    private final Vote option1;
    private final Vote option2;
    private final Map<UUID, Integer> votes;
    private final Inventory gui;

    public VotesManager(Vote option1, Vote option2) {
        this.plugin = VoteUpdate.getInstance();
        this.option1 = option1;
        this.option2 = option2;
        this.votes = new HashMap<>();
        this.gui = Bukkit.createInventory(null, 9, Component.text("Vote Time!"));

        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        proposeVote();
    }

    public void proposeVote() {
        setupGUI();

        for (Player player : Bukkit.getOnlinePlayers()) {
            player.openInventory(gui);
        }

        new BukkitRunnable() {
            @Override
            public void run() {
                showResults();
            }
        }.runTaskLater(plugin, 20 * 30); // 30 seconds
    }

    private void setupGUI() {
        ItemStack option1Item = createVoteItem(option1);
        ItemStack option2Item = createVoteItem(option2);

        gui.setItem(3, option1Item);
        gui.setItem(5, option2Item);
    }

    private ItemStack createVoteItem(Vote vote) {
        ItemStack item = vote.getIcon().clone();
        ItemMeta meta = item.getItemMeta();

        meta.displayName(Component.text(vote.getName()).color(NamedTextColor.GOLD));
        meta.lore(List.of(
                Component.text(TextReducer.reduceText(vote.getDescription(), 25)),
                Component.empty(),
                Component.text("Click to vote!").color(NamedTextColor.YELLOW)
        ));

        item.setItemMeta(meta);
        return item;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!event.getInventory().equals(gui)) return;
        event.setCancelled(true);

        Player voter = (Player) event.getWhoClicked();
        int slot = event.getSlot();

        if (slot == 3) {
            handleVote(voter, 1);
        } else if (slot == 5) {
            handleVote(voter, 2);
        } else {
            return;
        }

        voter.closeInventory();
    }

    private void handleVote(Player voter, int option) {
        if (!votes.containsKey(voter.getUniqueId())) {
            voter.playSound(voter.getEyeLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, 1, 1);
            votes.put(voter.getUniqueId(), option);
            voter.sendMessage("You voted for option " + option);
        } else {
            voter.sendMessage("You have already voted!");
        }
    }

    private void showResults() {
        int votesOption1 = (int) votes.values().stream().filter(v -> v == 1).count();
        int votesOption2 = (int) votes.values().stream().filter(v -> v == 2).count();

        String resultMessage = "§6Voting Results:\n" +
                "§e" + option1.getName() + ": §f" + votesOption1 + " votes\n" +
                "§e" + option2.getName() + ": §f" + votesOption2 + " votes\n";

        if (votesOption1 > votesOption2) {
            resultMessage += "§a" + option1.getName() + " wins!";
            option1.apply();
        } else if (votesOption2 > votesOption1) {
            resultMessage += "§a" + option2.getName() + " wins!";
            option2.apply();
        } else {
            resultMessage += "§6It's a tie!";
        }

        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendMessage(resultMessage);
        }
    }
}