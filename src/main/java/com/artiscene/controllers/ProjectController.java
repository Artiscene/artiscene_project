package com.artiscene.controllers;

import com.artiscene.models.Project;
import com.artiscene.models.Tag;
import com.artiscene.models.User;
import com.artiscene.repositories.ProjectRepository;
import com.artiscene.repositories.TagRepository;
import com.artiscene.repositories.UserRepository;
import com.artiscene.services.ProjectService;
import com.artiscene.services.UserSvc;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Created by Brian on 2/23/17.
 */
@Controller
public class ProjectController {

    @Value("${file-upload-path}")
    private String uploadPath;
    @Autowired
    private ProjectService service;
    @Autowired
    private TagRepository tagDao;
    @Autowired
    private ProjectRepository projectDao;
    @Autowired
    private UserSvc userSvc;
    @Autowired
    UserRepository userRepository;



    @GetMapping("/gallery")
    public String showAllProjects(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("projects", Collections.emptyList());
        model.addAttribute("tags", tagDao.findAll());
        return "/gallery";
    }

    @PostMapping("/gallery")
    public String searchByTags(
            Model model){

        return  "redirect:/gallery";
    }




    @GetMapping("/gallery.json")
    public @ResponseBody List<Project> retrieveAllProjects(){
        return service.all();
    }

    @GetMapping("/view/{id}")
    public String showOneProject(@PathVariable Long id, Model model){
        Project project = service.findOneProject(id);
        model.addAttribute("project", project);
        User user;
        if (userSvc.isLoggedIn()) {
            user = userSvc.loggedInUser();
        } else {
            user = new User();
        }
        model.addAttribute("loggedInUser", userRepository.findOne(user.getId()));
        model.addAttribute("showDeleteControls", userSvc.isLoggedIn() && Objects.equals(project.getUser().getUsername(), userSvc.loggedInUser().getUsername()));
        return "artwork/view";
    }

    @GetMapping("/projects/image/{filename:.+")
    public HttpEntity<byte[]> showProjectImage(@PathVariable String filename) throws IOException{
        String imagePath = String.format("%s/%s", uploadsFolder(), filename);
        byte[] image = IOUtils.toByteArray(FileUtils.openInputStream(new File(imagePath)));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        headers.setContentLength(image.length);
        return new HttpEntity<>(image,headers);
    }

    private String uploadsFolder() throws IOException {
        return String.format("%s/%s", new File(".").getCanonicalPath(), uploadPath);
    }

    @GetMapping("/project/{id}/edit")
    @PreAuthorize("@projectOwnerExpression.isOwner(principal, #id)")
    public String showEditProjectForm(@PathVariable Long id, Model model){
        model.addAttribute("project", service.findOneProject(id));
        return"forms/edit";
    }

    @PostMapping("/project/{id}/edit")
    @PreAuthorize("@projectOwnerExpression.isOwner(principal, #project.id)")
    public String updateProject(@ModelAttribute Project project, @ModelAttribute List<Tag> tags, Model model){
        service.update(project);
        model.addAttribute("project", project);
        return "redirect:/portfolio";
    }


    @PostMapping("/project/delete")
    public String deleteProject(@ModelAttribute Project project, Model model ){


        projectDao.delete(service.findOneProject(project.getId()));
        return "redirect:/portfolio";
    }


}
