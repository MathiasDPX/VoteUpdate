package gg.gyro.voteUpdate.commands;

import gg.gyro.localeAPI.Locales;
import gg.gyro.voteUpdate.VotesManager;
import gg.gyro.voteUpdate.events.VoteStartEvent;
import gg.gyro.voteUpdate.utils.Vote;
import gg.gyro.voteUpdate.utils.Votes;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import revxrsal.commands.annotation.*;
import revxrsal.commands.bukkit.annotation.CommandPermission;

public class AskVote {
    @Command("votes ask")
    @AutoComplete("@votes @votes")
    @Description("Starts a vote between two options")
    @CommandPermission("votes.commands.ask")
    public void ask(CommandSender sender, @Named("option1") @Default("random") String vote1name, @Named("option2") @Default("random") String vote2name) {
        Vote vote1 = Votes.getVoteFromString(vote1name);
        Vote vote2 = Votes.getVoteFromString(vote2name);

        if (vote1 == null) {
            sender.sendMessage(Locales.get("commands.vote_notfound").replace("%s",vote1name));
            return;
        }
        if (vote2 == null) {
            sender.sendMessage(Locales.get("commands.vote_notfound").replace("%s",vote2name));
            return;
        }

        Bukkit.getPluginManager().callEvent(new VoteStartEvent(vote1, vote2, VoteStartEvent.StartCause.FORCE));
        try {
            new VotesManager(vote1, vote2);
        } catch (Exception e) {
            sender.sendMessage(Component.text(Locales.get("commands.already_running")).color(NamedTextColor.RED));
        }
    }
}
