package de.exxcellent.challenge;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Example JUnit 5 test case.
 * @author Benjamin Schmid <benjamin.schmid@exxcellent.de>
 */
class AppTest {

    private String successLabel = "not successful";

    @BeforeEach
    void setUp() {
        successLabel = "successful";
    }

    @Test
    void findSmallestTempSpreadDayOutputsCorrectDayTest() throws IOException {
        // Arrange
        WeatherAnalyser sut = new WeatherAnalyser();
        URL weatherFile = getClass().getResource("/de/exxcellent/challenge/weather.csv");

        // Act
        int result = sut.findSmallestTempSpreadDay(weatherFile);

        // Assert
        assertEquals(14, result, "Correct day was output.");
    }

    @Test
    void findSmallestTempSpreadDayThrowsExceptionOnNullInputTest() {
        // Arrange
        WeatherAnalyser sut = new WeatherAnalyser();

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> sut.findSmallestTempSpreadDay(null), "");
    }

    @Test
    void runWeather() {
        App.main("--weather", "weather.csv");
    }

    @Test
    void runFootball() {
        App.main("--football", "football.csv");
    }

}