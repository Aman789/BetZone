package com.bettingzone.landon.data.entity;

import javax.persistence.*;

@Entity
@Table(name = "matchstat")
public class MatchStat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "matchstat_id")
    private Integer matchStatId;
    @Column(name = "home_team_result")
    private Integer homeTeamResult;
    @Column(name = "away_team_result")
    private Integer awayTeamResult;
    @Column(name = "matchs_match_id")
    private Integer matchIdPk;

    public Integer getMatchStatId() {
        return matchStatId;
    }

    public void setMatchStatId(Integer matchStatId) {
        this.matchStatId = matchStatId;
    }

    public Integer getHomeTeamResult() {
        return homeTeamResult;
    }

    public void setHomeTeamResult(Integer homeTeamResult) {
        this.homeTeamResult = homeTeamResult;
    }

    public Integer getAwayTeamResult() {
        return awayTeamResult;
    }

    public void setAwayTeamResult(Integer awayTeamResult) {
        this.awayTeamResult = awayTeamResult;
    }

    public Integer getMatchIdPk() {
        return matchIdPk;
    }

    public void setMatchIdPk(Integer matchIdPk) {
        this.matchIdPk = matchIdPk;
    }

    @Override
    public String toString() {
        return "MatchStat{" +
                "matchStatId=" + matchStatId +
                ", homeTeamResult=" + homeTeamResult +
                ", awayTeamResult=" + awayTeamResult +
                ", matchIdPk=" + matchIdPk +
                '}';
    }
}
