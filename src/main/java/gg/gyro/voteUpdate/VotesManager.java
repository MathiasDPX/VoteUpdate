package gg.gyro.voteUpdate;

import gg.gyro.localeAPI.Locales;
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
    private static Locales locales;

    public VotesManager(Vote option1, Vote option2) {
        this.plugin = VoteUpdate.getInstance();
        this.locales = Locales.getInstance();
        this.option1 = option1;
        this.option2 = option2;
        this.votes = new HashMap<>();
        this.gui = Bukkit.createInventory(null, 9, Component.text(locales.get("gui.title")));

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
        List<Component> lore = new ArrayList<>(TextReducer.reduceText(vote.getDescription(), 25));
        lore.add(Component.empty());
        lore.add(Component.text(locales.get("gui.click_to_vote")).color(NamedTextColor.YELLOW));

        meta.lore(lore);

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
            voter.sendMessage(locales.get("votes.success_vote").replace("%s", String.valueOf(option)));
        } else {
            voter.sendMessage(locales.get("votes.already_voted"));
        }
    }

    private void showResults() {
        int votesOption1 = (int) votes.values().stream().filter(v -> v == 1).count();
        int votesOption2 = (int) votes.values().stream().filter(v -> v == 2).count();

        String resultMessage = locales.get("votes.result_title")+"\n" +
                locales.get("votes.result_vote").replace("%name%", option1.getName()).replace("%amount%", String.valueOf(votesOption1))+"\n" +
                locales.get("votes.result_vote").replace("%name%", option2.getName()).replace("%amount%", String.valueOf(votesOption2))+"\n";

        if (votesOption1 > votesOption2) {
            resultMessage += locales.get("votes.result_winner").replace("%s", option1.getName());
            new BukkitRunnable() {@Override public void run() {option1.apply();}}.runTaskAsynchronously(VoteUpdate.getInstance());
        } else if (votesOption2 > votesOption1) {
            resultMessage += locales.get("votes.result_winner").replace("%s", option2.getName());
            new BukkitRunnable() {@Override public void run() {option2.apply();}}.runTaskAsynchronously(VoteUpdate.getInstance());
        } else {
            resultMessage += locales.get("votes.result_tie");
        }

        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendMessage(resultMessage);
        }
    }
}