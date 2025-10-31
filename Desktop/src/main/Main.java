package main;

import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

import main.cli.Console;
import main.cli.Logging;
import main.utils.Data;

import static main.utils.Constants.*;

/**
 * Entry point for the application. Performs basic environment checks and
 * starts the interactive console.
 */
public class Main {

    private static final Logger logger = new Logging(Main.class, LOG_FOLDER_ABS_PATH).getLogger();

    /**
     * Default constructor (private).
     */
    private Main() {}

    /**
     * Performs initial runtime checks for configuration and platform support.
     *
     * @throws UnsupportedEncodingException if the default charset is not UTF-8
     * @throws IllegalArgumentException if min/max show constraints are invalid
     * @throws UnsupportedOperationException if the OS is not Windows
     */
    private static void initialChecks() throws UnsupportedEncodingException {
        logger.info("Running initial background checks.");

        if (MIN_SHOWS_PER_DAY > MAX_SHOWS_PER_DAY ||
            MAX_SHOWS_PER_DAY > MOVIE_MAP.size()) {

            IllegalArgumentException e = new IllegalArgumentException("Invalid MIN/MAX show values.");
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw e;
        }

        if (!System.getProperty("file.encoding").equalsIgnoreCase("UTF-8")) {
            UnsupportedEncodingException e = new UnsupportedEncodingException("Invalid file encoding format. Switch to UTF-8.");
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw e;
        }

        if (!System.getProperty("os.name").contains("Windows")) {
            UnsupportedOperationException e = new UnsupportedOperationException("This program is only supported on Windows.");
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Main method.
     *
     * @param varargs command line arguments (unused)
     * @throws UnsupportedEncodingException if runtime encoding validation fails
     */
    public static void main(String... varargs) throws UnsupportedEncodingException {
        initialChecks();

        System.out.println("Starting program execution. Please wait.");

        logger.info("Starting program execution.");

        new Data().init();
        new Console().run();
    }

}
