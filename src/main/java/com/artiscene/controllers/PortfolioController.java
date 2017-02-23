package com.artiscene.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by HKoehler on 2/17/17.
 */

@Controller
public class PortfolioController {

    @GetMapping("/portfolio")
    public String portfolioPage(){
        return "portfolio";
    }
}