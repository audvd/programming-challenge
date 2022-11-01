package de.exxcellent.challenge;

import jdk.jshell.spi.ExecutionControl;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class WeatherAnalyser {
    public int findSmallestTempSpreadDay(URL path) throws IOException {
        if (path == null) {
            throw new IllegalArgumentException("URL must not be null");
        }

        try {
            List<String> weatherLines = Files.readAllLines(Paths.get(path.toURI()));
            FileParser parser = new FileParser();

            List<WeatherDataElement> dataList = weatherLines.stream()
                    .skip(1)
                    .map(line -> parser.parseLineToWeatherDataElement(line))
                    .collect(Collectors.toList());

            WeatherDataElement minDiffElement = null;

            for (WeatherDataElement el : dataList) {
                if (minDiffElement == null || minDiffElement.getMxt() - minDiffElement.getMnt() > el.getMxt() - el.getMnt()) {
                    minDiffElement = el;
                }
            }

            if (minDiffElement != null) {
                int result = minDiffElement.getDay();
                return result;
            }
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("Passed URL could not be converted to URI");
        }

        return -1;
    }
}
