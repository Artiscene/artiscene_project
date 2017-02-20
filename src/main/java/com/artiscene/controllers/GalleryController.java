package com.artiscene.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by HKoehler on 2/20/17.
 */

@Controller
public class GalleryController {

    @GetMapping("/gallery")
    public String galleryPage(){
        return "/gallery";
    }
}
