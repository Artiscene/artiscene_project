package com.artiscene.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

/**
 * Created by vanessamnoble on 2/17/17.
 */
/*
@Entity
@Table(name="favorites")
public class Favorite {
    public Favorite(User user, Project project) {
        this.user = user;
        this.project = project;
    }

    @ManyToOne
    @JoinColumn (name = "user_id") // define at the table level
    @JsonManagedReference
    private User user;  // owner, author

    @ManyToOne
    @JoinColumn (name = "project_id") // define at the table level
    @JsonManagedReference
    private Project project;  // owner, author

    public Favorite(){}

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
*/
