package tests;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import main.models.enums.Color;

public class ColorTest {
    @Test
    public void paint_Should_Return_Content_When_Color_Is_Null() {
        assertThat(Color.paint(null, "Kaixo"), is("Kaixo"));
    }

    @Test
    public void paint_Should_Return_Null_When_Content_Is_Null() {
        assertNull(Color.paint(Color.RED, null));
    }

    @Test
    public void paint_Should_Wrap_Content_With_Unicode_And_Reset() {
        String content = "Kaixo, Mundua!";
        String out = Color.paint(Color.RED, content);

        assertThat(out, startsWith(Color.RED.getUnicodeValue()));
        assertThat(out, endsWith(Color._RESET.getUnicodeValue()));
        assertThat(out, containsString(content));
    }
}
