package gg.gyro.voteUpdate.utils;

import gg.gyro.voteUpdate.VoteUpdate;
import gg.gyro.voteUpdate.votes.*;
import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.*;

public class Votes {
    @Getter static List<Vote> votes = new ArrayList<>();

    private void registerAll(Vote... v) {
        votes.addAll(Arrays.asList(v));
    }

    public Votes() {
        registerAll(
                new AlwaysFlying(),
                new ChargedCreeper(),
                new AdvancedAI(),
                new DefaultSheep(),
                new BedPVP(),
                new BinaryGameruleFlip(),
                new EggFree(),
                new FishAnything(),
                new FrenchMode(),
                new DisableShield(),
                new PermaEffect(),
                new PotGems(),
                new MidasTouch(),
                new GodOfLightning(),
                new TransparentPlayers(),
                new LessGravity()
        );
    }

    public static Vote getById(String id) {
        for (Vote vote : votes) {
            if (vote.getId().equals(id)) return vote;
        }
        return null;
    }

    public static Vote getVoteFromString(String name) {
        if (Objects.equals(name, "random")) {
            return getRandomVote();
        }

        return getById(name);
    }

    public static Vote getRandomVote() {
        FileConfiguration conf = VoteUpdate.getInstance().getConfig();
        Random random = new Random();
        Vote vote = votes.get(random.nextInt(votes.size()));
        if (!conf.getStringList("disabled_votes").contains(vote.getId())) {
            return getRandomVote();
        }
        return vote;
    }
}
