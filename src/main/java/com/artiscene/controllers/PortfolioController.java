package com.artiscene.controllers;

import com.artiscene.models.Project;
import com.artiscene.models.User;
import com.artiscene.repositories.ProjectRepository;
import com.artiscene.repositories.UserRepository;
import com.artiscene.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

/**
 * Created by HKoehler on 2/17/17.
 */

@Controller
public class PortfolioController {

    @Autowired
    public PortfolioController(ProjectRepository repository) {
        this.repository = repository;
    }

    private ProjectRepository repository;

    @Value("${file-upload-path}")
    private String uploadPath;
    @Autowired
    private ProjectService service;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/portfolio")
    public String portfolioPage(@ModelAttribute Project project, Model model){
        User user =(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("projects", repository.findByUser(user));
        model.addAttribute("project", project);
        model.addAttribute("user", new User());
        return "portfolio";
    }
    private String uploadsFolder() throws IOException {
        return String.format("%s/%s", new File(".").getCanonicalPath(), uploadPath);
    }

    @PostMapping("/portfolio")
    public String saveProject(
            @Valid Project project,
            Errors validation,
            Model model,
            @RequestParam(name="file") MultipartFile uploadedFile) throws IOException {
        if(validation.hasErrors()){
            model.addAttribute("errors", validation);
            model.addAttribute("project", project);
            return "portfolio";
        }

        String filename = uploadedFile.getOriginalFilename();
        String destinationPath = Paths.get(uploadsFolder(), filename).toString();
        uploadedFile.transferTo(new File(destinationPath));

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        project.setUser(userRepository.findOne(user.getId()));
        project.setImg_url(filename);
        service.save(project);
        return "redirect:/portfolio";
    }

    @GetMapping("/portolio/{id}")
    public String userPortfolioPage(Model model, @PathVariable Long id){
        repository.findByUserId(id);
        model.addAttribute("projects", repository.findByUserId(id));
        return "portfolio";
    }
}
