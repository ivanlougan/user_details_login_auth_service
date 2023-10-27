package com.example.demo;

import com.example.message.WelcomeMessageService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    private final WelcomeMessageService welcomeMessageService;

    public HomeController(WelcomeMessageService welcomeMessageService) {
        this.welcomeMessageService = welcomeMessageService;
    }

    @GetMapping("/")
    String home(@RequestParam(defaultValue = "en") String lang, Model model) {
        String welcomeMessage = welcomeMessageService.getWelcomeMessage(lang);
        model.addAttribute("lang", lang);
        model.addAttribute("welcomeMessage", welcomeMessage);
        return "index";
    }

//    @GetMapping("/")
//    public String home(Authentication authentication, Model model) {
//        model.addAttribute("username", authentication.getName());
//        return "index";
//    }

    //    ||


    //    @GetMapping("/")
    //    String home(@CurrentSecurityContext(expression = "authentication.name") String username, Model model) {
    //        model.addAttribute("username", username);
    //        return "index";
    //    }

    @GetMapping("/byebye")
    String bye() {
        return "byebye";
    }
}
