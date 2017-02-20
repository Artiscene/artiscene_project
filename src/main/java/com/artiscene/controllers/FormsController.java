package com.artiscene.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by HKoehler on 2/17/17.
 */
@Controller
public class FormsController {

    @GetMapping("/register")
    public String registerPage(){
        return "forms/register";
    }

    @GetMapping("/login")
    public String loginPage(){
        return "forms/login";
    }

}

