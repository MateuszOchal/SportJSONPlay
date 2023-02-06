package com.example.games;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class SportEventService {

    public String getDataFromJsonAsString() {
        BufferedReader br;
        StringBuilder sb = new StringBuilder();
        {
            try {
                br = new BufferedReader(new FileReader("src/main/resources/BE_data.json"));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        String inputLine;
        while (true) {
            try {
                if (!((inputLine = br.readLine()) != null)) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            sb.append(inputLine);
        }
        try {
            br.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return sb.toString();
    }

    public List<SportEvent> getValuableDataFromJSONAsAList() {
        List<SportEvent> sportEvents = new ArrayList<>();
        String rawJson = getDataFromJsonAsString();
        JSONObject outer = new JSONObject(rawJson);
        JSONArray jArr = outer.getJSONArray("Events");

        for (int i = 0; i < jArr.length(); i++) {
            JSONObject jsonEvent = jArr.getJSONObject(i);

            String startDate = jsonEvent.getString("start_date");

            JSONArray arrayJson = jsonEvent.getJSONArray("competitors");

            String homeCompName = arrayJson.getJSONObject(0).getString("name");
            String homeCompCountry = arrayJson.getJSONObject(0).getString("country");
            String awayCompName = arrayJson.getJSONObject(1).getString("name");
            String awayCompCountry = arrayJson.getJSONObject(1).getString("country");
            String venueName = "No venue name stated";

            if (jsonEvent.has("venue") && !jsonEvent.isNull("venue")) {
                JSONObject inner = jsonEvent.getJSONObject("venue");
                venueName = inner.getString("name");
            }
            double homeTeamWin = jsonEvent.getDouble("probability_home_team_winner");
            double draw = jsonEvent.getDouble("probability_draw");
            double awayTeamWin = jsonEvent.getDouble("probability_away_team_winner");

            SportEvent sportEvent = new SportEvent();

            sportEvent.setStartDate(startDate);
            sportEvent.setHomeCompetitorName(homeCompName);
            sportEvent.setHomeCompetitorCountry(homeCompCountry);
            sportEvent.setAwayCompetitorName(awayCompName);
            sportEvent.setAwayCompetitorCountry(awayCompCountry);
            sportEvent.setVenueName(venueName);
            setHighestProbableResult(sportEvent, homeTeamWin, draw, awayTeamWin);
            setHighestProbableOutcomeOfEvent(sportEvent, homeTeamWin,
                    draw, awayTeamWin);
            sportEvents.add(sportEvent);
        }
        return sportEvents;
    }

    public void setHighestProbableOutcomeOfEvent(SportEvent sportEvent, double homeWin,
                                                 double draw, double awayWin) {
        double outcome;

        if (homeWin > awayWin && homeWin > draw) {
            outcome = homeWin;
        } else if (draw > homeWin && draw > awayWin) {
            outcome = draw;
        } else outcome = awayWin;
        sportEvent.setHighestProbableValue(outcome);

    }

    public void setHighestProbableResult(SportEvent sportEvent, double homeWin, double draw, double awayWin) {
        HighestProbableResult highestProbableResult;
        if (homeWin > awayWin && homeWin > draw) {
            highestProbableResult = HighestProbableResult.HOME_TEAM_WIN;
        } else if (draw > homeWin && draw > awayWin) {
            highestProbableResult = HighestProbableResult.DRAW;
        } else highestProbableResult = HighestProbableResult.AWAY_TEAM_WIN;
        sportEvent.setHighestProbableResult(highestProbableResult);
    }

}

