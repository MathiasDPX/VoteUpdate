package gg.gyro.voteUpdate.utils;

import lombok.Getter;
import org.reflections.Reflections;

import java.util.*;

public class Votes {
    @Getter static List<Vote> votes = new ArrayList<>();

    public Votes() {
        Reflections reflections = new Reflections("gg.gyro.voteUpdate.votes");
        Set<Class<? extends Vote>> votes_classz = reflections.getSubTypesOf(Vote.class);
        for (Class<? extends Vote> voteClass : votes_classz) {
            try {
                votes.add(voteClass.getDeclaredConstructor().newInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
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
}
