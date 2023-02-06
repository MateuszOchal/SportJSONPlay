package com.example;

import com.example.games.HighestProbableResult;
import com.example.games.SportEvent;
import com.example.games.SportEventService;

import com.example.teams.Competitor;
import com.example.teams.CompetitorService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


class SportJsonPlayApplicationTests {

    @Test
    void test1() {
        SportEventService sportEventService = new SportEventService();
        List<SportEvent> list = sportEventService.tenEventsWithHighestProbableOutcome();
        assertEquals(10, list.size());
    }

    @Test
    void test2() {
        SportEventService sportEventService = new SportEventService();
        List<SportEvent> list = sportEventService.showAllEvents();
        assertEquals(73, list.size());
    }

    @Test
    void test3() {
        SportEventService sportEventService = new SportEventService();
        List<SportEvent> list = sportEventService.eventsWithHighestProbableOutcomeParametrized(14);
        assertEquals(14, list.size());
    }

    @Test
    void test4() {
        SportEventService sportEventService = new SportEventService();
        CompetitorService competitorService = new CompetitorService(sportEventService);
        List<Competitor> list = competitorService.getAllCompetitorsAlphabetically();
        assertEquals(52, list.size());
    }

    @Test
    void test5() {
        SportEventService sportEventService = new SportEventService();
        CompetitorService competitorService = new CompetitorService(sportEventService);
        List<Competitor> list = competitorService.getAllCompetitorsAlphabetically();
        List<Competitor> list1 = competitorService.getAllCompetitors();
        assertNotEquals(list, list1);
    }

    @Test
    void test6() {
        SportEventService sportEventService = new SportEventService();
        List<SportEvent> list = sportEventService.showAllEvents();
        List<SportEvent> list1 = sportEventService.eventsSortedByProbableOutcomeValue();
        assertNotEquals(list, list1);
    }

    @Test
    void testForMethodsSettingHighestProbableResultAndValue() {
        SportEventService sportEventService = new SportEventService();
        SportEvent sportEvent = new SportEvent();

        sportEventService.setHighestProbableValue(sportEvent,33.3,33.4,33.3);
        sportEventService.setHighestProbableResult(sportEvent,33.3,33.4,33.3);
        assertEquals(33.4, sportEvent.getHighestProbableValue());
        assertEquals(HighestProbableResult.DRAW, sportEvent.getHighestProbableResult());
        sportEventService.setHighestProbableValue(sportEvent,40,2,58);
        sportEventService.setHighestProbableResult(sportEvent,40,2,58);
        assertEquals(58, sportEvent.getHighestProbableValue());
        assertEquals(HighestProbableResult.AWAY_TEAM_WIN, sportEvent.getHighestProbableResult());
        sportEventService.setHighestProbableValue(sportEvent,100,0,0);
        sportEventService.setHighestProbableResult(sportEvent,100,0,0);
        assertEquals(100, sportEvent.getHighestProbableValue());
        assertEquals(HighestProbableResult.HOME_TEAM_WIN, sportEvent.getHighestProbableResult());
    }

}
