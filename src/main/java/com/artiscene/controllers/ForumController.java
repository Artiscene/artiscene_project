package com.artiscene.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by HKoehler on 2/22/17.
 */
@Controller
public class ForumController {

    @GetMapping("/forum")
    public String forumPage(){
        return "/forum";
    }

}
