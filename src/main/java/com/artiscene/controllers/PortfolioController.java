package com.artiscene.controllers;

import com.artiscene.models.Project;

import com.artiscene.models.User;
import com.artiscene.repositories.ProjectRepository;
import com.artiscene.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.PathVariable;


/**
 * Created by HKoehler on 2/17/17.
 */

@Controller
public class PortfolioController {


    @Autowired
    public PortfolioController(ProjectRepository repository) {
        this.repository = repository;
    }

    private ProjectRepository repository;

    @GetMapping("/portfolio")
    public String portfolioPage(@ModelAttribute Project project, Model model){
        User user =(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("projects", repository.findByUser(user));
        model.addAttribute("project", project);
        return "portfolio";
    }

    @GetMapping("/portolio/{id}")
    public String userPortfolioPage(Model model, @PathVariable Long id){
        repository.findByUserId(id);
        model.addAttribute("projects", repository.findByUserId(id));
        return "portfolio";
    }
//    @GetMapping("/portfolio")
//    public String uploadModal(@ModelAttribute Project project, Model model) {
//        model.addAttribute("project", project);
//        return "portfolio";
//    }
}

