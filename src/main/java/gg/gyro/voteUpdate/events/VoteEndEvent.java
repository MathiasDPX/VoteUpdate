package gg.gyro.voteUpdate.events;

import gg.gyro.voteUpdate.utils.Vote;
import lombok.Getter;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

import java.util.Set;
import java.util.UUID;

/**
 * Event called when a vote end
 */
@Getter public class VoteEndEvent extends Event {
    private static final HandlerList HANDLERS = new HandlerList();
    @NotNull final Vote winner;
    @NotNull final Vote looser;
    @NotNull final Set<UUID> voters;
    @NotNull final Boolean isTie;

    public VoteEndEvent(@NotNull Vote winner, @NotNull Vote looser, @NotNull Set<UUID> voters, @NotNull Boolean isTie) {
        this.winner = winner;
        this.looser = looser;
        this.voters = voters;
        this.isTie = isTie;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }
}
