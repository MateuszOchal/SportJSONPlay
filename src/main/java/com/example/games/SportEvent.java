package com.example.games;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SportEvent implements Comparable<SportEvent> {

private String startDate;
private String homeCompetitorName;
private String homeCompetitorCountry;
private String awayCompetitorName;
private String awayCompetitorCountry;
private String venueName;
private HighestProbableResult highestProbableResult;
private double highestProbableValue;
    @Override
    public int compareTo(SportEvent o) {
        return (int) (o.highestProbableValue - this.highestProbableValue);
    }

    @Override
    public String toString() {
        return "\n" +
                "Start date='" + startDate  +", "+
                  homeCompetitorName +"("+
                homeCompetitorCountry +") vs. " +
                 awayCompetitorName  +"("
                + awayCompetitorCountry +")"+
                ", Venue: " + venueName +
                ", Highest probable result=" + highestProbableResult +
                "(" + highestProbableValue +")";

    }
}
