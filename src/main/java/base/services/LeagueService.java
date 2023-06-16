package base.services;

import base.entities.*;
import base.repositories.implementations.*;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Math.min;

public class LeagueService {
    private GameRepositoryImp game;
    private GoalRepositoryImp goal;
    private TeamRepositoryImp team;
    private StadiumRepositoryImp stadium;
    private PlayerRepositoryImp playerRepository;
    private EntityManager entityManager;


    public LeagueService() {
        game = new GameRepositoryImp();
        goal = new GoalRepositoryImp();
        team = new TeamRepositoryImp();
        stadium = new StadiumRepositoryImp();
        playerRepository = new PlayerRepositoryImp();
        entityManager = Persistence.createEntityManagerFactory("league").createEntityManager();

    }

    public Coach HighestSalaryOfCoach() {
//        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Coach> cr = cb.createQuery(Coach.class);
//        Root<Coach> root = cr.from(Coach.class);
//
//        cr.select(root);
//        cr.orderBy(cb.desc(root.get("salary")));
//        List<Coach> coaches = entityManager.createQuery(cr).setMaxResults(1).getResultList();
//
//        return coaches.isEmpty() ? null : coaches.get(0);
        List<Team> teams = team.findAll();
        Coach highestSalaryCoach = null;
        for (Team team : teams) {
            if (highestSalaryCoach == null) {
                highestSalaryCoach = team.getCoach();
            } else {
                if (team.getCoach().getSalary() > highestSalaryCoach.getSalary()) {
                    highestSalaryCoach = team.getCoach();
                }
            }

        }
        return highestSalaryCoach;
    }

    public List<Player> ThreeHighestSalaryPlayers() {
//        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Player> cr = cb.createQuery(Player.class);
//        Root<Player> root = cr.from(Player.class);
//
//        cr.select(root);
//        cr.orderBy(cb.desc(root.get("salary")));
//        List<Player> players = entityManager.createQuery(cr).setMaxResults(3).getResultList();
//
//        return players;
        List<Player> allPlayers = playerRepository.findAll();
        allPlayers.sort((o1, o2) -> {
            if (o1.getSalary() > o2.getSalary()) {
                return -1;
            } else if (o1.getSalary() < o2.getSalary()) {
                return 1;
            } else {
                return 0;
            }
        });
        return allPlayers.subList(0, min(3, allPlayers.size()));
    }

    public HashMap<City, Integer> getCityAndNumberOfTeamsInIt() {
//        List<Team> teams = team.findAll();
//        HashMap<City, Integer> cityAndNumberOfTeamsInIt = new HashMap<>();
//        for (Team team : teams) {
//            cityAndNumberOfTeamsInIt.put(team.getCity(), cityAndNumberOfTeamsInIt.get(team.getCity()) + team.getPlayers().size());
//        }
//        return cityAndNumberOfTeamsInIt;

        List<Team> teams = team.findAll();
        HashMap<City, Integer> cityAndNumberOfTeamsInIt = new HashMap<>();
        for (Team team : teams) {
            City city = team.getCity();
            if (cityAndNumberOfTeamsInIt.containsKey(city)) {
                cityAndNumberOfTeamsInIt.put(city, cityAndNumberOfTeamsInIt.get(city) + 1);
            } else {
                cityAndNumberOfTeamsInIt.put(city, 1);
            }
        }
        return cityAndNumberOfTeamsInIt;
    }

    public HashMap<Team, Integer> getTeamAndTheirScore() {
        List<Team> teams = team.findAll();
        HashMap<Team, Integer> teamAndTheirScore = new HashMap<>();
        for (Team team : teams) {
            teamAndTheirScore.put(team, 0);
        }
        List<Game> games = game.findAll();
        for (Game game : games) {
            Team winnerTeam = game.getTheWinnerTeam();
            if (winnerTeam != null) {
                teamAndTheirScore.put(winnerTeam, teamAndTheirScore.get(winnerTeam) + 1);
            }
        }
        return teamAndTheirScore;
    }


    public Team getWinner(HashMap<Team, Integer> teamAndTheirScore) {

        return Collections.max(teamAndTheirScore.entrySet(), Map.Entry.comparingByValue()).getKey();
    }


    private Game getHighestScoringLocalDerby() {
        List<Game> games = game.findAll();
        Game highestScoringGame = null;
        int highestScore = 0;
        for (Game game : games) {
            if (game.isDerby()) {
                int score = game.getGoals().size();
                if (score > highestScore) {
                    highestScore = score;
                    highestScoringGame = game;
                }
            }
        }
        return highestScoringGame;

    }
}
