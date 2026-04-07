package com.portfolio.PortfolioWebsite.controller;

import com.portfolio.PortfolioWebsite.Model.Project;
import com.portfolio.PortfolioWebsite.Repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectRepository projectRepository;

    @GetMapping
    public String listProjects(Model model) {
        List<Project> projects = projectRepository.findByActiveTrueOrderByCreatedAtDesc();
        model.addAttribute("projects", projects);
        return "projects";
    }

    @GetMapping("/{id}")
    public String getProject(@PathVariable Long id, Model model) {
        Optional<Project> project = projectRepository.findById(id);
        if (project.isPresent()) {
            model.addAttribute("project", project.get());
            return "project-detail";
        }
        return "redirect:/projects";
    }

    @GetMapping("/api/all")
    @ResponseBody
    public List<Project> getAllProjects() {
        return projectRepository.findByActiveTrueOrderByCreatedAtDesc();
    }
}

