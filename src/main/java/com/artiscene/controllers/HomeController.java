package com.artiscene.controllers;

import com.artiscene.models.Project;
import com.artiscene.models.User;
import com.artiscene.repositories.UserRepository;
import com.artiscene.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.swing.*;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by HKoehler on 2/17/17.
 */

@Controller
public class HomeController {
    private UserRepository repository;
    private PasswordEncoder encoder;

    @Autowired
    private ProjectService service;

    @Autowired
    public HomeController(UserRepository repository, PasswordEncoder encoder){
        this.repository=repository;
        this.encoder=encoder;
    }

    @GetMapping("/")
    public String homepage(Model model) {
        model.addAttribute("user", new User());
        return "home";
    }


    @PostMapping("/register")
    public String registerUser(@Valid User user, Errors validation, Model model, @RequestParam(name="password_confirm") String passwordConfirmation) {

        if (!passwordConfirmation.equals(user.getPassword())) {
            validation.rejectValue("password", "user.password", "Your passwords do not match");
        }

        if (validation.hasErrors()) {
            model.addAttribute("errors", validation);
            model.addAttribute("user", user);
            return "redirect:/";
        }
        String hashedPassword = encoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        repository.save(user);
        return "redirect:/";

    }

    @GetMapping("/home.json")
    public @ResponseBody
    List<Project> retrieveAllProjects(){
        return service.all();
    }


    @GetMapping("/home/random")
    public String currentUsers(){
        User user = repository.randomUser();
        return "redirect:/portfolio/"+ user.getId();

    }

}

