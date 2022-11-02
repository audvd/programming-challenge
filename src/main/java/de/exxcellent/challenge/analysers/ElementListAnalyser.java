package de.exxcellent.challenge.analysers;

import de.exxcellent.challenge.dataparsers.IParseElements;
import de.exxcellent.challenge.fileparsers.IParseStringToElementStringList;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

// Abstract class to analyse files according to some criteria

public abstract class ElementListAnalyser<S,T> {
    public T analyse(URL path, IParseStringToElementStringList elementStringListParser) throws IOException {
        if (path == null || elementStringListParser == null) {
            throw new IllegalArgumentException("URL and elementStringListParser must not be null");
        }

        try {
            // Read file at path and get list of one-entity strings
            List<String> elementStringList = getListOfElementsToAnalyse(path, elementStringListParser);

            // Get suitable parser to instantiate POJOs of correct type
            IParseElements<S> elementParser = createCorrespondingElementParser();

            // Set default analysis result
            S minDiffElement = null;

            // Skip header line (assuming csv for now) and parse all elements to POJOs
            List<S> dataList = elementStringList.stream()
                    .skip(1)
                    .map(line -> elementParser.parseToDataElement(line))
                    .collect(Collectors.toList());

            // Find element based on in-subclass defined criteria
            for (S el : dataList) {
                minDiffElement = compareElements(minDiffElement, el);
            }

            // Get and return result (if applicable)
            if (minDiffElement != null) {
                T result = getResult(minDiffElement);
                return result;
            }
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("Passed URL could not be converted to URI");
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        // Return null otherwise
        return null;
    }

    // Return suitable ElementParser
    public abstract IParseElements createCorrespondingElementParser();

    // Compare two POJOs according to some criteria
    public abstract S compareElements(S min, S currentElement);

    // Return the result from a given element
    public abstract T getResult(S elem);

    // Write corresponding output message
    public abstract void writeOutput(T result);

    // Read file@path and split it into one-entity strings
    public <T extends IParseStringToElementStringList> List<String> getListOfElementsToAnalyse(URL path, IParseStringToElementStringList parser) throws IOException, URISyntaxException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        String fileContent = Files.readString(Paths.get(path.toURI()));
        List<String> elementStringList = parser.parseFileToElementStringList(fileContent);
        return elementStringList;
    }
}
