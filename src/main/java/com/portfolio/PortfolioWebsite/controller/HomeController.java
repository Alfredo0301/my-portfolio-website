package com.portfolio.PortfolioWebsite.controller;

import com.portfolio.PortfolioWebsite.Repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class HomeController {

    @Autowired
    private ProjectRepository projectRepository;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("message", "Welcome to my Portfolio, I'm a Java Developer!");
        model.addAttribute("projects", projectRepository.findByActiveTrueOrderByCreatedAtDesc());
        return "index";
    }
}
