package gg.gyro.voteUpdate.votes;

import gg.gyro.localeAPI.Locales;
import gg.gyro.voteUpdate.utils.Skull;
import gg.gyro.voteUpdate.utils.Vote;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Random;

public class KickMessage extends Vote {
    public List<String> messages = List.of(
            "400: Bad Request",
            "401: Unauthorized",
            "402: Payment Required",
            "403: Forbidden",
            "404: Not Found",
            "405: Method Not Allowed",
            "406: Not Acceptable",
            "407: Proxy Authentication Required",
            "408: Request Timeout",
            "409: Conflict",
            "410: Gone",
            "411: Length Required",
            "412: Precondition Failed",
            "413: Payload Too Large",
            "414: URI Too Long",
            "415: Unsupported Media Type",
            "416: Range Not Satisfiable",
            "417: Expectation Failed",
            "418: I'm a teapot",
            "421: Misdirected Request",
            "422: Unprocessable Content",
            "423: Locked",
            "424: Failed Dependency",
            "425: Too Early",
            "426: Upgrade Required",
            "428: Precondition Required",
            "429: Too Many Requests",
            "431: Request Header Fields Too Large",
            "451: Unavailable For Legal Reasons"
    );

    @Override
    public String getId() {
        return "http_message";
    }

    @Override
    public ItemStack getIcon() {
        return Skull.getCustomSkull("https://textures.minecraft.net/texture/dcf2835180cbfec3b317d6a47491a74ae71435ba169a57925b9096ea2f9c61b6");
    }

    @Override
    public String getName() {
        return Locales.get(getLocaleRoot()+"name");
    }

    @Override
    public String getDescription() {
        return Locales.get(getLocaleRoot()+"description");
    }

    @Override
    public void apply() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            String message = messages.get(new Random().nextInt(messages.size()));
            player.kick(Component.text(message).color(NamedTextColor.RED));
        }
    }
}
