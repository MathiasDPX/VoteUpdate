package gg.gyro.voteUpdate.events;

import gg.gyro.voteUpdate.utils.Vote;
import lombok.Getter;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * Event called when a vote is applied
 * Vote force command and automatic vote
 * Difference between VoteStartEvent and ApplyVoteEvent
 * - VoteEndEvent is triggered when a vote ends
 * - ApplyVoteEvent is triggered when a vote is forced or when a vote is automatically started
 */
@Getter public class ApplyVoteEvent extends Event {
    public enum ApplyCause {
        /**
         * Triggered by /vote force
         */
        FORCE,

        /**
         * Triggered by automatic vote
         */
        AUTOMATIC
    }

    @NotNull private final Vote vote;
    @NotNull private final ApplyCause cause;

    public ApplyVoteEvent(@NotNull Vote vote, @NotNull ApplyCause cause) {
        this.vote = vote;
        this.cause = cause;
    }

    private static final HandlerList HANDLERS = new HandlerList();

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }
}
