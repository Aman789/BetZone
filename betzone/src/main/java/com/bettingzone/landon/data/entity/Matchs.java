package com.bettingzone.landon.data.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "MATCHS")
public class Matchs {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MATCH_ID")
    private Integer matchId;
    @Column(name = "HOME_TEAM_ID")
    private Integer homeTeamId;
    @Column(name = "AWAY_TEAM_ID")
    private Integer awayTeamId;
    @Column(name = "DATE_FOR")
    private Date date;
    @Column(name = "SPORT_ID")
    private Integer sportId;

    public Integer getMatchId() {
        return matchId;
    }

    public void setMatchId(Integer matchId) {
        this.matchId = matchId;
    }

    public Integer getHomeTeamId() {
        return homeTeamId;
    }

    public void setHomeTeamId(Integer homeTeamId) {
        this.homeTeamId = homeTeamId;
    }

    public Integer getAwayTeamId() {
        return awayTeamId;
    }

    public void setAwayTeamId(Integer awayTeamId) {
        this.awayTeamId = awayTeamId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getSportId() {
        return sportId;
    }

    public void setSportId(Integer sportId) {
        this.sportId = sportId;
    }
}
