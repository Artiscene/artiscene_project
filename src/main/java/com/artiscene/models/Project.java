package com.artiscene.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.List;

/**
 * Created by vanessamnoble on 2/17/17.
 */
@Entity
@Table(name="project")
public class Project {
    @Id
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "project")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    @NotBlank(message = "Title cannot be empty")
    private String title;


    @Column(name="for_sale", nullable = false)
    @Type(type="org.hibernate.type.NumericBooleanType")
    private Boolean forSale;

    @Column(nullable = false)
    private int views;

    @Column(nullable = false, length = 100)
    private String img_url;

    @ManyToOne
    @JoinColumn(name="tag_id") //define at the tag level
    @JsonManagedReference
    private List<Tag> tags;

    @ManyToOne
    @JoinColumn (name = "user_id") // define at the table level
    @JsonManagedReference
    private User user;  // owner, author

    public Project(){}

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getForSale() {
        return forSale;
    }

    public void setForSale(Boolean forSale) {
        this.forSale = forSale;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Project(Project project) {
        this.title = project.title;
        this.forSale = project.forSale;
        this.views = project.views;
        this.img_url = project.img_url;
        this.tags = project.tags;
        this.user = project.user;
    }

}
