package main.cli;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Logger;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.DayOfWeek;
import java.time.temporal.TemporalAdjusters;

import main.models.Day;
import main.models.Show;
import main.models.enums.Color;
import main.models.enums.Menu;
import main.utils.Data;

import static main.utils.Constants.*;

/**
 * Menu rendering and flow control for the console UI.
 */
public class Menus {

    /** Logger for menu activities. */
    private static final Logger logger = new Logging(Menus.class, LOG_FOLDER_ABS_PATH).getLogger();

    /**
     * Default constructor (private).
     */
    protected Menus() {}

    /**
     * Reads a trimmed line from stdin; returns null on empty input.
     *
     * @param prompt prompt to display
     * @return user input or null if empty
     */
    private static String readSelection(String prompt) {
        System.out.print(prompt);

        String input = Console.INPUT_SCANNER.nextLine().trim();

        if (input.isEmpty()) {
            return null;
        }

        return input;
    }

    /**
     * Prints a stylized header with a title and underline.
     *
     * @param color color to use for the header
     * @param header header text
     */
    private static void printHeader(Color color, String header) {
        Console.clear();

        String title = String.format("[Zinema Usurbil - Zinemapp %s]", PROGRAM_VERSION);

        System.out.println(Color.paint(Color.YELLOW_BOLD, title));
        System.out.println();

        System.out.println(Color.paint(color, header));
        System.out.println("-".repeat(header.length()));
        System.out.println();
    }

    /**
     * Generates a simple screen with an optional body and enter wait.
     *
     * @param printer body renderer (nullable)
     * @param waitForEnter whether to pause for user input
     */
    private static void generateMenu(Runnable printer, boolean waitForEnter) {
        logger.info(String.format("Generating custom dynamic menu for \"%s\".", printer.getClass().getSimpleName().toString()));

        if (printer != null) {
            printer.run();
        }

        if (waitForEnter) {
            Console.waitForEnter();
        }

        System.out.println();
    }

    /**
     * Renders a numbered options menu.
     *
     * @param returnToMain whether to show a return-to-main option
     * @param prompt input prompt text
     * @param header menu header
     * @param options list of options
     * @return raw user input
     */
    private static String generateMenu(boolean returnToMain, String prompt, String header, List<String> options) {
        printHeader(Color.BLUE, header);

        int index = 1;

        for (String option : options) {
            System.out.println(String.format("    %s. %s.", Color.paint(Color.GREEN, String.valueOf(index)), option.trim()));
            ++index;
        }

        System.out.println();

        if (returnToMain) {
            System.out.println(String.format("    %s. %s.", Color.paint(Color.GREEN, String.valueOf(index)), "Itzuli menu nagusira"));
        }

        System.out.println(String.format("    %s. %s.", Color.paint(Color.GREEN, "0"), "Amaitu programa"));
        System.out.println();

        return readSelection(String.format("%s: ", prompt));
    }

    /**
     * Renders a screen with a custom body and basic navigation options.
     *
     * @param returnToMain whether to show a return-to-main option
     * @param prompt input prompt text
     * @param header menu header
     * @param printer body renderer (nullable)
     * @return raw user input
     */
    private static String generateMenu(boolean returnToMain, String prompt, String header, Runnable printer) {
        printHeader(Color.BLUE, header);

        if (printer != null) {
            printer.run();
        }

        System.out.println();

        if (returnToMain) {
            System.out.println(String.format("    %s. %s.", Color.paint(Color.GREEN, "1"), "Itzuli menu nagusira"));
        }

        System.out.println(String.format("    %s. %s.", Color.paint(Color.GREEN, "0"), "Amaitu programa"));
        System.out.println();

        return readSelection(String.format("%s: ", prompt));
    }

    /**
     * Displays the main menu and returns the next state.
     *
     * @return next menu state
     */
    protected static Menu handleMainMenu() {
        Menu state = Menu.MAIN_MENU;

        String prompt = "Zure aukera";
        String header = "Menu Nagusia";
        List<String> options = Arrays.asList(
            "Aste eguna aukeratu",
            "Pelikulen informazio orokorra ikusi",
            "Kokapena ikusi (gela zerrenda)",
            "Irekiera ordutegia ikusi"
        );

        String selection = generateMenu(false, prompt, header, options);
        final int selectionNum;

        try {
            selectionNum = Integer.parseInt(selection);
        } catch (NumberFormatException e) {
            return state;
        }

        switch (selectionNum) {
            case 1 -> state = Menu.WEEK_REFERENCE_DATE_MENU;

            case 2 -> {
                printHeader(Color.BLUE, "Zinemako Pelikulen Informazio Orokorra");
                generateMenu(() -> Data.prettyPrintMovies(), true);
            }

            case 3 -> {
                printHeader(Color.BLUE, "Zinemako Gelen Informazio Orokorra");
                generateMenu(() -> Data.prettyPrintRooms(), true);
            }

            case 4 -> {
                printHeader(Color.BLUE, "Zinemako Ordutegia");
                generateMenu(() -> Data.prettyPrintWorkingHours(), true);
            }

            case 0 -> state = Menu.EXIT;

            default -> {}
        }

        return state;
    }

    /**
     * Asks the user for a date within the desired week, computes dates for
     * Monday through Sunday, and proceeds to weekday selection.
     *
     * @param returnToMain whether to allow returning to main menu
     * @return next menu state
     */
    protected static Menu handleWeekReferenceDateMenu(boolean returnToMain) {
        Menu state = Menu.WEEK_REFERENCE_DATE_MENU;

        String prompt = "Sartu data YYYY-MM-DD formatuan:";
        String header = "Aste Erreferentzia Data";

        printHeader(Color.BLUE, header);

        if (returnToMain) {
            System.out.println(String.format("    %s. %s.", Color.paint(Color.GREEN, "1"), "Itzuli menu nagusira"));
        }

        System.out.println(String.format("    %s. %s.", Color.paint(Color.GREEN, "0"), "Amaitu programa"));
        System.out.println();

        String input = readSelection(String.format("%s: ", prompt));

        if (input == null || input.isEmpty()) {
            return state;
        }

        if ("1".equals(input) && returnToMain) {
            return Menu.MAIN_MENU;
        }

        if ("0".equals(input)) {
            return Menu.EXIT;
        }

        LocalDate refDate;

        try {
            refDate = LocalDate.parse(input);
        } catch (DateTimeParseException e) {
            System.out.println(Color.paint(Color.RED, "Data baliogabea. Erabili formatua: YYYY-MM-DD"));
            Console.waitForEnter();

            return state;
        }

        LocalDate monday = refDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        List<LocalDate> weekDates = new ArrayList<>();

        for (int i = 0; i < 7; i++) {
            weekDates.add(monday.plusDays(i));
        }

        Console.currentWeekDates = weekDates;

        return Menu.WEEKDAY_SELECTION_MENU;
    }

    /**
     * Displays weekday selection and returns the next state.
     *
     * @param returnToMain whether to allow returning to main menu
     * @return next menu state
     */
    protected static Menu handleWeekdaySelectionMenu(boolean returnToMain) {
        Menu state = Menu.WEEKDAY_SELECTION_MENU;

        List<String> options = new ArrayList<>();

        DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("dd/MM");

        int idx = 0;

        for (Map.Entry<String, Boolean> weekday : WEEKDAY_MAP.entrySet()) {
            String dateSuffix = "";

            if (Console.currentWeekDates != null && Console.currentWeekDates.size() == 7) {
                LocalDate d = Console.currentWeekDates.get(idx);
                dateSuffix = String.format(" (%s)", d.format(dayFormatter));
            }

            if (!weekday.getValue()) {
                options.add(String.format("[%s] %s%s", Color.paint(Color.RED, "ITXITA"), weekday.getKey(), dateSuffix));
            } else {
                options.add(String.format("%s%s", weekday.getKey(), dateSuffix));
            }

            idx++;
        }

        String selection = generateMenu(returnToMain, "Aukeratu aste eguna (zenbakia)", "Aste Egun Menua", options);
        final int selectionNum;

        try {
            selectionNum = Integer.parseInt(selection);
        } catch (NumberFormatException e) {
            return state;
        }

        switch (selectionNum) {
            case 1, 2, 3, 4, 5, 6, 7 -> {
                Console.currentDay = WEEKDAYS
                    .stream()
                    .filter(d -> d.getName().equals(Day.getDayNameFromNumber(selectionNum)))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Invalid selection: " + selection));

                state = Menu.INDIVIDUAL_DAY_OPTIONS_MENU;
            }

            case 8 -> state = Menu.MAIN_MENU;

            case 0 -> state = Menu.EXIT;

            default -> {}
        }

        return state;
    }

    /**
     * Displays per-day options for the selected day.
     *
     * @param returnToMain whether to allow returning to previous menus
     * @param selectedDay the chosen day
     * @return {@link Menu} of the next menu
     */
    protected static Menu handleIndividualDayOptionsMenu(boolean returnToMain, Day selectedDay) {
        Menu state = Menu.INDIVIDUAL_DAY_OPTIONS_MENU;

        String prompt = "Aukeratu nahi duzun zeregina";
        String header = String.format("Egun Menua - %s", selectedDay.getName());
        List<String> options = Arrays.asList(
            "Egun honako pelikula zerrenda ikusi",
            "Pelikula aukeratu eta sarrera erosi",
            "Aukeratu beste eguna"
        );

        String selection = generateMenu(returnToMain, prompt, header, options);
        Integer selectionNum = null;

        try {
            selectionNum = Integer.parseInt(selection);
        } catch (NumberFormatException e) {
            return state;
        }

        switch (selectionNum) {
            case 1 -> {
                printHeader(Color.BLUE, String.format("%s-ko Pelikula Saio Zerrenda", selectedDay.getName()));
                generateMenu(() -> Data.prettyPrintShows(selectedDay), true);
            }

            case 2 -> state = Menu.TICKET_PURCHASE_MENU;
            case 3 -> state = Menu.WEEKDAY_SELECTION_MENU;
            case 4 -> state = Menu.MAIN_MENU;

            case 0 -> state = Menu.EXIT;

            default -> {}
        }

        return state;
    }

    /**
     * Displays ticket purchase flow for a day.
     *
     * @param selectedDay the chosen day
     * @return next menu state
     */
    protected static Menu handleTicketPurchaseMenu(Day selectedDay) {
        Menu state = Menu.TICKET_PURCHASE_MENU;

        String prompt = "Zure aukera";
        String header = "Sarrera Erosketa Menua";

        String selection = generateMenu(true, prompt, header, () -> Data.prettyPrintShows(selectedDay));

        if (selection == null) {
            return state;
        }

        switch (selection) {
            case "1" -> state = Menu.MAIN_MENU;

            case "0" -> state = Menu.EXIT;

            default -> {
                System.out.println();

                Show show = SCHEDULE.get(Console.currentDay.getName())
                    .stream()
                    .filter(s -> s.getId().equals(selection))
                    .findFirst()
                    .orElse(null);

                if (show == null) {
                    System.out.println(Color.paint(Color.RED, "\"Show ID\" okerra. Mesedez saiatu berriro."));
                    Console.waitForEnter();

                    return Menu.TICKET_PURCHASE_MENU;
                }

                int soldTickets = show.getMovie().getTickets();
                int remaining = MAX_TICKETS_PER_MOVIE - soldTickets;

                if (remaining <= 0) {
                    System.out.println(String.format("Barkatu, \"%s\" pelikularentzat dagoen sarrerak agortu dira.", Color.paint(Color.PURPLE, show.getMovie().toString())));
                    Console.waitForEnter();

                    return Menu.TICKET_PURCHASE_MENU;
                }

                System.out.println("Hau aukeratu duzu:");
                System.out.println();
                System.out.println(String.format("    - %s: %s", Color.paint(Color.GREEN, "Pelikula"), show.getMovie()));
                System.out.println(String.format("    - %s: %s", Color.paint(Color.GREEN, "Gela"), show.getMovie().getRoom().getName()));
                System.out.println(String.format("    - %s: %s", Color.paint(Color.GREEN, "Ordua"), show.getTime()));
                System.out.println();
                System.out.println(String.format("Eskuragarri geratzen diren sarrerak pelikula honetarako: %s", Color.paint(Color.GREEN, String.valueOf(remaining))));
                System.out.println();
                System.out.print(String.format("Zenbat sarrera erosi nahi dituzu? (0 - %s): ", Color.paint(Color.GREEN, String.valueOf(remaining))));

                String quantityStr = Console.INPUT_SCANNER.nextLine().trim();
                int quantity;

                try {
                    quantity = Integer.parseInt(quantityStr);
                } catch (NumberFormatException e) {
                    System.out.println(Color.paint(Color.RED, "Zenbaki egoki bat behar da."));
                    Console.waitForEnter();

                    return Menu.TICKET_PURCHASE_MENU;
                }

                if (quantity < 1) {
                    return Menu.TICKET_PURCHASE_MENU;
                } else if (quantity > remaining) {
                    System.out.println(Color.paint(Color.RED, "Sarrera kopuru okerra."));
                    Console.waitForEnter();

                    return Menu.TICKET_PURCHASE_MENU;
                }

                show.getMovie().setTickets(soldTickets + quantity);

                System.out.println();
                System.out.println(String.format("Erositako sarrera kopurua: %s (%d/%d)", Color.paint(Color.GREEN, quantityStr), show.getMovie().getTickets(), MAX_TICKETS_PER_MOVIE));

                Console.waitForEnter();
            }
        }

        return state;
    }

}
