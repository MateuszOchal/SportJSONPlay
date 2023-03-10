package com.example.teams;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class CompetitorController {
    private final CompetitorService competitorService;
    @GetMapping("/teams")
    public List<Competitor>getAllCompetitors(){
        return competitorService.getAllCompetitors();
    }
    @GetMapping("/teamsSorted")
    public List<Competitor>getAllCompetitorsAlphabetically(){
        return competitorService.getAllCompetitorsAlphabetically();
    }
}
