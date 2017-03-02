package com.artiscene.controllers;

import com.artiscene.models.Project;
import com.artiscene.models.Tag;
import com.artiscene.models.User;
import com.artiscene.repositories.ProjectRepository;
import com.artiscene.repositories.TagRepository;
import com.artiscene.repositories.UserRepository;
import com.artiscene.services.ProjectService;
import com.artiscene.services.UserSvc;
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
import java.util.List;
import java.util.Objects;

/**
 * Created by HKoehler on 2/17/17.
 */

@Controller
public class PortfolioController {

    @Autowired
    public PortfolioController(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    private ProjectRepository projectRepository;

    @Value("${file-upload-path}")
    private String uploadPath;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserSvc userSvc;
    @Autowired
    private TagRepository tagDao;


    @GetMapping("/portfolio")
    public String portfolioPage(@ModelAttribute Project project, Model model){
        User user=userSvc.loggedInUser();
        model.addAttribute("projects", projectRepository.findByUser(user));
        model.addAttribute("project", project);
        model.addAttribute("user", new User());
        model.addAttribute("loggedInUser", userRepository.findOne(user.getId()));
        model.addAttribute("showEditControls", userSvc.isLoggedIn() && Objects.equals(user.getUsername(), userSvc.loggedInUser().getUsername()));


        //model.addAttribute("loggedInUser", user);
        model.addAttribute("tags", tagDao.findAll());
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
            @RequestParam(name="file") MultipartFile uploadedFile,
            @RequestParam(name="tags") List<Tag> tags) throws IOException {
        if (validation.hasErrors()) {
            model.addAttribute("errors", validation);
            model.addAttribute("project", project);
            return "portfolio";
        }

        String filename = uploadedFile.getOriginalFilename();
        String destinationPath = Paths.get(uploadsFolder(), filename).toString();
        uploadedFile.transferTo(new File(destinationPath));

        User user = userSvc.loggedInUser();
        project.setUser(userRepository.findOne(user.getId()));
        project.setImg_url(filename);
        project.setTags(tags);
        projectService.save(project);
        return "redirect:/portfolio";
    }

    @GetMapping("/portfolio/{id}")
    public String userPortfolioPage(Model model, @PathVariable Long id, User user){
        projectRepository.findByUserId(id);
        model.addAttribute("projects", projectRepository.findByUserId(id));
        model.addAttribute("project", new Project());
        model.addAttribute("user", new User());
        model.addAttribute("loggedInUser", userRepository.findOne(id));

        return "portfolio";
    }

    @GetMapping("/getportfolio.json")
    @ResponseBody
    public List<Project> retrieveUserProjects(@RequestParam Long id){
        return projectService.findByUser(userRepository.findOne(id));
    }

//    @GetMapping("/portfolio.json")
//    @ResponseBody
//    public List<Users>
}
