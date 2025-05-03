package gg.gyro.voteUpdate.commands;

import gg.gyro.localeAPI.Locales;
import gg.gyro.voteUpdate.VoteUpdate;
import gg.gyro.voteUpdate.VotesManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import revxrsal.commands.annotation.*;
import revxrsal.commands.bukkit.annotation.CommandPermission;

public class VoteCommand {
    @Command("vote")
    @Description("Open the vote GUI")
    @CommandPermission("votes.commands.vote")
    public void voteCommand(CommandSender sender) {
        if (!VoteUpdate.getInstance().getConfig().getBoolean("optional", false)) {
            sender.sendMessage(Component.text(Locales.get("commands.open_vote")).color(NamedTextColor.RED));
            return;
        }

        if (!(sender instanceof Player player)) {
            sender.sendMessage(Component.text(Locales.get("commands.sender_not_player")).color(NamedTextColor.RED));
            return;
        }

        Inventory gui = VotesManager.getGui();

        if (gui == null) {
            sender.sendMessage(Component.text(Locales.get("commands.no_vote_running")).color(NamedTextColor.RED));
        } else {
            player.openInventory(gui);
        }
    }
}
