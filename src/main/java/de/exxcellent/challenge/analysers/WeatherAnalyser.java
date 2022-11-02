package de.exxcellent.challenge.analysers;

import de.exxcellent.challenge.dataparsers.IParseElements;
import de.exxcellent.challenge.dataparsers.WeatherDataElementParser;
import de.exxcellent.challenge.model.WeatherDataElement;

public class WeatherAnalyser extends ElementListAnalyser<WeatherDataElement, Integer> {
    @Override
    public IParseElements createCorrespondingElementParser() {
        return new WeatherDataElementParser();
    }

    // Check for minimum temperature spread on a day
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
