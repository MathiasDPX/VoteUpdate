 package gg.gyro.voteUpdate;

import dev.xernas.menulib.MenuLib;
import gg.gyro.localeAPI.Locales;
import gg.gyro.voteUpdate.commands.*;
import gg.gyro.voteUpdate.utils.Vote;
import gg.gyro.voteUpdate.utils.Votes;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import revxrsal.commands.bukkit.BukkitCommandHandler;

import java.util.ArrayList;
import java.util.List;

public final class VoteUpdate extends JavaPlugin {
    @Getter static VoteUpdate instance;

    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();
        Locales.saveDefaultConfig(this, "de_de.yml");
        Locales.saveDefaultConfig(this, "en_us.yml");
        Locales.saveDefaultConfig(this, "fr_fr.yml");
        Locales.saveDefaultConfig(this, "it_it.yml");

        MenuLib.init(this);
        Locales.initialize(this, getConfig().getString("language"));
        Votes.initialize();

        BukkitCommandHandler handler = BukkitCommandHandler.create(this);
        handler.getAutoCompleter().registerSuggestion("votes", (args, sender, command) -> {
            List<String> suggestions = new ArrayList<>(List.of("random"));
            for (Vote vote: Votes.getVotes()){
                suggestions.add(vote.getId());
            }
            return suggestions;
        });

        handler.register(
                new AskVote(),
                new ForceVote(),
                new ListVotes(),
                new UndoCommand(),
                new TurnONOFF()
        );

        if (getConfig().getInt("vote_delay") == 0) {
            getLogger().info("Automatics votes are disabled");
        } else {
            getLogger().info("Automatics votes are enabled");
            new BukkitRunnable() {
                @Override
                public void run() {
                    if (!TurnONOFF.isVoteOn) { return; }
                    new VotesManager(Votes.getRandomVote(), Votes.getRandomVote());
                }
            }.runTaskTimer(this, 600, getConfig().getInt("vote_delay")*20L);
        }
    }

    public static void registerEvents(Listener... args) {
        for (Listener listener : args) {
            Bukkit.getServer().getPluginManager().registerEvents(listener, VoteUpdate.getInstance());
        }
    }
}
