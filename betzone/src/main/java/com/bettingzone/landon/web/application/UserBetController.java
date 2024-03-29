package com.bettingzone.landon.web.application;

import com.bettingzone.landon.business.domain.BetUserBetReservation;
import com.bettingzone.landon.business.service.UserBetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/userbets")
public class UserBetController {
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    @Autowired
    private UserBetService userBetService;

    @RequestMapping(method = RequestMethod.GET)
    public String getUserbets(@RequestParam(value = "date", required = false)String dateString, Model model){
        Date date = null;
        if (null != dateString){
            try{
                date=DATE_FORMAT.parse(dateString);
                } catch (ParseException pe){
                date = new Date();
            }
        }else{
            date = new Date();
        }
        List<BetUserBetReservation> userBetServiceList = this.userBetService.getBetUserBetReservationForDate(date);

        model.addAttribute("betUserBetReservations", userBetServiceList);

        return "userbets";
    }

}
