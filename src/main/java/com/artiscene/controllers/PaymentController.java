package com.artiscene.controllers;

import com.artiscene.models.Project;
import com.artiscene.models.User;
import com.artiscene.repositories.ProjectRepository;
import com.stripe.Stripe;
import com.stripe.exception.*;
import com.stripe.model.Charge;
import com.stripe.model.Transfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vanessamnoble on 2/23/17.
 */
@Controller
public class PaymentController {

    ProjectRepository repository;

    @Autowired
    public PaymentController(ProjectRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/forms/payment")
    public String paymentForm(Model model) {
        model.addAttribute("user", new User());
        return "forms/payment";
    }

    @PostMapping("/forms/payment")
    public String paymentPage(
            @RequestParam(name = "stripeToken") String token,
            @RequestParam(name = "projectId")  long projectId
    ) throws CardException, APIException, AuthenticationException, InvalidRequestException, APIConnectionException {
//button from a project object pass to the other side
        Project project = repository.findOne(projectId);
        User user = project.getUser();
// Set your secret key: remember to change this to your live secret key in production
// See your keys here: https://dashboard.stripe.com/account/apikeys
        Stripe.apiKey = "sk_test_kUyFKcQWcsRYKDohJ6bPDm3F";
//        Stripe.setPublishableKey('pk_test_JKbmxAoOv4rgoznSpQ9RMgjF');


// Token is created using Stripe.js or Checkout!
// Get the payment token submitted by the form:

// Create a Charge: (needs the purchasers stripe token
        Map<String, Object> chargeParams = new HashMap<>();
        chargeParams.put("amount", (int) project.getPrice() * 100); //project price
        chargeParams.put("currency", "usd");
        chargeParams.put("source", token);
        chargeParams.put("transfer_group", "ORDER42");
        Charge charge = Charge.create(chargeParams);

// Create a Transfer to the connected account (later): needs the artists stripe token
        Map<String, Object> transferParams = new HashMap<>();
        transferParams.put("amount", (int) project.getPrice() * 100 - 200);
        transferParams.put("currency", "usd");
        transferParams.put("destination", user.getStripeUserId()); // return the token
        transferParams.put("transfer_group", "ORDER42");
 //future nav to thank you for payment page.
        try {
            Transfer transfer = Transfer.create(transferParams);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return "redirect:/gallery";
    }


}
