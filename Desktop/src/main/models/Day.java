package main.models;

import lombok.Getter;

import static main.utils.Constants.*;

/**
 * Represents a weekday with its open/closed status.
 */
public class Day {

    /** Weekday name. */
    @Getter private String name;

    /** Open/closed status. */
    @Getter private boolean open;

    /**
     * Constructs a {@link Day}.
     *
     * @param name weekday name as defined in constants
     * @param open whether the cinema is open on this day
     * @throws IllegalArgumentException if the name is not a valid weekday
     */
    public Day(String name, boolean open) {
        if (!WEEKDAY_MAP.containsKey(name)) {
            throw new IllegalArgumentException("Invalid day name: " + name);
        }

        this.name = name;
        this.open = open;
    }

    /**
     * Maps a 1-based index to a weekday name based on insertion order.
     *
     * @param dayNumber 1-based day index
     * @return weekday name
     * @throws IllegalArgumentException if the index is invalid
     */
    public static String getDayNameFromNumber(int dayNumber) {
        if (dayNumber < 1 || dayNumber > WEEKDAY_MAP.size()) {
            return null;
        }

        return WEEKDAY_MAP.keySet()
            .stream()
            .skip(dayNumber - 1)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Invalid day number: " + dayNumber));
    }

}
