package com.smart.smartcontactmanager.Controllers;

import com.smart.smartcontactmanager.Entities.USER;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class WebController {

    @GetMapping("/")
    public String home(){
        return "Libraries/home";
    }

    @GetMapping("/about")
    public String about(){
        return "Libraries/about";
    }

    @GetMapping("/signup")
    public String signUp(Model model){
        model.addAttribute("user",new USER());
        return "Libraries/signup";
    }

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("user",new USER());
        return "Libraries/login";
    }

}
