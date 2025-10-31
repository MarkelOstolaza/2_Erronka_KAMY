package tests;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Map;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.models.Day;
import main.models.Movie;
import main.models.Show;
import main.utils.Data;

import static main.utils.Constants.*;

public class DataTest {
    private Map<String, Boolean> originalWeekdayMap;

    @Before
    public void setUp() {
        // Preserve weekday map in case future tests modify it.
        originalWeekdayMap = new LinkedHashMap<>(WEEKDAY_MAP);

        // Reset dynamic collections before each run.
        WEEKDAYS.clear();
        MOVIES.clear();
        SCHEDULE.clear();
    }

    @After
    public void tearDown() {
        // Restore weekday map.
        WEEKDAY_MAP.clear();
        WEEKDAY_MAP.putAll(originalWeekdayMap);

        // Also clear dynamic collections to avoid bleed.
        WEEKDAYS.clear();
        MOVIES.clear();
        SCHEDULE.clear();
    }

    @Test
    public void init_Should_Populate_Days_Movies_And_Schedule() {
        new Data().init();

        assertThat(WEEKDAYS.size(), is(WEEKDAY_MAP.size()));
        assertThat(MOVIES.size(), is(MOVIE_MAP.size()));

        int openDays = (int) WEEKDAY_MAP.values().stream().filter(d -> d).count();

        assertThat(SCHEDULE.size(), is(openDays));
    }

    @Test
    public void init_Should_Populate_Schedule_With_Unique_Movies() {
        new Data().init();

        for (Day day : WEEKDAYS) {
            if (!day.isOpen()) {
                continue;
            }

            List<Show> shows = SCHEDULE.get(day.getName());

            assertNotNull(shows);

            assertTrue(shows.size() >= MIN_SHOWS_PER_DAY);
            assertTrue(shows.size() <= MAX_SHOWS_PER_DAY);

            Set<Movie> seen = new HashSet<>();

            for (Show show : shows) {
                assertNotNull(show.getId());
                assertNotNull(show.getMovie());

                // Ensure unique movies each day.
                assertTrue(seen.add(show.getMovie()));

                assertTrue(show.getMovie().getTickets() >= 0);
                assertTrue(show.getMovie().getTickets() <= MAX_TICKETS_PER_MOVIE);
                assertTrue(show.getTime().matches("^[0-2][0-9]:[0-5][0-9]$"));
            }
        }
    }
}
