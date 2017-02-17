package com.artiscene.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by HKoehler on 2/17/17.
 */

@Controller
public class HomeController {

    @GetMapping("/home")
    public String homepage() {
        return "home";
    }
}
