package com.bettingzone.landon.data.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="BETS")
public class Bets {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "bet_id")
    private Integer betId;
    @Column(name = "enddate")
    private Date endDate;
    @Column(name= "description")
    private String description;
    @Column(name = "startdate")
    private Date startDate;
    @Column(name = "homeodds")
    private Double homeOdds;
    @Column(name = "awayodds")
    private Double awayOdds;
    @Column(name = "MATCHS_MATCH_ID")
    private Integer matchId;


    public Integer getBetId() {
        return betId;
    }

    public void setBetId(Integer betId) {
        this.betId = betId;
    }

    public Double getHomeOdds() {
        return homeOdds;
    }

    public void setHomeOdds(Double homeOdds) {
        this.homeOdds = homeOdds;
    }

    public Double getAwayOdds() {
        return awayOdds;
    }

    public void setAwayOdds(Double awayOdds) {
        this.awayOdds = awayOdds;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Integer getMatchId() {
        return matchId;
    }

    public void setMatchId(Integer matchId) {
        this.matchId = matchId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
