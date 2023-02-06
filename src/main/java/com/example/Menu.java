package com.example;

import com.example.games.SportEvent;
import com.example.games.SportEventService;
import com.example.teams.Competitor;
import com.example.teams.CompetitorController;
import com.example.teams.CompetitorService;


import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Menu {
    SportEventService sportEventService = new SportEventService();
    CompetitorService competitorService = new CompetitorService(sportEventService);
    Scanner sc = new Scanner(System.in);

    public void showMenu() {

        String menuInput;
        do {
            System.out.println("\n");
            System.out.println("What would You like to do");
            System.out.println("1: Show all matches for this season " + "or show in browser -> http://localhost:8080/allEvents");
            System.out.println("2: Show all matches for this season sorted by most probable result" + "or show in browser -> http://localhost:8080/allEventsSorted");
            System.out.println("3: Show ten matches for this season sorted by most probable result" + "or show in browser -> http://localhost:8080/topTen");
            System.out.println("4: Select to pick number to show parametrized number of matches for this season sorted by most probable result (result available to show in browser after selecting number of matches to show)");
            System.out.println("5: Show teams for this season" + "or show in browser -> http://localhost:8080/teams");
            System.out.println("6: Show teams for this season sorted alphabetically" + "or show in browser -> http://localhost:8080/teamsSorted");
            System.out.println("0: Exit");
            menuInput = sc.nextLine();


            while (!menuInput.matches("[0-6]")) {
                System.out.println("There is no such action, please choose a number from the menu");
                menuInput = sc.nextLine();
            }
            switchMenu(parseInt(menuInput));
        }
        while (!menuInput.equals("0"));
    }

    public void switchMenu(int menuInput) {


        switch (menuInput) {
            case 1 -> {
                List<SportEvent> sportEventsList =
                        sportEventService.showAllEvents();
                for (SportEvent sp : sportEventsList) {
                    System.out.println(sp);
                }
            }
            case 2 -> {
                List<SportEvent> sportEventsList =
                        sportEventService.eventsSortedByProbableOutcomeValue();
                for (SportEvent sportEvent : sportEventsList) {
                    System.out.println(sportEvent);
                }
            }
            case 3 -> {
                List<SportEvent> sportEventsList =
                        sportEventService.tenEventsWithHighestProbableOutcome();
                for (SportEvent sportEvent : sportEventsList) {
                    System.out.println(sportEvent);
                }
            }
            case 4 -> {
                System.out.println("Enter number of sorted matches to show");
                String numberOfMatches = sc.nextLine();
                while (!numberOfMatches.matches("[0-9]?[0-9]+")) {
                    System.out.println("please enter a number in range 0-1E6");
                    numberOfMatches = sc.nextLine();
                }
                for (int i = 0; i < numberOfMatches.length(); i++) {
                    if (!(numberOfMatches.charAt(i) == 0)) {
                        break;
                    } else numberOfMatches = numberOfMatches.substring(1);
                }
                List<SportEvent> sportEventList =
                        sportEventService.eventsWithHighestProbableOutcomeParametrized(Integer.parseInt(numberOfMatches));
                for (SportEvent se : sportEventList) {
                    System.out.println(se);
                }
                System.out.println("Show in browser -> http://localhost:8080/top/"+numberOfMatches);
            }
            case 5-> {List<Competitor>competitorList =
                    competitorService.getAllCompetitors();
                for (Competitor competitor : competitorList) {
                    System.out.println(competitor);
                }
            }
            case 6 -> {List<Competitor>competitorList =
                    competitorService.getAllCompetitorsAlphabetically();
                for (Competitor competitor : competitorList) {
                    System.out.println(competitor);
                }
            }
            case 0 -> {
                System.exit(0);
            }
        }
    }
}

