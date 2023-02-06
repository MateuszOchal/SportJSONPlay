package com.example.teams;

import com.example.games.SportEventService;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Service
@RequiredArgsConstructor
public class CompetitorService {
    private final SportEventService sportEventService;
    public List<Competitor> getValuableDataFromJSONAsAList() {

        List<Competitor> competitors = new ArrayList<>();
        String rawJson = sportEventService.getDataFromJsonAsString();
        JSONObject outer = new JSONObject(rawJson);
        JSONArray jArr = outer.getJSONArray("Events");

        for (int i = 0; i < jArr.length(); i++) {
            JSONObject jsonEvent = jArr.getJSONObject(i);

            JSONArray arrayJson = jsonEvent.getJSONArray("competitors");

            String homeId = arrayJson.getJSONObject(0).getString("id");
            String homeCompName = arrayJson.getJSONObject(0).getString("name");
            String homeCompCountry = arrayJson.getJSONObject(0).getString("country");
            String homeCompAbr = arrayJson.getJSONObject(0).getString("abbreviation");
            String awayId = arrayJson.getJSONObject(1).getString("id");
            String awayCompName = arrayJson.getJSONObject(1).getString("name");
            String awayCompCountry = arrayJson.getJSONObject(1).getString("country");
            String awayCompAbr = arrayJson.getJSONObject(1).getString("abbreviation");

            Competitor competitor1 = new Competitor();
            Competitor competitor2 = new Competitor();

            competitor1.setId(beautifyId(homeId));
            competitor1.setName(homeCompName);
            competitor1.setName(homeCompName);
            competitor1.setCountry(homeCompCountry);
            competitor1.setNameAbbreviation(homeCompAbr);

            competitor2.setId(beautifyId(awayId));
            competitor2.setName(awayCompName);
            competitor2.setCountry(awayCompCountry);
            competitor2.setNameAbbreviation(awayCompAbr);

            if(!competitors.contains(competitor1)){competitors.add(competitor1);}
            if(!competitors.contains(competitor2)){competitors.add(competitor2);}

        }
        return competitors;
    }

    public List<Competitor> getAllCompetitors() {
    List<Competitor> competitors = getValuableDataFromJSONAsAList();
    return competitors;
    }
    public List<Competitor> getAllCompetitorsAlphabetically() {
        List<Competitor> competitors = getValuableDataFromJSONAsAList();
        Collections.sort(competitors);
        return competitors;
    }
    public int beautifyId(String id){
        char[]chars = id.toCharArray();
        StringBuilder sb = new StringBuilder();
        for(char c :chars){
            if(Character.isDigit(c)){
                sb.append(c);
            }
        }
        return Integer.parseInt(sb.toString());
    }
}
