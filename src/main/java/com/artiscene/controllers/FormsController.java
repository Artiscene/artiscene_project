package com.artiscene.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by HKoehler on 2/17/17.
 */
@Controller
public class FormsController {


    @GetMapping("forms/register")
    public String registerPage(){
        return "forms/register";
    }

    @GetMapping("forms/login")
    public String loginPage(){

        return "forms/login";
    }

    @GetMapping("forms/upload")
    public String uploadPage() { return "forms/upload"; }

    @GetMapping("forms/purchase")
    public String purchasePage(Model model) { return "forms/purchase"; }

}

