package gg.gyro.voteUpdate;

import gg.gyro.localeAPI.Locales;
import gg.gyro.voteUpdate.events.ApplyVoteEvent;
import gg.gyro.voteUpdate.events.PlayerVoteEvent;
import gg.gyro.voteUpdate.events.VoteEndEvent;
import gg.gyro.voteUpdate.utils.TextReducer;
import gg.gyro.voteUpdate.utils.Vote;
import lombok.Getter;
import lombok.Setter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
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

    @Getter @Setter
    private static UUID president = null;

    public VotesManager(Vote option1, Vote option2) {
        this.plugin = VoteUpdate.getInstance();
        this.option1 = option1;
        this.option2 = option2;
        this.votes = new HashMap<>();
        this.gui = Bukkit.createInventory(null, 9, Component.text(Locales.get("gui.title")));

        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        plugin.getLogger().info("Starting new votes ("+option1.getId()+" or "+option2.getId()+")");
        proposeVote();
    }

    public void proposeVote() {
        setupGUI();

        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.hasPermission("votes.banvote")) continue;
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

        meta.displayName(Component.text(vote.getName()).color(NamedTextColor.GOLD).decoration(TextDecoration.ITALIC, false).decoration(TextDecoration.BOLD, true));
        List<Component> lore = new ArrayList<>(TextReducer.reduceText(vote.getDescription(), 25));
        lore.add(Component.empty());
        lore.add(Component.text(Locales.get("gui.click_to_vote")).color(NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false));

        meta.lore(lore);

        item.setItemMeta(meta);
        return item;
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        if (!event.getInventory().equals(gui)) return;
        if (!(event.getPlayer() instanceof Player player)) return;
        if (!VoteUpdate.getInstance().getConfig().getBoolean("mandatory")) return;

        if (!votes.containsKey(player.getUniqueId())) {
            Bukkit.getScheduler().runTaskLater(VoteUpdate.getInstance(), () -> player.openInventory(gui), 1);
        }
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
            votes.put(voter.getUniqueId(), option);
            voter.playSound(voter.getEyeLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, 1, 1);
            voter.sendMessage(Locales.get("votes.success_vote").replace("%s", String.valueOf(option)));

            if (option == 1) {
                Bukkit.getPluginManager().callEvent(new PlayerVoteEvent(option1, voter));
            } else {
                Bukkit.getPluginManager().callEvent(new PlayerVoteEvent(option2, voter));
            }
        } else {
            voter.sendMessage(Locales.get("votes.already_voted"));
        }
    }

    private void showResults() {
        int votesOption1 = (int) votes.values().stream().filter(v -> v == 1).count();
        int votesOption2 = (int) votes.values().stream().filter(v -> v == 2).count();

        if (votes.getOrDefault(president, 0) == 1) {
            votesOption1++;
        } else if (votes.getOrDefault(president, 0) == 2) {
            votesOption2++;
        }

        String resultMessage = Locales.get("votes.result_title")+"\n" +
                Locales.get("votes.result_vote").replace("%name%", option1.getName()).replace("%amount%", String.valueOf(votesOption1))+"\n" +
                Locales.get("votes.result_vote").replace("%name%", option2.getName()).replace("%amount%", String.valueOf(votesOption2))+"\n";

        if (votesOption1 > votesOption2) {
            triggerEvents(option1, option2);

            resultMessage += Locales.get("votes.result_winner").replace("%s", option1.getName());
            VoteUpdate.getInstance().getLogger().info("Applying "+option1.getId()+" vote");

            option1.apply();
        } else if (votesOption2 > votesOption1) {
            triggerEvents(option2, option1);

            resultMessage += Locales.get("votes.result_winner").replace("%s", option2.getName());
            VoteUpdate.getInstance().getLogger().info("Applying "+option2.getId()+" vote");

            option2.apply();
        } else {
            Bukkit.getPluginManager().callEvent(new VoteEndEvent(option1, option2, votes.keySet(), true));
            resultMessage += Locales.get("votes.result_tie");
        }

        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendMessage(resultMessage);
        }
    }

    private void triggerEvents(Vote win, Vote lose) {
        Bukkit.getPluginManager().callEvent(new VoteEndEvent(win, lose, votes.keySet(), false));
        Bukkit.getPluginManager().callEvent(new ApplyVoteEvent(win, ApplyVoteEvent.ApplyCause.AUTOMATIC));
    }
}