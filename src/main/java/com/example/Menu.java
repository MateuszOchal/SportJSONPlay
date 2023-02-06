package com.example;

import com.example.games.SportEvent;
import com.example.games.SportEventService;


import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Menu {
    SportEventService sportEventService = new SportEventService();
    Scanner sc = new Scanner(System.in);

    public void showMenu() {

        String menuInput;
        do {
            System.out.println("\n");
            System.out.println("What would You like to do");
            System.out.println("1: Show all matches for this season ");
            System.out.println("2: Show all matches for this season sorted by most probable result");
            System.out.println("3: Show ten matches for this season sorted by most probable result");
            System.out.println("4: Show parametrized number of matches for this season sorted by most probable result");
            System.out.println("0: Exit");
            menuInput = sc.nextLine();


            while (!menuInput.matches("[0-4]")) {
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
                for (int i = 0; i < sportEventsList.size(); i++) {
                    SportEvent sp = sportEventsList.get(i);
                    System.out.println(sp);
                }
            }
            case 2 -> {
                List<SportEvent> sportEventsList =
                        sportEventService.eventsSortedByProbableOutcomeValue();
                for (int i = 0; i < sportEventsList.size(); i++) {
                    System.out.println(sportEventsList.get(i));
                }
            }
            case 3 -> {
                List<SportEvent> sportEventsList =
                        sportEventService.tenEventsWithHighestProbableOutcome();
                for (int i = 0; i < sportEventsList.size(); i++) {
                    System.out.println(sportEventsList.get(i));
                }
            }
            case 4 -> {
                System.out.println("Enter number of sorted matches to show");
                String numberOfMatches = sc.nextLine();
                while (!numberOfMatches.matches("[0-9]?[0-9]+")) {
                    System.out.println("please enter a number in range 1-1E6");
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
            }
            case 0 -> {
                System.exit(0);
            }
        }
    }
}

