package com.artiscene.controllers;

import com.artiscene.models.Project;
import com.artiscene.models.Tag;
import com.artiscene.models.User;
import com.artiscene.repositories.ProjectRepository;
import com.artiscene.repositories.TagRepository;
import com.artiscene.repositories.UserRepository;
import com.artiscene.services.ProjectService;
import com.artiscene.services.usersSvc;
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
    private TagRepository tagDao;
    @Autowired
    private com.artiscene.services.usersSvc usersSvc;

    @GetMapping("/portfolio")
    public String portfolioPage(@ModelAttribute Project project, Model model){
        User user =(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("projects", projectRepository.findByUser(user));
        model.addAttribute("project", project);
        model.addAttribute("user", new User());
        model.addAttribute("loggedInUser", user);

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
        projectService.save(project);
        return "redirect:/portfolio";
    }

    @GetMapping("/portfolio/{id}")
    public String userPortfolioPage(Model model, @PathVariable Long id){
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

    @GetMapping("/portfolio/create")
    public String showCreate(Model model){
        model.addAttribute("project", new Project());
        model.addAttribute("tags", tagDao.findAll());
//        ? get current form for uploading projects
//          modal in fragments named upload-modal
        return "fragments/upload-modal";
    }


    @PostMapping("/portfolio/create")
    public String createProjects(@Valid Project projectCreated,Model model) {
        projectCreated.setTags((List<Tag>) tagDao.findAll());
        projectCreated.setUser(usersSvc.loggedInUser());
        projectService.save(projectCreated);
        return "redirect:/portfolio";
    }
}
