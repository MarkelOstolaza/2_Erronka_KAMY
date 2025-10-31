package main.models.enums;

import lombok.Getter;

/**
 * ANSI color codes for colorizing console output.
 */
public enum Color {

    /** Red foreground. */
    RED("\u001B[0;31m"),

    /** Green foreground. */
    GREEN("\u001B[0;32m"),

    /** Blue foreground. */
    BLUE("\u001B[0;34m"),

    /** Yellow foreground. */
    YELLOW("\u001B[0;33m"),

    /** Purple foreground. */
    PURPLE("\u001B[0;35m"),

    /** Cyan foreground. */
    CYAN("\u001B[0;36m"),

    /** Black foreground. */
    BLACK("\u001B[0;30m"),

    /** White foreground. */
    WHITE("\u001B[0;37m"),

    /** Red bold foreground. */
    RED_BOLD("\u001B[1;31m"),

    /** Green bold foreground. */
    GREEN_BOLD("\u001B[1;32m"),

    /** Blue bold foreground. */
    BLUE_BOLD("\u001B[1;34m"),

    /** Yellow bold foreground. */
    YELLOW_BOLD("\u001B[1;33m"),

    /** Purple bold foreground. */
    PURPLE_BOLD("\u001B[1;35m"),

    /** Cyan bold foreground. */
    CYAN_BOLD("\u001B[1;36m"),

    /** Black bold foreground. */
    BLACK_BOLD("\u001B[1;30m"),

    /** White bold foreground. */
    WHITE_BOLD("\u001B[1;37m"),

    /** Reset code ("private"). */
    _RESET("\u001B[0m");

    /** Color unicode (ANSI) value. */
    @Getter public final String unicodeValue;

    private Color(String unicodeValue) {
        this.unicodeValue = unicodeValue;
    }

    /**
     * Wraps the content with the given color code and reset code.
     *
     * @param color ANSI color enum (nullable)
     * @param content text to colorize (nullable)
     * @return colorized string or original content if inputs are null
     */
    public static String paint(Color color, String content) {
        if (color == null) {
            return content;
        }

        if (content == null) {
            return null;
        }

        return color.getUnicodeValue() + content + Color._RESET.getUnicodeValue();
    }

}
