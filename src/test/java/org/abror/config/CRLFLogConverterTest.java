//Anvarov Abror

// tests for the CRLFLogConverter class, which appears to be a custom log converter used in a logging framework.
// The tests cover different scenarios where the input string is transformed based on the markers and logger names.

package org.abror.config;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ch.qos.logback.classic.spi.ILoggingEvent;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.boot.ansi.AnsiColor;
import org.springframework.boot.ansi.AnsiElement;

class CRLFLogConverterTest {

    // Ensures that when the marker list is empty (or null), the input string is returned unchanged.
    @Test
    void transformShouldReturnInputStringWhenMarkerListIsEmpty() {
        ILoggingEvent event = mock(ILoggingEvent.class);
        when(event.getMarkerList()).thenReturn(null);
        when(event.getLoggerName()).thenReturn("org.hibernate.example.Logger");
        String input = "Test input string";
        CRLFLogConverter converter = new CRLFLogConverter();

        String result = converter.transform(event, input);

        assertEquals(input, result);
    }

    // Verifies that when the markers contain "CRLF_SAFE", the input string is returned without transformation.
    @Test
    void transformShouldReturnInputStringWhenMarkersContainCRLFSafeMarker() {
        ILoggingEvent event = mock(ILoggingEvent.class);
        Marker marker = MarkerFactory.getMarker("CRLF_SAFE");
        List<Marker> markers = Collections.singletonList(marker);
        when(event.getMarkerList()).thenReturn(markers);
        String input = "Test input string";
        CRLFLogConverter converter = new CRLFLogConverter();

        String result = converter.transform(event, input);

        assertEquals(input, result);
    }

    // Checks that when the markers contain a marker other than "CRLF_SAFE", the input string is not transformed.
    @Test
    void transformShouldReturnInputStringWhenMarkersNotContainCRLFSafeMarker() {
        ILoggingEvent event = mock(ILoggingEvent.class);
        Marker marker = MarkerFactory.getMarker("CRLF_NOT_SAFE");
        List<Marker> markers = Collections.singletonList(marker);
        when(event.getMarkerList()).thenReturn(markers);
        when(event.getLoggerName()).thenReturn("org.hibernate.example.Logger");
        String input = "Test input string";
        CRLFLogConverter converter = new CRLFLogConverter();

        String result = converter.transform(event, input);

        assertEquals(input, result);
    }

    // Tests that if the logger is "safe" (based on its name), the input string is returned unchanged.
    @Test
    void transformShouldReturnInputStringWhenLoggerIsSafe() {
        ILoggingEvent event = mock(ILoggingEvent.class);
        when(event.getLoggerName()).thenReturn("org.hibernate.example.Logger");
        String input = "Test input string";
        CRLFLogConverter converter = new CRLFLogConverter();

        String result = converter.transform(event, input);

        assertEquals(input, result);
    }

    // Confirms that when markers do not contain "CRLF_SAFE" and the logger is not considered safe, newline (\n) and carriage return (\r) characters are replaced with underscores.
    @Test
    void transformShouldReplaceNewlinesAndCarriageReturnsWithUnderscoreWhenMarkersDoNotContainCRLFSafeMarkerAndLoggerIsNotSafe() {
        ILoggingEvent event = mock(ILoggingEvent.class);
        List<Marker> markers = Collections.emptyList();
        when(event.getMarkerList()).thenReturn(markers);
        when(event.getLoggerName()).thenReturn("com.mycompany.myapp.example.Logger");
        String input = "Test\ninput\rstring";
        CRLFLogConverter converter = new CRLFLogConverter();

        String result = converter.transform(event, input);

        assertEquals("Test_input_string", result);
    }

    // Similar to the previous test but includes an option for an ANSI color code, verifying that the transformation properly handles ANSI elements.
    @Test
    void transformShouldReplaceNewlinesAndCarriageReturnsWithAnsiStringWhenMarkersDoNotContainCRLFSafeMarkerAndLoggerIsNotSafeAndAnsiElementIsNotNull() {
        ILoggingEvent event = mock(ILoggingEvent.class);
        List<Marker> markers = Collections.emptyList();
        when(event.getMarkerList()).thenReturn(markers);
        when(event.getLoggerName()).thenReturn("com.mycompany.myapp.example.Logger");
        String input = "Test\ninput\rstring";
        CRLFLogConverter converter = new CRLFLogConverter();
        converter.setOptionList(List.of("red"));

        String result = converter.transform(event, input);

        assertEquals("Test_input_string", result);
    }

    // Verifies that the logger is considered safe if its name starts with a predefined safe prefix.
    @Test
    void isLoggerSafeShouldReturnTrueWhenLoggerNameStartsWithSafeLogger() {
        ILoggingEvent event = mock(ILoggingEvent.class);
        when(event.getLoggerName()).thenReturn("org.springframework.boot.autoconfigure.example.Logger");
        CRLFLogConverter converter = new CRLFLogConverter();

        boolean result = converter.isLoggerSafe(event);

        assertTrue(result);
    }

    // Confirms that the logger is not safe if its name does not start with the safe prefix.
    @Test
    void isLoggerSafeShouldReturnFalseWhenLoggerNameDoesNotStartWithSafeLogger() {
        ILoggingEvent event = mock(ILoggingEvent.class);
        when(event.getLoggerName()).thenReturn("com.mycompany.myapp.example.Logger");
        CRLFLogConverter converter = new CRLFLogConverter();

        boolean result = converter.isLoggerSafe(event);

        assertFalse(result);
    }

    // Ensures that the conversion to an ANSI string works correctly when given an AnsiColor.
    @Test
    void testToAnsiString() {
        CRLFLogConverter cut = new CRLFLogConverter();
        AnsiElement ansiElement = AnsiColor.RED;

        String result = cut.toAnsiString("input", ansiElement);

        assertThat(result).isEqualTo("input");
    }
}
