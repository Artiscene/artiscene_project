package com.artiscene.controllers;

import com.artiscene.models.Project;
import com.artiscene.models.Tag;
import com.artiscene.models.User;
import com.artiscene.repositories.TagRepository;
import com.artiscene.repositories.UserRepository;
import com.artiscene.services.ProjectService;
import com.artiscene.services.usersSvc;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

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
    private usersSvc usersSvc;



    @GetMapping("/gallery")
    public String showAllProjects(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("projects", Collections.emptyList());
        return "/gallery";
    }

    @GetMapping("/gallery.json")
    public @ResponseBody List<Project> retrieveAllProjects(){
        return service.all();
    }

    @GetMapping("/view/{id}")
    public String showOneProject(@PathVariable Long id, Model model){
        model.addAttribute("project", service.findOneProject(id));
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
    @GetMapping("/project/create")
    public String showCreate(Model model){
        model.addAttribute("project", new Project());
        model.addAttribute("tags", tagDao.findAll());
//        ? get current form for uploading projects
//          modal in fragments named upload-modal
        return "fragments/upload-modal";
    }


    @PostMapping("/project/create")
    public String createProjects(@Valid Project projectCreated,Model model) {
        projectCreated.setTags((List<Tag>) tagDao.findAll());
        projectCreated.setUser(usersSvc.loggedInUser());
        usersSvc.save(projectCreated);
        return "redirect:/portfolio";
    }

}
