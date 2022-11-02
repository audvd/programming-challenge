package de.exxcellent.challenge.analysers;

import de.exxcellent.challenge.dataparsers.FootballElementParser;
import de.exxcellent.challenge.dataparsers.IParseElements;
import de.exxcellent.challenge.dataparsers.WeatherDataElementParser;
import de.exxcellent.challenge.fileparsers.IParseStringToElementStringList;
import de.exxcellent.challenge.model.FootballElement;
import de.exxcellent.challenge.model.WeatherDataElement;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

public class FootballAnalyser extends ElementListAnalyser<FootballElement, String> {
    @Override
    public IParseElements createCorrespondingElementParser() {
        return new FootballElementParser();
    }

    @Override
    public FootballElement compareElements(FootballElement min, FootballElement currentElement) {
        if (min == null || Math.abs(min.getGoals() - min.getGoals_allowed()) > Math.abs(currentElement.getGoals() - currentElement.getGoals_allowed())) {
            return currentElement;
        }
        return min;
    }

    @Override
    public String getResult(FootballElement elem) {
        return elem.getTeam();
    }

    @Override
    public void writeOutput(String result) {
        System.out.printf("Team with smallest goal spread: %s%n", result);
    }
}
