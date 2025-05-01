package gg.gyro.voteUpdate.commands;

import gg.gyro.localeAPI.Locales;
import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import revxrsal.commands.annotation.Command;
import revxrsal.commands.annotation.Default;
import revxrsal.commands.annotation.Description;
import revxrsal.commands.bukkit.EntitySelector;
import revxrsal.commands.bukkit.annotation.CommandPermission;

public class UndoCommand {
    @Command("votes undo invisible")
    @CommandPermission("votes.commands.undo.invisible")
    @Description("Makes all players visible")
    public void invisible(CommandSender sender) {
        sender.sendMessage(Locales.get("commands.undo").replace("%vote%", Locales.get("options.transparent_players.name")));
        for (Player player: Bukkit.getOnlinePlayers()) {
            player.setInvisible(false);
        }
    }

    @Command("votes undo minime")
    @CommandPermission("votes.commands.undo.minime")
    @Description("Resets the size of all players to normal")
    public void minime(CommandSender sender, @Default("@p") EntitySelector<Player> players) {
        sender.sendMessage(Locales.get("commands.undo").replace("%vote%", Locales.get("options.minime.name")));
        for (Player player: players) {
            AttributeInstance attribute = player.getAttribute(Attribute.SCALE);
            if (attribute != null) {
                attribute.setBaseValue(1.0);
            }
        }
    }

    @Command("votes undo moon")
    @CommandPermission("votes.commands.undo.moon")
    @Description("Resets gravity to normal")
    public void moon(CommandSender sender) {
        for (Player player: Bukkit.getOnlinePlayers()) {
            player.getAttribute(Attribute.GRAVITY).setBaseValue(0.08);
        }
    }
}
