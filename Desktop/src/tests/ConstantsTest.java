package tests;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import static main.utils.Constants.*;

public class ConstantsTest {
    @Test
    public void MIN_MAX_Constants_Invariants_Check() {
        assertTrue(MIN_SHOWS_PER_DAY <= MAX_SHOWS_PER_DAY);
        assertThat(MAX_SHOWS_PER_DAY, is(MOVIE_MAP.size()));
    }

    @Test
    public void Working_Hours_Not_Null_Check() {
        assertNotNull(OPENING_TIME);
        assertNotNull(CLOSING_TIME);
    }
}
