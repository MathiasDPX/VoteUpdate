package gg.gyro.voteUpdate.utils;

import gg.gyro.voteUpdate.votes.*;
import java.util.*;

public class Votes {
    static List<Vote> votes = new ArrayList<>();

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
                new EggFree()
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
        Random random = new Random();
        return votes.get(random.nextInt(votes.size()));
    }

    public static List<Vote> getVotes() {
        return votes;
    }
}
