package com.artiscene.controllers;

import com.artiscene.models.User;
import com.artiscene.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

/**
 * Created by HKoehler on 2/17/17.
 */
@Controller
public class FormsController {
    private UserRepository repository;
    private PasswordEncoder encoder;


    @Autowired
    public FormsController(UserRepository repository, PasswordEncoder encoder){
        this.repository=repository;
        this.encoder=encoder;
    }


//    @GetMapping("/login")
//    public String loginPage(Model model) {
//        return "forms/login";
//    }

//    @GetMapping("/register")
//    public String registerPage(Model model) {
//        model.addAttribute("user", new User());
//        return "forms/register";
//    }
//
//    @PostMapping("/forms/register")
//    public String registerUser(@Valid User user, Errors validation, Model model, @RequestParam(name="password_confirm") String passwordConfirmation) {
//
//        if (!passwordConfirmation.equals(user.getPassword())) {
//            validation.rejectValue("password", "user.password", "Your passwords do not match");
//        }
//
//        if (validation.hasErrors()) {
//            model.addAttribute("errors", validation);
//            model.addAttribute("user", user);
//            return "forms/register";
//        }
//        String hashedPassword = encoder.encode(user.getPassword());
//        user.setPassword(hashedPassword);
//        repository.save(user);
//        return "redirect:/login";
//
//    }


    @GetMapping("forms/purchase")
    public String purchasePage(Model model) { return "forms/purchase"; }


}

