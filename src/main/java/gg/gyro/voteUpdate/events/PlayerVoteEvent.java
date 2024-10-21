package gg.gyro.voteUpdate.events;

import gg.gyro.voteUpdate.utils.Vote;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * Event called when a player vote
 */
@Getter public class PlayerVoteEvent extends Event {
    private static final HandlerList HANDLERS = new HandlerList();

    @NotNull private final Vote choice;
    @NotNull private final Player player;

    public PlayerVoteEvent(@NotNull Vote choice, @NotNull Player player) {
        this.player = player;
        this.choice = choice;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }
}
