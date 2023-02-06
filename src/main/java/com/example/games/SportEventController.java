package com.example.games;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class SportEventController {
    private final SportEventService sportEventService;
    @GetMapping("/allEvents")
    public List<SportEvent> getAllEvents() {
        return sportEventService.getValuableDataFromJSONAsAList();
    }
    @GetMapping("/allEventsSorted")
    public List<SportEvent> getAllEventsSorted() {
        return sportEventService.eventsSortedByProbableOutcomeValue();
    }
    @GetMapping("/topTen")
    public List<SportEvent>getTenMostProbableResults(){
        return sportEventService.tenEventsWithHighestProbableOutcome();
    }
    @GetMapping("/top/{i}")
    public List<SportEvent>getTenMostProbableResults(@PathVariable int i){
        return sportEventService.eventsWithHighestProbableOutcomeParametrized(i);
    }
}
