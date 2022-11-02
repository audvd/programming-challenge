package de.exxcellent.challenge.dataparsers;

import de.exxcellent.challenge.model.FootballElement;

public class FootballElementParser implements IParseElements<FootballElement> {
    @Override
    public FootballElement parseToDataElement(String element) {
        String[] splitLine = element.split(",");

        if (splitLine.length != 8) {
            throw new IllegalArgumentException(String.format("line: %s contains %d arguments. It has to contain 8.", element, splitLine.length));
        }

        FootballElement result = new FootballElement(
                splitLine[0],
                Integer.parseInt(splitLine[1]),
                Integer.parseInt(splitLine[2]),
                Integer.parseInt(splitLine[3]),
                Integer.parseInt(splitLine[4]),
                Integer.parseInt(splitLine[5]),
                Integer.parseInt(splitLine[6]),
                Integer.parseInt(splitLine[7]));

        return result;
    }
}
