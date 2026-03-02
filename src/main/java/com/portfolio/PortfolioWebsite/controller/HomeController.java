package com.portfolio.PortfolioWebsite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("message", "Welcome to my Portfolio, I'm a Java Developer!");
        return "index"; // Isso vai procurar um arquivo chamado index.html em templates
    }
}
