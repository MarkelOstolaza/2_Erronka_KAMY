package main.utils;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.Map;

import main.models.Day;
import main.models.Movie;
import main.models.Show;
import main.models.enums.Room;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ArrayList;

/**
 * Centralized application constants and shared data structures used across
 * the program's modules.
 */
public class Constants {

    /**
     * Default constructor (private).
     */
    private Constants() {}

    /** Application semantic version. */
    public static final String PROGRAM_VERSION = "v1.0";

    /** Absolute path to the Java source directory. */
    public static final Path SOURCE_FOLDER_ABS_PATH = Paths.get(System.getProperty("user.dir"), "Desktop", "src").toAbsolutePath();

    /** Absolute path to the logs directory. */
    public static final String LOG_FOLDER_ABS_PATH = Paths.get(SOURCE_FOLDER_ABS_PATH.toString(), "logs").toString();

    /** Flag representing an open day. */
    public static final boolean OPEN = true;

    /** Flag representing a closed day. */
    public static final boolean CLOSED = false;

    /** Ordered map of weekday name to open/closed status. */
    public static final Map<String, Boolean> WEEKDAY_MAP = new LinkedHashMap<>();

    static {
        WEEKDAY_MAP.put("Astelehena", OPEN);
        WEEKDAY_MAP.put("Asteartea", OPEN);
        WEEKDAY_MAP.put("Asteazkena", OPEN);
        WEEKDAY_MAP.put("Osteguna", OPEN);
        WEEKDAY_MAP.put("Ostirala", OPEN);
        WEEKDAY_MAP.put("Larunbata", CLOSED);
        WEEKDAY_MAP.put("Igandea", CLOSED);
    }

    /** Map of movie title to assigned {@link Room}. */
    public static final Map<String, Room> MOVIE_MAP = new HashMap<>();

    static {
        MOVIE_MAP.put("Blancanieves", Room.CHILDREN);
        MOVIE_MAP.put("Lilo y Stitch", Room.CHILDREN);
        MOVIE_MAP.put("Mufasa: El rey León", Room.CHILDREN);
        MOVIE_MAP.put("Sonic 3: La película", Room.CHILDREN);
        MOVIE_MAP.put("Una Película de Minecraft", Room.CHILDREN);
        MOVIE_MAP.put("Cómo Entrenar a tu Dragón", Room.CHILDREN);

        MOVIE_MAP.put("Thunderbolt", Room.SUPER_HEROES);
        MOVIE_MAP.put("Capitán América: Brave New World", Room.SUPER_HEROES);

        MOVIE_MAP.put("Sirat", Room.THRILLER);
        MOVIE_MAP.put("Conclave", Room.THRILLER);
        MOVIE_MAP.put("Misión: Imposible. Sentencia Final", Room.THRILLER);

        MOVIE_MAP.put("El Casoplón", Room.COMEDY);
        MOVIE_MAP.put("Un Funeral de Locos", Room.COMEDY);
        MOVIE_MAP.put("Wolfgang (Extraordinario)", Room.COMEDY);
        MOVIE_MAP.put("Padre no hay más que Uno 5", Room.COMEDY);
    }

    /** In-memory list of configured weekdays. */
    public static final List<Day> WEEKDAYS = new ArrayList<>();

    /** In-memory map of movie id to {@link Movie}. */
    public static final Map<String, Movie> MOVIES = new LinkedHashMap<>();

    /** In-memory schedule mapping day name to list of {@link Show}. */
    public static final Map<String, List<Show>> SCHEDULE = new LinkedHashMap<>();

    /** Minimum number of shows per open day. */
    public static final int MIN_SHOWS_PER_DAY = 3;

    /** Maximum number of shows per open day. */
    public static final int MAX_SHOWS_PER_DAY = MOVIE_MAP.size();

    /** Max ticket quantity allowed per movie. */
    public static final int MAX_TICKETS_PER_MOVIE = 4;

    /** Interval in minutes between show start times. */
    public static final int INTERVAL_BETWEEN_SHOWS = 30;

    /** Cinema opening time. */
    public static final LocalTime OPENING_TIME = LocalTime.of(10, 00);

    /** Cinema closing time. */
    public static final LocalTime CLOSING_TIME = LocalTime.of(00, 00);

}
