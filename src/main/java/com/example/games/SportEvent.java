package com.example.games;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SportEvent {

private String startDate;
private String homeCompetitorName;
private String homeCompetitorCountry;
private String awayCompetitorName;
private String awayCompetitorCountry;
private String venueName;
private HighestProbableResult highestProbableResult;
private double highestProbableValue;

}
