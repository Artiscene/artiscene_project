package com.artiscene.controllers;

import com.artiscene.models.Project;
import com.artiscene.models.User;
import com.artiscene.repositories.UserRepository;
import com.artiscene.services.UserSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * Created by Brian on 2/28/17.
 */
@Controller
public class UserController {
    @Autowired
    public UserController(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    private UserRepository userRepository;

    @Value("${file-upload-path}")
    private String uploadPath;

    @Autowired
    private UserSvc userSvc;

    private String uploadsFolder() throws IOException {
        return String.format("%s/%s", new File(".").getCanonicalPath(), uploadPath);
    }

    @PostMapping("/profile/edit")
    public String editPortfolio(@Valid User user, Errors validation, Model model,  @RequestParam("profile_pic") MultipartFile uploadedFile) {
        System.out.println("edit fx");
        User userToUpdated = userRepository.findOne(user.getId());


        int errorCount = validation.getErrorCount();
        if (errorCount > 0) {
            if (validation.hasFieldErrors("username")) errorCount--;
            if (validation.hasFieldErrors("password")) errorCount--;
            if (validation.hasFieldErrors("profile_pic")) errorCount--;
        }


        if(errorCount > 0){

            model.addAttribute("errors", validation);
            model.addAttribute("loggedInUser", userToUpdated);
            model.addAttribute("project", new Project());
            model.addAttribute("showEditControls", true);

            return "portfolio";
        }


        if(!uploadedFile.getOriginalFilename().isEmpty()){

            String filename = uploadedFile.getOriginalFilename().replace(" ", "_").toLowerCase();
            String filepath = Paths.get(uploadPath, filename).toString();
            File destinationFile = new File(filepath);

            // Try to save it in the server
            try {
                uploadedFile.transferTo(destinationFile);
                model.addAttribute("message", "File successfully uploaded!");
            } catch (IOException e) {
                e.printStackTrace();
                model.addAttribute("message", "Oops! Something went wrong! " + e);
            }

            //Save it in the DB
            userToUpdated.setProfile_pic(filename);
        }

            //User loggedInUser = userSvc.loggedInUser();
            // user.setUser(userRepository.findOne(user.getId()));
            userToUpdated.setEmail(user.getEmail());
            userToUpdated.setBio(user.getBio());
            userToUpdated.setLocation(user.getLocation());
            userToUpdated.setInterests(user.getInterests());

            userRepository.save(userToUpdated);

        return "redirect:/portfolio";
    }
}
