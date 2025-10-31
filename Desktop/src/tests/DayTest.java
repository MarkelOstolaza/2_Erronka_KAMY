package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Map;
import java.util.LinkedHashMap;

import main.models.Day;

import static main.utils.Constants.*;

public class DayTest {
    private Map<String, Boolean> originalWeekdayMap;

    @Before
    public void setUp() {
        // Preserve original map.
        originalWeekdayMap = new LinkedHashMap<>(WEEKDAY_MAP);
    }

    @After
    public void tearDown() {
        // Restore global state to avoid leakage between tests.
        WEEKDAY_MAP.clear();
        WEEKDAY_MAP.putAll(originalWeekdayMap);
    }

    @Test
    public void constructor_Should_Accept_Valid_Days() {
        Day day = new Day("Astelehena", true);

        assertThat(day.getName(), is("Astelehena"));
        assertTrue(day.isOpen());
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_Should_Reject_Invalid_Days() {
        new Day(null, true);
    }

    @Test
    public void getDayNameFromNumber_Should_Return_Correct_Name() {
        String day1 = Day.getDayNameFromNumber(1);
        String day5 = Day.getDayNameFromNumber(5);

        assertThat(day1, is("Astelehena"));
        assertThat(day5, is("Ostirala"));
    }

    @Test
    public void getDayNameFromNumber_Should_Return_Null_If_Out_Of_Bound() {
        assertNull(Day.getDayNameFromNumber(0));
        assertNull(Day.getDayNameFromNumber(8));
    }
}
