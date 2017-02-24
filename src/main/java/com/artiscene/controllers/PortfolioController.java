package com.artiscene.controllers;

import com.artiscene.models.Project;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * Created by HKoehler on 2/17/17.
 */

@Controller
public class PortfolioController {


    @GetMapping("/portfolio")
    public String uploadModal(@ModelAttribute Project project, Model model) {
        model.addAttribute("project", project);
        return "portfolio";
    }


}