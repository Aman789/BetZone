package com.bettingzone.landon.business.service;

import com.bettingzone.landon.business.domain.BetsDisplay;
import com.bettingzone.landon.data.entity.*;
import com.bettingzone.landon.data.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BetPageService {
    private BetsRepository betsRepository;
    private UserBetRepository userBetRepository;
    private TeamRepository teamRepository;
    private MatchRepository matchRepository;
    private MatchStatRepository matchStatRepository;
    private LeagueRepository leagueRepository;

    @Autowired
    public BetPageService(LeagueRepository leagueRepository, BetsRepository betsRepository, UserBetRepository userBetRepository, TeamRepository teamRepository, MatchRepository matchRepository, MatchStatRepository matchStatRepository) {
        this.betsRepository = betsRepository;
        this.userBetRepository = userBetRepository;
        this.teamRepository = teamRepository;
        this.matchRepository = matchRepository;
        this.matchStatRepository = matchStatRepository;
        this.leagueRepository = leagueRepository;
    }

    //bets for today
    public List<BetsDisplay> betsDisplaysForHomepage(Date date){
        Iterable<Bets> bets = this.betsRepository.findByStartDate(new java.sql.Date(date.getTime()));
        Map<Integer, BetsDisplay> betsDisplayMap = new HashMap<>();
        bets.forEach(bet -> {
            //System.out.println(bet.getBetId());
            BetsDisplay betsDisplay = new BetsDisplay();
            betsDisplay.setBetId(bet.getBetId());
            betsDisplay.setDescription(bet.getDescription());
            betsDisplay.setHomeOdds(bet.getHomeOdds());
            betsDisplay.setAwayOdds(bet.getAwayOdds());
            betsDisplay.setDate(bet.getStartDate());
            betsDisplay.setEndDate(bet.getEndDate());
            betsDisplay.setMatchId(bet.getMatchId());
            betsDisplayMap.put(bet.getBetId(), betsDisplay);
        });

        Iterable<Matchs> matchs = this.matchRepository.findByDate(new java.sql.Date(date.getTime()));
        for(Bets bet: bets){
            BetsDisplay betsDisplay = betsDisplayMap.get(bet.getBetId());
            for(Matchs match: matchs){
                if (match.getMatchId() == betsDisplay.getMatchId()){
                    betsDisplay.setHomeTeamId(match.getHomeTeamId());
                    betsDisplay.setAwayTeamId(match.getAwayTeamId());
                    betsDisplay.setSportId(match.getSportId());
                }
            }
        }

        Iterable<MatchStat> matchStats = this.matchStatRepository.findAll();
        for(Bets bet: bets){
            BetsDisplay betsDisplay =betsDisplayMap.get(bet.getBetId());
            for (MatchStat matchStat: matchStats){
                if (matchStat.getMatchIdPk() == bet.getMatchId()){
                    betsDisplay.setMatchStatId(matchStat.getMatchStatId());
                    betsDisplay.setHomeResult(matchStat.getHomeTeamResult());
                    betsDisplay.setAwayResult(matchStat.getAwayTeamResult());
                }
            }
        }

        Iterable<League> leagues = this.leagueRepository.findAll();
        bets.forEach(bet->{
            BetsDisplay betsDisplay = betsDisplayMap.get(bet.getBetId());
            for (League league : leagues) {
                if (league.getSportsId() == betsDisplay.getSportId()){
                    betsDisplay.setLeagueId(league.getLeagueId());
                    betsDisplay.setLeagueName(league.getLeagueName());
                }
            }
        });

        Iterable<Team> teams = this.teamRepository.findAll();
        bets.forEach(bet->{
            BetsDisplay betsDisplay = betsDisplayMap.get(bet.getBetId());
            teams.forEach(team -> {
                if (betsDisplay.getHomeTeamId() == team.getTeamId()){
                    betsDisplay.setHomeTeamName(team.getTeamName());
                }
                else if (betsDisplay.getAwayTeamId() == team.getTeamId()){
                    betsDisplay.setAwayTeamName(team.getTeamName());
                }
            });
        });

        List<BetsDisplay> betsDisplays = new ArrayList<>();
        for( Integer betId: betsDisplayMap.keySet()){
            betsDisplays.add(betsDisplayMap.get(betId));
        }

        return betsDisplays;
    }


    public List<BetsDisplay> betsDisplaysForBetweenDates(Date date, Date endDate){
        Iterable<Bets> bets = this.betsRepository.findAllByStartDateBetween(new java.sql.Date(date.getTime()), new java.sql.Date(endDate.getTime()));
        Map<Integer, BetsDisplay> betsDisplayMap = new HashMap<>();
        for (Bets bet : bets) {
            //System.out.println("BETS" + bet.getMatchId());
            //System.out.println("BETS" + bet.getDescription());
            //System.out.println("BETS" + bet.getBetId());
            //System.out.println("BETS" + bet.getStartDate());

        }
        bets.forEach(bet -> {
            //System.out.println(bet.getBetId());
            BetsDisplay betsDisplay = new BetsDisplay();
            betsDisplay.setBetId(bet.getBetId());
            betsDisplay.setDescription(bet.getDescription());
            betsDisplay.setHomeOdds(bet.getHomeOdds());
            betsDisplay.setAwayOdds(bet.getAwayOdds());
            betsDisplay.setDate(bet.getStartDate());
            betsDisplay.setEndDate(bet.getEndDate());
            betsDisplay.setMatchId(bet.getMatchId());
            betsDisplayMap.put(bet.getBetId(), betsDisplay);
        });

        Iterable<Matchs> matchs = this.matchRepository.findAllByDateBetween(new java.sql.Date(date.getTime()), new java.sql.Date(date.getTime()));

        for(Bets bet: bets){
            BetsDisplay betsDisplay = betsDisplayMap.get(bet.getBetId());
            for(Matchs match: matchs){
                if (match.getMatchId() == betsDisplay.getMatchId()){
                    betsDisplay.setHomeTeamId(match.getHomeTeamId());
                    betsDisplay.setAwayTeamId(match.getAwayTeamId());
                    betsDisplay.setSportId(match.getSportId());
                }
            }
        }

        Iterable<MatchStat> matchStats = this.matchStatRepository.findAll();
        for(Bets bet: bets){
            BetsDisplay betsDisplay =betsDisplayMap.get(bet.getBetId());
            for (MatchStat matchStat: matchStats){
                if (matchStat.getMatchIdPk() == bet.getMatchId()){
                    betsDisplay.setMatchStatId(matchStat.getMatchStatId());
                    betsDisplay.setHomeResult(matchStat.getHomeTeamResult());
                    betsDisplay.setAwayResult(matchStat.getAwayTeamResult());
                }
            }
        }

        Iterable<League> leagues = this.leagueRepository.findAll();
        bets.forEach(bet->{
            BetsDisplay betsDisplay = betsDisplayMap.get(bet.getBetId());
            for (League league : leagues) {
                if (league.getSportsId() == betsDisplay.getSportId()){
                    betsDisplay.setLeagueId(league.getLeagueId());
                    betsDisplay.setLeagueName(league.getLeagueName());
                }
            }
        });

        Iterable<Team> teams = this.teamRepository.findAll();
        bets.forEach(bet->{
            BetsDisplay betsDisplay = betsDisplayMap.get(bet.getBetId());
            teams.forEach(team -> {
                if (betsDisplay.getHomeTeamId() == team.getTeamId()){
                    betsDisplay.setHomeTeamName(team.getTeamName());
                }
                else if (betsDisplay.getAwayTeamId() == team.getTeamId()){
                    betsDisplay.setAwayTeamName(team.getTeamName());
                }
            });
        });

        List<BetsDisplay> betsForDisplays = new ArrayList<>();
        for( Integer betId: betsDisplayMap.keySet()){
            betsForDisplays.add(betsDisplayMap.get(betId));
        }

        return betsForDisplays;
    }


}
