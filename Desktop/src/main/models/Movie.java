package main.models;

import lombok.Getter;
import lombok.Setter;
import main.models.enums.Room;

/**
 * Represents a movie with an id, title, assigned room and sold tickets.
 */
public class Movie {

    /** Internal movie identifier. */
    @Getter private String id;

    /** Human-readable movie title. */
    @Getter private String title;

    /** Assigned screening room. */
    @Getter private Room room;

    /** Number of tickets sold for this movie. */
    @Getter @Setter private int tickets;

    /**
     * Constructs a {@link Movie}.
     *
     * @param id internal movie identifier
     * @param title human-readable movie title
     * @param room assigned screening room
     */
    public Movie(String id, String title, Room room) {
        this.id = id;
        this.title = title;
        this.room = room;

        this.tickets = 0;
    }

    /**
     * Returns a compact representation including id and title.
     */
    @Override
    public String toString() {
        return String.format("(%s) %s", id, title);
    }

}
