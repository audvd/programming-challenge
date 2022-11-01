package de.exxcellent.challenge;

import de.exxcellent.challenge.analysers.WeatherAnalyser;
import de.exxcellent.challenge.fileparsers.CsvToElementStringParser;
import de.exxcellent.challenge.fileparsers.IParseStringToElementStringList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Example JUnit 5 test case.
 * @author Benjamin Schmid <benjamin.schmid@exxcellent.de>
 */
class AppTest {
    
    private IParseStringToElementStringList parser;

    @BeforeEach
    void setUp() {
        parser = new CsvToElementStringParser();
    }

    @Test
    void findSmallestTempSpreadDayOutputsCorrectDayTest() throws IOException {
        // Arrange
        WeatherAnalyser sut = new WeatherAnalyser();
        URL weatherFile = getClass().getResource("/de/exxcellent/challenge/weather.csv");

        // Act
        int result = sut.analyse(weatherFile, parser);

        // Assert
        assertEquals(14, result, "Correct day was output.");
    }

    @Test
    void findSmallestTempSpreadDayThrowsExceptionOnNullInputTest() {
        // Arrange
        WeatherAnalyser sut = new WeatherAnalyser();

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> sut.analyse(null, parser), "");
    }

    @Test
    void runWeather() throws IOException, URISyntaxException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        App.main("--weather", "weather.csv");
    }

    @Test
    void runFootball() throws IOException, URISyntaxException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        App.main("--football", "football.csv");
    }

}