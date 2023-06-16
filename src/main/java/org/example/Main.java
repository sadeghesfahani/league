package org.example;

import base.entities.Coach;
import base.services.LeagueService;

public class Main {
    public static void main(String[] args) {
        LeagueService service = new LeagueService();
        Coach coach = service.HighestSalaryOfCoach();
        System.out.println(coach);
    }
}