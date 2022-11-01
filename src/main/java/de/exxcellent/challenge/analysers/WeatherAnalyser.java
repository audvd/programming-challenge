package de.exxcellent.challenge.analysers;

import de.exxcellent.challenge.dataparsers.WeatherDataElementParser;
import de.exxcellent.challenge.fileparsers.IParseStringToElementStringList;
import de.exxcellent.challenge.model.WeatherDataElement;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

public class WeatherAnalyser extends ElementListAnalyser {
    public int findSmallestTempSpreadDay(URL path, IParseStringToElementStringList elementStringListParser) throws IOException {
        if (path == null || elementStringListParser == null) {
            throw new IllegalArgumentException("URL and elementStringListParser must not be null");
        }

        try {
            //List<String> elementStringList = getListOfElementsToAnalyse(path, CsvToElementStringParser.class);
            List<String> elementStringList = getListOfElementsToAnalyse(path, elementStringListParser);

            WeatherDataElementParser weatherDataElementParser = new WeatherDataElementParser();
            WeatherDataElement minDiffElement = null;

            List<WeatherDataElement> dataList = elementStringList.stream()
                    .skip(1)
                    .map(line -> weatherDataElementParser.parseLineToWeatherDataElement(line))
                    .collect(Collectors.toList());

            for (WeatherDataElement el : dataList) {
                if (minDiffElement == null || minDiffElement.getMxt() - minDiffElement.getMnt() > el.getMxt() - el.getMnt()) {
                    minDiffElement = el;
                }
            }

            if (minDiffElement != null) {
                int result = minDiffElement.getDay();
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

        return -1;
    }
}
