package com.artiscene.models;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;

/**
 * Created by vanessamnoble on 2/22/17.
 */
@Entity
@Table(name="posts")
public class Posts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 300)
    @NotBlank(message = "Enter a Post Title")
    private String title;

    @Column(nullable = false, length = 1000)
    private String body;

    @Column(length = 50)
    private String date;

    @Column(length = 100)
    private String location;

    @Column(length = 250)
    private String category;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Posts(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Posts(String title, String body, String date, String location, String category, User user) {
        this.title = title;
        this.body = body;
        this.date = date;
        this.location = location;
        this.category = category;
        this.user = user;
    }
}