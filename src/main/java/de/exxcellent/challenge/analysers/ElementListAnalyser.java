package de.exxcellent.challenge.analysers;

import de.exxcellent.challenge.fileparsers.IParseStringToElementStringList;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public abstract class ElementListAnalyser {
    public <T extends IParseStringToElementStringList> List<String> getListOfElementsToAnalyse(URL path, IParseStringToElementStringList parser) throws IOException, URISyntaxException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        String fileContent = Files.readString(Paths.get(path.toURI()));
        List<String> elementStringList = parser.parseFileToElementStringList(fileContent);
        return elementStringList;
    }

    /*
    public <T extends IParseStringToElementStringList> List<String> getListOfElementsToAnalyse(URL path, Class<T> parser) throws IOException, URISyntaxException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        String fileContent = Files.readString(Paths.get(path.toURI()));
        //IParseStringToElementStringList fileToElementStringListParser = new CsvToElementStringParser();
        IParseStringToElementStringList fileToElementStringListParser = parser.getDeclaredConstructor(new Class[0]).newInstance(new Class[0]);
        List<String> elementStringList = fileToElementStringListParser.parseFileToElementStringList(fileContent);
        return elementStringList;
    }
    */

}
