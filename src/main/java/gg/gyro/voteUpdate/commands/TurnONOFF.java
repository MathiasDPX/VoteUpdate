package gg.gyro.voteUpdate.commands;

import gg.gyro.localeAPI.Locales;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import revxrsal.commands.annotation.AutoComplete;
import revxrsal.commands.annotation.Command;
import revxrsal.commands.annotation.Description;
import revxrsal.commands.bukkit.annotation.CommandPermission;

public class TurnONOFF {
    public static boolean isVoteOn = true;

    @Command("votes on")
    @AutoComplete("@votes")
    @Description("Enable votes")
    @CommandPermission("votes.commands.onoff")
    void turn_on(CommandSender sender) {
        String lang = Locales.getDefaultLocale();
        if (sender instanceof Player player) lang = player.getLocale();

        if (isVoteOn) {
            sender.sendMessage(Locales.get(lang, "commands.onoff.already_on"));
        } else {
            isVoteOn = true;
            sender.sendMessage(Locales.get(lang, "commands.onoff.now_on"));
        }
    }

    @Command("votes off")
    @AutoComplete("@votes")
    @Description("Disable votes")
    @CommandPermission("votes.commands.onoff")
    void turn_off(CommandSender sender) {
        String lang = Locales.getDefaultLocale();
        if (sender instanceof Player player) lang = player.getLocale();

        if (!isVoteOn) {
            sender.sendMessage(Locales.get(lang, "commands.onoff.already_off"));
        } else {
            isVoteOn = false;
            sender.sendMessage(Locales.get(lang, "commands.onoff.now_off"));
        }
    }
}
