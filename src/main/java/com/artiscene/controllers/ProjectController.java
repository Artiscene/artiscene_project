package com.artiscene.controllers;

import com.artiscene.models.Project;
import com.artiscene.models.User;
import com.artiscene.repositories.UserRepository;
import com.artiscene.services.ProjectService;
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
    private final UserRepository userRepository;
    @Value("${file-upload-path}")
    private String uploadPath;
    private ProjectService service;

    @Autowired
    public ProjectController(ProjectService service, UserRepository userRepository){
        this.service=service;
        this.userRepository=userRepository;
    }


    @GetMapping("/gallery")
    public String showAllProjects(Model model){
        model.addAttribute("projects", Collections.emptyList());
        return "/gallery";
    }

    @GetMapping("/gallery.json")
    public @ResponseBody List<Project> retrieveAllProjects(){
        return service.all();
    }

    @GetMapping("/project/{id}")
    public String showOneProject(@PathVariable Long id, Model model){
        model.addAttribute("project", service.findOneProject(id));
        return "forms/show";
    }

    @GetMapping("/forms/upload")
    public String showCreateProjectForm(@ModelAttribute Project project, Model model){
        model.addAttribute("project", project);
        return "forms/upload";
    }

    @PostMapping("/forms/upload")
    public String saveProject(
            @Valid Project project,
            Errors validation,
            Model model,
            @RequestParam(name="file") MultipartFile uploadedFile) throws IOException{
                if(validation.hasErrors()){
                    model.addAttribute("errors", validation);
                    model.addAttribute("project", project);
                    return "forms/upload";
                }

                String filename = uploadedFile.getOriginalFilename();
                String destinationPath = Paths.get(uploadsFolder(), filename).toString();
                uploadedFile.transferTo(new File(destinationPath));

                User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(user);
                project.setUser(userRepository.findOne(user.getId()));
                project.setImg_url(filename);
                service.save(project);
                return "redirect:/portfolio";
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
    public String updateProject(@ModelAttribute Project project, Model model){
        service.update(project);
        model.addAttribute("project", project);
        return "redirect:/portfolio";
    }







}