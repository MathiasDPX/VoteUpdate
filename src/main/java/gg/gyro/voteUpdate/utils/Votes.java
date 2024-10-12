package gg.gyro.voteUpdate.utils;

import gg.gyro.voteUpdate.votes.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Votes {
    static List<Vote> votes = new ArrayList<>();

    public Votes() {
        votes.add(new AlwaysFlying());
        votes.add(new ChargedCreeper());
        votes.add(new AdvancedAI());
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
