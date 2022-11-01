package de.exxcellent.challenge;

import de.exxcellent.challenge.analysers.ElementListAnalyser;
import de.exxcellent.challenge.analysers.WeatherAnalyser;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import de.exxcellent.challenge.fileparsers.CsvToElementStringParser;
import de.exxcellent.challenge.fileparsers.IParseStringToElementStringList;
import org.apache.commons.io.FilenameUtils;

/**
 * The entry class for your solution. This class is only aimed as starting point and not intended as baseline for your software
 * design. Read: create your own classes and packages as appropriate.
 *
 * @author Benjamin Schmid <benjamin.schmid@exxcellent.de>
 */
public final class App {

    /**
     * This is the main entry method of your program.
     * @param args The CLI arguments passed
     */
    public static void main(String... args) throws IOException, URISyntaxException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {

        // Your preparation code …
        if (args.length != 2) {
            System.out.println("Invalid input. You need to supply exactly two arguments.\nAbort.");
            return;
        }

        String taskArg  = args[0];
        String input = args[1];

        ElementListAnalyser analyser = null;
        IParseStringToElementStringList elementStringParser = null;

        switch (taskArg) {
            case "--weather":
                analyser = new WeatherAnalyser();
                break;
            default:
                System.out.println("First argument was invalid.\nAbort.");
                return;
        }

        String extension = FilenameUtils.getExtension(input);
        switch (extension) {
            case "csv":
                elementStringParser = new CsvToElementStringParser();
                break;
            default:
                System.out.println("Second argument was invalid.\nAbort.");
                return;
        }

        URL inputFile = App.class.getResource(String.format("/de/exxcellent/challenge/%s", input));
        int output = analyser.analyse(inputFile, elementStringParser);

        analyser.writeOutput(output);
        //String teamWithSmallestGoalSpread = "A good team"; // Your goal analysis function call …
        //System.out.printf("Team with smallest goal spread       : %s%n", teamWithSmallestGoalSpread);
    }
}
