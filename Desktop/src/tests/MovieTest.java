package tests;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import main.models.Movie;
import main.models.enums.Room;

public class MovieTest {
    @Test
    public void classMethods_Should_Work() {
        Movie movie = new Movie("M01", "Example Movie", Room.COMEDY);

        assertThat(movie.getId(), is("M01"));
        assertThat(movie.getTitle(), is("Example Movie"));
        assertThat(movie.getRoom(), is(Room.COMEDY));

        assertThat(movie.toString(), is("(M01) Example Movie"));
    }

    @Test
    public void tickets_Should_Default_To_Zero_And_Can_Be_Updated() {
        Movie movie = new Movie("M02", "Another", Room.SCI_FI);

        assertThat(movie.getTickets(), is(0));

        movie.setTickets(3);

        assertThat(movie.getTickets(), is(3));
    }
}
