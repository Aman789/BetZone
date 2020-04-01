package com.bettingzone.landon.web.application;

import com.bettingzone.landon.business.domain.BetsDisplay;
import com.bettingzone.landon.business.service.BetPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/display")
public class DisplayController {
    private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    @Autowired
    private BetPageService betPageService;

    @RequestMapping(method = RequestMethod.GET)
    public String getHomepage(Model model) {
        Date date = new Date();
        System.out.println(date);
        LocalDate localDate = LocalDate.now().plusDays(4);
        Date enddate = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        System.out.println(enddate);
        List<BetsDisplay> betsDisplayList = this.betPageService.betsDisplaysForBetweenDates(date, enddate);
        model.addAttribute("betsDisplayList", betsDisplayList);
        model.addAttribute("date", date);

        return "display";
    }
}
