package com.artiscene.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by vanessamnoble on 2/23/17.
 */
@Controller
public class PaymentController {
    @GetMapping("/forms/payment")
    public String paymentPage(Model model){
        return "forms/payment";
    }


}
