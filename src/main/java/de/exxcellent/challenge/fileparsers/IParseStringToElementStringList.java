package de.exxcellent.challenge.fileparsers;

import java.net.URL;
import java.util.List;

// Simple interface for parsing any string to a list of strings
// with each string describing a separate entity.

public interface IParseStringToElementStringList {
    public List<String> parseFileToElementStringList(String input);
}
