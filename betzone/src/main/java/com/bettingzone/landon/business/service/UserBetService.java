package com.bettingzone.landon.business.service;

import com.bettingzone.landon.auth.User;
import com.bettingzone.landon.auth.UserRepository;
import com.bettingzone.landon.business.domain.BetUserBetReservation;
import com.bettingzone.landon.data.entity.Bets;
import com.bettingzone.landon.data.entity.UserBet;
import com.bettingzone.landon.data.repository.BetsRepository;
import com.bettingzone.landon.data.repository.UserBetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
public class UserBetService {
    private BetsRepository betsRepository;
    private UserBetRepository  userBetRepository;
    private UserRepository userRepository;

    @Autowired
    public UserBetService(BetsRepository betsRepository, UserBetRepository userBetRepository, UserRepository userRepository) {
        this.betsRepository = betsRepository;
        this.userBetRepository = userBetRepository;
        this.userRepository = userRepository;
    }

    public List<BetUserBetReservation> getBetUserBetReservationForDate(Date date){
//        System.out.println("hello working");
        Iterable<Bets> bets = this.betsRepository.findAll();

        Map<Integer, BetUserBetReservation> betUserBetReservationMap = new HashMap<>();
        bets.forEach(bet -> {
            BetUserBetReservation betUserBetReservation = new BetUserBetReservation();
            betUserBetReservation.setBetId(bet.getBetId());
            betUserBetReservation.setDescription(bet.getDescription());
            betUserBetReservationMap.put(bet.getBetId(), betUserBetReservation);
        });
        Iterable<UserBet> userBets = this.userBetRepository.findByDate(new java.sql.Date(date.getTime()));
        if(null != userBets){
            userBets.forEach(userBet -> {
                Optional<User> userresponse = this.userRepository.findById(userBet.getUserIdPk());
                if(userresponse.isPresent()){
                    User userinfo = userresponse.get();
                    BetUserBetReservation betUserBetReservation = betUserBetReservationMap.get(userBet.getUserbetId());
                    betUserBetReservation.setDate(date);
                    betUserBetReservation.setName(userinfo.getName());
                    betUserBetReservation.setUserId(userinfo.getId());
                }
            });
        }
        List<BetUserBetReservation> betUserBetReservations = new ArrayList<>();
        for(Integer betId:betUserBetReservationMap.keySet()){
            betUserBetReservations.add(betUserBetReservationMap.get(betId));
        }
        betUserBetReservations.forEach(e -> {
            System.out.println(e);
        });
        return betUserBetReservations;
    }

//    public List<BetUserBetReservation> getBetUserBetReservationForId(Date date){
//        Iterable<Bets> bets = this.betsRepository.findAll();
//        bets.forEach(bets1 -> {
//            System.out.println(bets1);
//        });
//        Map<Integer, BetUserBetReservation> betUserBetReservationMap = new HashMap<>();
//        bets.forEach(bet -> {
//            BetUserBetReservation betUserBetReservation = new BetUserBetReservation();
//            betUserBetReservation.setBetId(bet.getBetId());
//            betUserBetReservation.setDescription(bet.getDescription());
//            betUserBetReservationMap.put(bet.getBetId(), betUserBetReservation);
//        });
//        Iterable<UserBet> userBets = this.userBetRepository.findByDate(new java.sql.Date(date.getTime()));
//        if(null != userBets){
//            userBets.forEach(userBet -> {
//                Optional<Userinfo> userresponse = this.userRepository.findById(userBet.getUserIdPk());
//                if(userresponse.isPresent()){
//                    Userinfo userinfo = userresponse.get();
//                    BetUserBetReservation betUserBetReservation = betUserBetReservationMap.get(userBet.getUserbetId());
//                    betUserBetReservation.setDate(date);
//                    betUserBetReservation.setName(userinfo.getName());
//                    betUserBetReservation.setUserId(userinfo.getId());
//                }
//            });
//        }
//        List<BetUserBetReservation> betUserBetReservations = new ArrayList<>();
//        for(Integer betId:betUserBetReservationMap.keySet()){
//            betUserBetReservations.add(betUserBetReservationMap.get(betId));
//        }
//        return betUserBetReservations;
//    }
}
