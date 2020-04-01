//package com.bettingzone.landon.web.application;
//
//import com.bettingzone.landon.business.domain.BetsDisplay;
//import com.bettingzone.landon.business.service.HomePageBet;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.List;
//
//@Controller
//@RequestMapping(value = "/homepage")
//public class HomepageController {
//
//    private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//    @Autowired
//    private HomePageBet homePageBet;
//
//    @RequestMapping(method = RequestMethod.GET)
//    public String getHomepage(Model model){
//        Date date = new Date();
//
//
//        List<BetsDisplay> betsDisplayList = this.homePageBet.betsDisplaysForHomepage(date);
//        model.addAttribute("betsDisplayList", betsDisplayList);
//        model.addAttribute("date", date);
//
//        return "homepage";
//    }
//}
