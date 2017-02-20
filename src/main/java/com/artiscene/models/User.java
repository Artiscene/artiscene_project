package com.artiscene.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.javafx.beans.IDProperty;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by vanessamnoble on 2/17/17.
 */
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private int id;

    @Column(nullable = false)
    @NotBlank(message = "Enter a username")
    private String username;

    @Column(nullable = false)
    @NotBlank(message = "Enter an email")
    @Email(message = "Enter a valid email address")
    private String email;

    @Column(nullable = false)
    @NotBlank(message = "Your password cannot be empty")
    @Size(min = 8, message = "Your password should have at least 8 characters")
    @JsonIgnore
    private String password;

    @Column(nullable = true, length = 100)
    private String phone;

    @Column(nullable = false, length = 100)
    private String profile_pic;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="user")
    @JsonBackReference
    private List<Project> projects;

    public User(){}

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProfile_pic() {
        return this.profile_pic;
    }

    public void setProfile_pic(String profile_pic) {
        this.profile_pic = profile_pic;
    }

    public List<Project> getProjects() {
        return this.projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public User(User user) {
        this.id = user.id;
        this.username = user.username;
        this.email = user.email;
        this.password = user.password;
        this.phone = user.phone;
        this.profile_pic = user.profile_pic;
        this.projects = user.projects;
    }

}
