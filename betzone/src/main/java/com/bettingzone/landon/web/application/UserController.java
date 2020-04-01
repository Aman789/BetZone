package com.bettingzone.landon.web.application;

import com.bettingzone.landon.business.domain.BetsDisplay;
import com.bettingzone.landon.business.service.HomePageBet;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/")
public class UserController {
    private final HomePageBet homePageBetservice;

    public UserController(HomePageBet homePageBetservice) {
        this.homePageBetservice = homePageBetservice;
    }

    @GetMapping(value = "/login")
    public String getLoginPage(Model model){
        return "login";
    }

    @GetMapping(value = "/logout-success")
    public String getLogoutPage(Model model){
        return "logout";
    }

    @GetMapping(value = {"/","/homepage"})
    public String getBetspage(Model model){
        Date date = new Date();
        List<BetsDisplay> betsDisplayList = this.homePageBetservice.betsDisplaysForHomepage(date);
        model.addAttribute("betsDisplayList", betsDisplayList);
        model.addAttribute("date", date);

        return "layout";
    }

    @GetMapping(value = "/basketball")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getBasketballbets(Model model){
        Date date = new Date();
        List<BetsDisplay> betsDisplayList = this.homePageBetservice.betsDisplaysForHomepage(date);
        model.addAttribute("betsDisplayList", betsDisplayList);
        model.addAttribute("date", date);

        return "basketball";
    }
}
