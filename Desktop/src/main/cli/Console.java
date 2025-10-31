package main.cli;

import java.io.IOException;
import java.util.Scanner;

import main.models.Day;
import main.models.enums.Color;
import main.models.enums.Menu;

/**
 * Console runner and helpers.
 */
public class Console extends Menus {

    /** Shared input scanner for console interactions. */
    public static final Scanner INPUT_SCANNER = new Scanner(System.in);

    /** Currently selected day in the console session. */
    public static Day currentDay = null;

    /**
     * Creates a new console session.
     */
    public Console() {
        super();
    }

    /**
     * Clears the console on Windows using cmd.
     */
    public static void clear() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Exits the program with the given status code.
     *
     * @param statusCode process exit code
     */
    public static void exit(int statusCode) {
        clear();
        System.exit(statusCode);
    }

    /**
     * Prints a final message and exits the program.
     *
     * @param statusCode process exit code
     * @param message message to print before exit
     */
    public static void exit(int statusCode, String message) {
        clear();

        if (statusCode == 1) {
            System.out.println(Color.paint(Color.RED, message));
        } else {
            System.out.println(message);
        }

        System.out.println();

        System.exit(statusCode);
    }

    /**
     * Waits for the user to press Enter.
     */
    public static void waitForEnter() {
        System.out.println();
        System.out.println(String.format("Sakatu \"%s\" botoia bueltatzeko...", Color.paint(Color.PURPLE, "Enter")));
        System.out.println();

        INPUT_SCANNER.nextLine();
    }

    /**
     * Starts the main menu loop until exit.
     */
    public void run() {
        Menu state = Menu.MAIN_MENU;

        while (state != Menu.EXIT) {
            clear();

            state = switch(state) {
                case MAIN_MENU -> handleMainMenu();
                case WEEKDAY_SELECTION_MENU -> handleWeekdaySelectionMenu(true);
                case INDIVIDUAL_DAY_OPTIONS_MENU -> handleIndividualDayOptionsMenu(true, currentDay);
                case TICKET_PURCHASE_MENU -> handleTicketPurchaseMenu(currentDay);

                default -> Menu.EXIT;
            };
        }

        exit(0, Color.paint(Color.CYAN_BOLD, "Agur!"));
    }

}
