package gg.gyro.voteUpdate.events;

import gg.gyro.voteUpdate.utils.Vote;
import lombok.Getter;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * Event called when a vote start
 */
@Getter public class VoteStartEvent extends Event {
    public enum StartCause {
        /**
         * Triggered by /vote ask
         */
        FORCE,

        /**
         * Triggered by automatic vote
         */
        AUTOMATIC
    }

    private static final HandlerList HANDLERS = new HandlerList();
    @NotNull private final Vote option1;
    @NotNull private final Vote option2;
    @NotNull private final StartCause cause;

    public VoteStartEvent(@NotNull Vote option1, @NotNull Vote option2, @NotNull StartCause cause) {
        this.option1 = option1;
        this.option2 = option2;
        this.cause = cause;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }
}
