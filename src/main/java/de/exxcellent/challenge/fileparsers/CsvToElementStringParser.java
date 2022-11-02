package de.exxcellent.challenge.fileparsers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CsvToElementStringParser implements IParseStringToElementStringList {

    @Override
    public List<String> parseFileToElementStringList(String input) {
        String[] stringLines = input.split("((\\r)?\\n|\\r(\\n)?)");
        return Arrays.asList(stringLines);
    }
}
