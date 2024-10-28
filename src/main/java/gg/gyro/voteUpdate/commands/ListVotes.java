package gg.gyro.voteUpdate.commands;

import gg.gyro.localeAPI.Locales;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import revxrsal.commands.annotation.AutoComplete;
import revxrsal.commands.annotation.Command;
import revxrsal.commands.annotation.Description;
import revxrsal.commands.bukkit.annotation.CommandPermission;

public class ListVotes {
    Locales locales;

    public ListVotes() {
        locales = Locales.getInstance();
    }

    @Command("votes list")
    @AutoComplete("@votes")
    @Description("Lists all votes")
    @CommandPermission("votes.commands.list")
    public void command(CommandSender sender) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(locales.get("commands.sender_not_player"));
            return;
        }

        new ListMenu(player).open();

    }
}
