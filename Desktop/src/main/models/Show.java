package main.models;

import lombok.Getter;

/**
 * Represents a scheduled screening of a movie at a specific time.
 */
public class Show {

    /** Show identifier (day + show number). */
    @Getter private String id;

    /** Start time formatted as HH:mm. */
    @Getter private String time;

    /** Associated movie. */
    @Getter private Movie movie;

    /**
     * Constructs a {@link Show}.
     *
     * @param id show identifier (day + show number)
     * @param time formatted start time (HH:mm)
     * @param movie associated movie
     */
    public Show(String id, String time, Movie movie) {
        this.id = id;
        this.time = time;
        this.movie = movie;
    }

}
