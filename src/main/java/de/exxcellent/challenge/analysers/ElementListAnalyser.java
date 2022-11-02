package de.exxcellent.challenge.analysers;

import de.exxcellent.challenge.dataparsers.IParseElements;
import de.exxcellent.challenge.dataparsers.WeatherDataElementParser;
import de.exxcellent.challenge.fileparsers.IParseStringToElementStringList;
import de.exxcellent.challenge.model.WeatherDataElement;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public abstract class ElementListAnalyser<S,T> {
    public T analyse(URL path, IParseStringToElementStringList elementStringListParser) throws IOException {
        if (path == null || elementStringListParser == null) {
            throw new IllegalArgumentException("URL and elementStringListParser must not be null");
        }

        try {
            List<String> elementStringList = getListOfElementsToAnalyse(path, elementStringListParser);

            IParseElements<S> elementParser = createCorrespondingElementParser();
            S minDiffElement = null;

            List<S> dataList = elementStringList.stream()
                    .skip(1)
                    .map(line -> elementParser.parseToDataElement(line))
                    .collect(Collectors.toList());

            for (S el : dataList) {
                minDiffElement = compareElements(minDiffElement, el);
            }

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

        return null;
    }

    public abstract IParseElements createCorrespondingElementParser();

    public abstract S compareElements(S min, S currentElement);

    public abstract T getResult(S elem);

    public abstract void writeOutput(T result);

    public <T extends IParseStringToElementStringList> List<String> getListOfElementsToAnalyse(URL path, IParseStringToElementStringList parser) throws IOException, URISyntaxException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        String fileContent = Files.readString(Paths.get(path.toURI()));
        List<String> elementStringList = parser.parseFileToElementStringList(fileContent);
        return elementStringList;
    }
}
