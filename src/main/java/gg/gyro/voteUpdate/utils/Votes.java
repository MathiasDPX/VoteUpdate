package gg.gyro.voteUpdate.utils;

import gg.gyro.voteUpdate.listeners.AlwaysFlying;
import gg.gyro.voteUpdate.listeners.ChargedCreeper;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Votes {
    static List<Vote> votes = new ArrayList<>();

    public Votes() {
        votes.add(new Vote(Material.CREEPER_HEAD, "Charged Creepers", "All newly spawned creepers are charged.", new ChargedCreeper()));
        votes.add(new Vote(Material.PHANTOM_MEMBRANE, "Always Flying", "According to all known laws of aviation, there is no way a mob should be able to walk", new AlwaysFlying()));
    }

    public static Vote getById(String id) {
        for (Vote vote : votes) {
            if (vote.getId().equals(id)) return vote;
        }
        return null;
    }

    public static Vote getVoteFromString(String name) {
        if (Objects.equals(name, "?")) {
            return getRandomVote();
        }

        return getById(name);
    }

    public static Vote getRandomVote() {
        Random random = new Random();
        return votes.get(random.nextInt(votes.size()));
    }

    public static List<Vote> getVotes() {
        return votes;
    }
}
