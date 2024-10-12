 package gg.gyro.voteUpdate;

import gg.gyro.localeAPI.Locales;
import gg.gyro.voteUpdate.utils.Vote;
import gg.gyro.voteUpdate.utils.Votes;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import revxrsal.commands.bukkit.BukkitCommandHandler;

import java.util.ArrayList;
import java.util.List;

public final class VoteUpdate extends JavaPlugin {
    static VoteUpdate instance;

    @Override
    public void onEnable() {
        instance = this;
        new Votes();

        Locales.saveDefaultConfig(this, "en_us.yml");
        new Locales(this, getConfig().getString("language"));
        BukkitCommandHandler handler = BukkitCommandHandler.create(this);

        handler.getAutoCompleter().registerSuggestion("votes", (args, sender, command) -> {
            List<String> suggestions = new ArrayList<>(List.of("random"));
            for (Vote vote: Votes.getVotes()){
                suggestions.add(vote.getId());
            }
            return suggestions;
        });

        handler.register(new VotesCommands());
    }

    public static VoteUpdate getInstance() { return instance; }

    public static void registerEvents(Listener... args) {
        for (Listener listener : args) {
            Bukkit.getServer().getPluginManager().registerEvents(listener, VoteUpdate.getInstance());
        }
    }
}
