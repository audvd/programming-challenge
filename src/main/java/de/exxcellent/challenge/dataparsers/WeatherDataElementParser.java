package de.exxcellent.challenge.dataparsers;

import de.exxcellent.challenge.model.WeatherDataElement;

// Parses a one-entity string into a WeatherDataElement

public class WeatherDataElementParser implements IParseElements<WeatherDataElement> {

    // Current restriction: This method only works for a csv-style one-entity string.
    @Override
    public WeatherDataElement parseToDataElement(String element) {
        String[] splitLine = element.split(",");

        if (splitLine.length != 14) {
            throw new IllegalArgumentException(String.format("line: %s contains %d arguments. It has to contain 14.", element, splitLine.length));
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
