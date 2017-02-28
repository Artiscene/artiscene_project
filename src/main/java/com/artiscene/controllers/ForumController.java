package com.artiscene.controllers;

import com.artiscene.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by HKoehler on 2/24/17.
 */

@Controller
public class ForumController {

    @GetMapping ("/forum")
    public String forumPage(Model model){
        model.addAttribute("user", new User());
        return "forum";
    }

}
