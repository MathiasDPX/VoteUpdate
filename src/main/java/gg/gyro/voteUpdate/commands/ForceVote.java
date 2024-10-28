package gg.gyro.voteUpdate.commands;

import gg.gyro.localeAPI.Locales;
import gg.gyro.voteUpdate.VoteUpdate;
import gg.gyro.voteUpdate.events.ApplyVoteEvent;
import gg.gyro.voteUpdate.utils.Vote;
import gg.gyro.voteUpdate.utils.Votes;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import revxrsal.commands.annotation.AutoComplete;
import revxrsal.commands.annotation.Command;
import revxrsal.commands.annotation.Description;
import revxrsal.commands.annotation.Named;
import revxrsal.commands.bukkit.annotation.CommandPermission;

public class ForceVote {
    Locales locales;

    public ForceVote() {
        locales = Locales.getInstance();
    }

    @Command("votes force")
    @AutoComplete("@votes")
    @Description("Forces a vote to be applied")
    @CommandPermission("votes.commands.force")
    public void force(CommandSender sender, @Named("force") String forcedVote) {
        Vote vote = Votes.getVoteFromString(forcedVote);
        if (vote == null) {
            sender.sendMessage(locales.get("commands.vote_notfound").replace("%s",forcedVote));
            return;
        }

        sender.sendMessage(locales.get("commands.force_voteresult").replace("%s",vote.getName()));
        VoteUpdate.getInstance().getLogger().info("Applying "+vote.getId()+" vote");

        Bukkit.getPluginManager().callEvent(new ApplyVoteEvent(vote, ApplyVoteEvent.ApplyCause.FORCE));
        vote.apply();
    }
}
