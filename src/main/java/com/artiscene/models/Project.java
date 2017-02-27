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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "Title cannot be empty")
    private String title;


    @Column(name="for_sale")
    @Type(type="org.hibernate.type.NumericBooleanType")
    private Boolean forSale;


    @Column(nullable = false, length = 100)
    private String img_url;

    @Column(length = 100)
    private String size;

    @Column(length= 200)
    private String medium;

    @Column(length = 100)
    private String date;

    @Column(length = 50)
    private String price;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "project")
    private List<Favorite> favorites;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @JsonManagedReference
    private User user;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name="tag_projects",
            joinColumns={@JoinColumn(name="tag_id")},
            inverseJoinColumns={@JoinColumn(name="project_id")}
    )

    private List<Tag> tags;

    public Project(){}

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public List<Favorite> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<Favorite> favorites) {
        this.favorites = favorites;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return this.id;
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

    public Boolean getForSale() {
        return forSale;
    }

    public void setForSale(Boolean forSale) {
        this.forSale = forSale;
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


    public Project(String title, Boolean forSale, String img_url, String size, String medium, String date, String price, List<Favorite> favorites, User user, List<Tag> tags) {
        this.title = title;
        this.forSale = forSale;
        this.img_url = img_url;
        this.size = size;
        this.medium = medium;
        this.date = date;
        this.price = price;
        this.favorites = favorites;
        this.user = user;
        this.tags = tags;
    }

}
