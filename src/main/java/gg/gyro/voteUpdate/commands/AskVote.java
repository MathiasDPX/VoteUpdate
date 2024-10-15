package gg.gyro.voteUpdate.commands;

import gg.gyro.localeAPI.Locales;
import gg.gyro.voteUpdate.VotesManager;
import gg.gyro.voteUpdate.utils.Vote;
import gg.gyro.voteUpdate.utils.Votes;
import org.bukkit.command.CommandSender;
import revxrsal.commands.annotation.AutoComplete;
import revxrsal.commands.annotation.Command;
import revxrsal.commands.annotation.Default;
import revxrsal.commands.annotation.Named;
import revxrsal.commands.bukkit.annotation.CommandPermission;

public class AskVote {
    Locales locales;

    public AskVote() {
        locales = Locales.getInstance();
    }

    @Command("votes ask")
    @AutoComplete("@votes @votes")
    @CommandPermission("votes.commands.ask")
    public void ask(CommandSender sender, @Named("option1") @Default("random") String vote1name, @Named("option2") @Default("random") String vote2name) {
        Vote vote1 = Votes.getVoteFromString(vote1name);
        Vote vote2 = Votes.getVoteFromString(vote2name);

        if (vote1 == null) {
            sender.sendMessage(locales.get("commands.vote_notfound").replace("%s",vote1name));
            return;
        }
        if (vote2 == null) {
            sender.sendMessage(locales.get("commands.vote_notfound").replace("%s",vote2name));
            return;
        }

        new VotesManager(vote1, vote2);
    }
}
