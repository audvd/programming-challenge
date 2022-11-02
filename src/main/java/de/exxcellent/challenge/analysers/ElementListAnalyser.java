package de.exxcellent.challenge.analysers;

import de.exxcellent.challenge.fileparsers.IParseStringToElementStringList;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public abstract class ElementListAnalyser<T> {
    public abstract T analyse(URL path, IParseStringToElementStringList elementStringListParser) throws IOException;

    public abstract void writeOutput(T result);

    public <T extends IParseStringToElementStringList> List<String> getListOfElementsToAnalyse(URL path, IParseStringToElementStringList parser) throws IOException, URISyntaxException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        String fileContent = Files.readString(Paths.get(path.toURI()));
        List<String> elementStringList = parser.parseFileToElementStringList(fileContent);
        return elementStringList;
    }
}
