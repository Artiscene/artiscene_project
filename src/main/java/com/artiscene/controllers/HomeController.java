package com.artiscene.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Created by HKoehler on 2/17/17.
 */

@Controller
public class HomeController {

    @GetMapping("/")
    public String homepage(Model model) {
        return "home";
    }
}
