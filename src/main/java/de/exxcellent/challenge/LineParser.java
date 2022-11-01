package de.exxcellent.challenge;

public class LineParser {
    public WeatherDataElement parseLineToWeatherDataElement(String line) {
        String[] splitLine = line.split(",");

        if (splitLine.length != 14) {
            throw new IllegalArgumentException(String.format("line: %s contains %i arguments. It has to contain 14.", line, splitLine.length));
        }

        WeatherDataElement result = new WeatherDataElement(
                Integer.parseInt(splitLine[0]),
                Integer.parseInt(splitLine[1]),
                Integer.parseInt(splitLine[2]),
                Integer.parseInt(splitLine[3]),
                Float.parseFloat(splitLine[4]),
                Integer.parseInt(splitLine[5]),
                Integer.parseInt(splitLine[6]),
                Float.parseFloat(splitLine[7]),
                Integer.parseInt(splitLine[8]),
                Integer.parseInt(splitLine[9]),
                Float.parseFloat(splitLine[10]),
                Integer.parseInt(splitLine[11]),
                Integer.parseInt(splitLine[12]),
                Float.parseFloat(splitLine[13]));

        return result;
    }
}
