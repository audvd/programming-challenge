package de.exxcellent.challenge.analysers;

import de.exxcellent.challenge.dataparsers.IParseElements;
import de.exxcellent.challenge.dataparsers.WeatherDataElementParser;
import de.exxcellent.challenge.fileparsers.IParseStringToElementStringList;
import de.exxcellent.challenge.model.WeatherDataElement;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

public class WeatherAnalyser extends ElementListAnalyser<WeatherDataElement, Integer> {
    @Override
    public IParseElements createCorrespondingElementParser() {
        return new WeatherDataElementParser();
    }

    @Override
    public WeatherDataElement compareElements(WeatherDataElement min, WeatherDataElement currentElement) {
        if (min == null || min.getMxt() - min.getMnt() > currentElement.getMxt() - currentElement.getMnt()) {
            return currentElement;
        }
        return min;
    }

    @Override
    public Integer getResult(WeatherDataElement elem) {
        return elem.getDay();
    }

    @Override
    public void writeOutput(Integer result) {
        System.out.printf("Day with smallest temperature spread : %s%n", result);
    }
}
