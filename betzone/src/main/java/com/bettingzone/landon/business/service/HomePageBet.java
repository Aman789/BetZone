package com.bettingzone.landon.business.service;

import com.bettingzone.landon.business.domain.BetsDisplay;
import com.bettingzone.landon.data.entity.*;
import com.bettingzone.landon.data.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class HomePageBet {
    private BetsRepository betsRepository;
    private UserBetRepository userBetRepository;
    private TeamRepository teamRepository;
    private MatchRepository matchRepository;
    private MatchStatRepository matchStatRepository;
    private LeagueRepository leagueRepository;
    private SportsRepository sportsRepository;

    @Autowired
    public HomePageBet(SportsRepository sportsRepository, LeagueRepository leagueRepository, BetsRepository betsRepository, UserBetRepository userBetRepository, TeamRepository teamRepository, MatchRepository matchRepository, MatchStatRepository matchStatRepository) {
        this.betsRepository = betsRepository;
        this.userBetRepository = userBetRepository;
        this.teamRepository = teamRepository;
        this.matchRepository = matchRepository;
        this.matchStatRepository = matchStatRepository;
        this.leagueRepository = leagueRepository;
        this.sportsRepository = sportsRepository;

    }

    public static <K, V> K getKey(Map<K, V> map, V value) {
        return map.keySet()
                .stream()
                .filter(key -> value.equals(map.get(key)))
                .findFirst().get();
    }

    private Integer getKey(Map<Integer, BetsDisplay> betsDisplayMap, Matchs match) {
        return betsDisplayMap.keySet().stream()
                .findFirst().get();
    }
    //bets for today and upcoming dates
    public List<BetsDisplay> betsDisplaysForHomepage(Date date){
        Iterable<Matchs> matchs = this.matchRepository.findByDate(new java.sql.Date(date.getTime()));
        Map<Integer, BetsDisplay> betsDisplayMap = new HashMap<>();
        matchs.forEach(match -> {
            //System.out.println(match.getMatchId());
            BetsDisplay betsDisplay = new BetsDisplay();
            betsDisplay.setMatchId(match.getMatchId());
            betsDisplay.setAwayTeamId(match.getAwayTeamId());
            betsDisplay.setHomeTeamId(match.getHomeTeamId());
            betsDisplay.setDate(match.getDate());
            betsDisplay.setSportId(match.getSportId());
            betsDisplayMap.put(match.getMatchId(), betsDisplay);
            //System.out.println("----------betsdisplaymap---------------");
            //System.out.println(betsDisplayMap.keySet());
            //System.out.println(getKey(betsDisplayMap, match));
        });
        //betsDisplayMap.forEach((key, value) -> //System.out.println("This is : " + key + " " + value));
        Iterable<Bets> betsOptional = this.betsRepository.findByStartDate(new java.sql.Date(date.getTime()));
        betsOptional.forEach(e->{
            //System.out.println(e.getBetId() + " and " + e.getDescription() + " mat" + e.getMatchId());
        });
        if(null != betsOptional) {
            betsOptional.forEach(bet -> {
                BetsDisplay betsDisplay = betsDisplayMap.get(bet.getMatchId());
                if(null != betsDisplay) {
                    betsDisplay.setBetId(bet.getBetId());
                    betsDisplay.setDescription(bet.getDescription());
                    betsDisplay.setHomeOdds(bet.getHomeOdds());
                    betsDisplay.setAwayOdds(bet.getAwayOdds());
                }
            });
        }
        //System.out.println("Showing list before matchsts");
        //betsDisplayMap.forEach((key, value) -> //System.out.println(key + " " + value));

        Iterable<MatchStat> matchStats = this.matchStatRepository.findAll();
        matchStats.forEach(matchStat -> {
            //System.out.println("------------matchstats----------------");
            //System.out.print (matchStat.getMatchStatId() + " ");
            //System.out.print(matchStat.getMatchIdPk() + " ");
            //System.out.print(matchStat.getAwayTeamResult());
        });

        if(null != matchStats) {
            matchStats.forEach(matchStat -> {
                BetsDisplay betsDisplay = betsDisplayMap.get(matchStat.getMatchIdPk());
                if(null != betsDisplay) {
                    betsDisplay.setAwayResult(matchStat.getAwayTeamResult());
                    betsDisplay.setHomeResult(matchStat.getHomeTeamResult());
                }
            });
        }

        for (Map.Entry me : betsDisplayMap.entrySet()){
                //System.out.println("display list |||||" + me.getValue());
        }
        //System.out.println("--------------FINAL before team name-----------");
        //betsDisplayMap.forEach((key, value) -> //System.out.println(key + " awaw  " + value.getAwayTeamId() + " homr " + value.getHomeTeamId()));
        //System.out.println("-------------------------");
        Iterable<Team> teams = this.teamRepository.findAll();

        for (Matchs match : matchs) {
            BetsDisplay betsDisplay = betsDisplayMap.get(match.getMatchId());
            for (Team team : teams) {
                if (betsDisplay.getHomeTeamId() == team.getTeamId()) {
                    betsDisplay.setHomeTeamName(team.getTeamName());
                } else if (betsDisplay.getAwayTeamId() == team.getTeamId()) {
                    betsDisplay.setAwayTeamName(team.getTeamName());
                }
            }
        }

        Iterable<League> leagues = this.leagueRepository.findAll();
        leagues.forEach(league -> {
            //System.out.println(league.getLeagueId() + " " +  league.getLeagueName() + " " + league.getSportsId());
        });
        for(Matchs match: matchs){
            BetsDisplay betsDisplay = betsDisplayMap.get(match.getMatchId());
            for (League league: leagues){
                if(betsDisplay.getSportId() == league.getSportsId()){
                    betsDisplay.setLeagueId(league.getLeagueId());
                    betsDisplay.setLeagueName(league.getLeagueName());
                }
            }
        }
       Set<Map.Entry<Integer, BetsDisplay>> entries = betsDisplayMap.entrySet();
       //System.out.println("entryset: " + entries);
        List<BetsDisplay> betsDisplays = new ArrayList<>();
        for(Integer matchID:betsDisplayMap.keySet()){
            betsDisplays.add(betsDisplayMap.get(matchID));
        }
        return betsDisplays;
    }




}
