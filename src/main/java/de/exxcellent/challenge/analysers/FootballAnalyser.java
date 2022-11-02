package de.exxcellent.challenge.analysers;

import de.exxcellent.challenge.dataparsers.FootballElementParser;
import de.exxcellent.challenge.dataparsers.IParseElements;
import de.exxcellent.challenge.model.FootballElement;


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
