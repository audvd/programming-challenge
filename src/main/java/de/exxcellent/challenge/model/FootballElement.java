package de.exxcellent.challenge.model;

import de.exxcellent.challenge.dataparsers.FootballElementParser;

public class FootballElement {
    private String team;
    private int games;
    private int wins;
    private int losses;
    private int draws;
    private int goals;
    private int goals_allowed;
    private int points;

    public FootballElement(String team, int games, int wins, int losses, int draws, int goals, int goals_allowed, int points) {
        this.team = team;
        this.games = games;
        this.wins = wins;
        this.losses = losses;
        this.draws = draws;
        this.goals = goals;
        this.goals_allowed = goals_allowed;
        this.points = points;
    }

    public String getTeam() {
        return team;
    }

    public int getGames() {
        return games;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public int getDraws() {
        return draws;
    }

    public int getGoals() {
        return goals;
    }

    public int getGoals_allowed() {
        return goals_allowed;
    }

    public int getPoints() {
        return points;
    }
}
