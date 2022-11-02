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
    /*
    @Override
    public String analyse(URL path, IParseStringToElementStringList elementStringListParser) throws IOException {
        if (path == null || elementStringListParser == null) {
            throw new IllegalArgumentException("URL and elementStringListParser must not be null");
        }

        try {
            List<String> elementStringList = getListOfElementsToAnalyse(path, elementStringListParser);

            FootballElementParser footballElementParser = new FootballElementParser();
            FootballElement minDiffElement = null;

            List<FootballElement> dataList = elementStringList.stream()
                    .skip(1)
                    .map(line -> footballElementParser.parseToDataElement(line))
                    .collect(Collectors.toList());

            for (FootballElement el : dataList) {
                if (minDiffElement == null || Math.abs(minDiffElement.getGoals() - minDiffElement.getGoals_allowed()) > Math.abs(el.getGoals() - el.getGoals_allowed())) {
                    minDiffElement = el;
                }
            }

            if (minDiffElement != null) {
                String result = minDiffElement.getTeam();
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
    }*/

}
