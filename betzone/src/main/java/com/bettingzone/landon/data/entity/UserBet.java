package com.bettingzone.landon.data.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "userbet")
public class UserBet {

    @Id
    @Column(name = "userbet_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userbetId;
    @Column(name = "bet_amount")
    //should be double on schema
    private Integer betAmount;
    @Column(name = "timestamp")
    private Date date;
    @Column(name = "users_user_id")
    private Integer userIdPk;
    @Column(name = "bets_bet_id")
    private Integer betIdPk;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getUserbetId() {
        return userbetId;
    }

    public void setUserbetId(Integer userbetId) {
        this.userbetId = userbetId;
    }

    public Integer getBetAmount() {
        return betAmount;
    }

    public void setBetAmount(Integer betAmount) {
        this.betAmount = betAmount;
    }



    public Integer getUserIdPk() {
        return userIdPk;
    }

    public void setUserIdPk(Integer userIdPk) {
        this.userIdPk = userIdPk;
    }

    public Integer getBetIdPk() {
        return betIdPk;
    }

    public void setBetIdPk(Integer betIdPk) {
        this.betIdPk = betIdPk;
    }
}
