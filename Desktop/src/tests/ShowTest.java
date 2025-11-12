package tests;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import main.models.Movie;
import main.models.Show;
import main.models.enums.Room;

public class ShowTest {
    @Test
    public void classMethods_Should_Work() {
        Movie movie = new Movie("M10", "Test", Room.THRILLER);
        Show show = new Show("S01", "12:30", movie);

        assertThat(show.getId(), is("S01"));
        assertThat(show.getTime(), is("12:30"));
        assertThat(show.getMovie(), is(movie));
    }
}
