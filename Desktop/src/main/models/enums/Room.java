package main.models.enums;

import lombok.Getter;

/**
 * Logical grouping of screening rooms by genre/category with localized names.
 */
public enum Room {

    /** Family/children room. */
    CHILDREN("Umeen Gela"),

    /** Super-hero themed room. */
    SUPER_HEROES("Super Heroien Gela"),

    /** Thriller themed room. */
    THRILLER("Thriller Gela"),

    /** Science fiction room. */
    SCI_FI("Zientzia Fikzioko Gela"),

    /** Comedy room. */
    COMEDY("Komedia Gela");

    /** Room name. */
    @Getter public final String name;

    private Room(String name) {
        this.name = name;
    }

}
