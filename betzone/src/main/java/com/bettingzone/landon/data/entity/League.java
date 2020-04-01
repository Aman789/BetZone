package com.bettingzone.landon.data.entity;

import javax.persistence.*;

@Entity
@Table(name="LEAGUE")
public class League {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "league_id")
    private Integer leagueId;
    @Column(name = "league_name")
    private String leagueName;
    @Column(name = "sports_sport_id")
    private Integer sportsId;

    public Integer getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(Integer leagueId) {
        this.leagueId = leagueId;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }

    public Integer getSportsId() {
        return sportsId;
    }

    public void setSportsId(Integer sportsId) {
        this.sportsId = sportsId;
    }
}
