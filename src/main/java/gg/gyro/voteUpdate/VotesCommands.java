package gg.gyro.voteUpdate;

import gg.gyro.localeAPI.Locales;
import gg.gyro.voteUpdate.utils.Vote;
import gg.gyro.voteUpdate.utils.Votes;
import org.bukkit.command.CommandSender;
import org.bukkit.scheduler.BukkitRunnable;
import revxrsal.commands.annotation.AutoComplete;
import revxrsal.commands.annotation.Command;
import revxrsal.commands.annotation.Named;

public class VotesCommands {
    Locales locales;

    public VotesCommands() {
        locales = Locales.getInstance();
    }

    @Command("votes ask")
    @AutoComplete("@votes @votes")
    public void ask(CommandSender sender, @Named("option1") String vote1name, @Named("option2") String vote2name) {
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

    @Command("votes force")
    @AutoComplete("@votes")
    public void force(CommandSender sender, @Named("force") String forcedVote) {
        Vote vote = Votes.getVoteFromString(forcedVote);
        if (vote == null) {
            sender.sendMessage(locales.get("commands.vote_notfound").replace("%s",forcedVote));
            return;
        }

        sender.sendMessage(locales.get("commands.force_voteresult").replace("%s",vote.getName()));
        new BukkitRunnable() {@Override public void run() {vote.apply();}}.runTaskAsynchronously(VoteUpdate.getInstance());
    }
}
