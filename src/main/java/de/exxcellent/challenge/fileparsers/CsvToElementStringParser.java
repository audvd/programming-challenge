package de.exxcellent.challenge.fileparsers;

import java.util.Arrays;
import java.util.List;

// Parses strings in Csv format into separate entities

public class CsvToElementStringParser implements IParseStringToElementStringList {

    // Every line in a csv file represents one entity, so simply split the string into lines
    @Override
    public List<String> parseFileToElementStringList(String input) {
        // RegEx to take care of different line-ending cases (likely based on OS)
        String[] stringLines = input.split("((\\r)?\\n|\\r(\\n)?)");
        return Arrays.asList(stringLines);
    }
}
