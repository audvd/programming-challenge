package de.exxcellent.challenge;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    void findSmallestTempSpreadDayOutputsCorrectDayTest() {
        // Arrange
        WeatherAnalyser sut = new WeatherAnalyser();
        URL weatherFile = getClass().getClassLoader().getResource("weather.csv");

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
        assertThrows(IOException.class, () -> sut.findSmallestTempSpreadDay(null), "");
    }

    @Test
    void findSmallestTempSpreadDayThrowsExceptionOnInvalidInputTest() {
        // Arrange
        WeatherAnalyser sut = new WeatherAnalyser();
        URL invalidFile = getClass().getClassLoader().getResource("filedoesnotexist.csv");

        // Act & Assert
        assertThrows(IOException.class, () -> sut.findSmallestTempSpreadDay(invalidFile));

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