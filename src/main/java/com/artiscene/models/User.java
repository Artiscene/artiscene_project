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
    private Long id;

    @Column(nullable = false, unique=true)
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

    @Column(nullable=true,length = 100)
    private String profile_pic;


    @Column(nullable = true, length=100)
    private String accessToken;

    @Column(nullable = true, length = 100)
    private String refreshToken;

    @Column(nullable = true, length = 100)
    private String stripePublishableKey;

    @Column(nullable = true, length = 100)
    private String stripeUserId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Favorite> favorites;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Posts> post;


    @Column(length = 2000)
    private String bio;

    @Column(length = 300)
    private String location;

    @Column(length=200)
    private String interests;

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getInterests() {
        return interests;
    }

    public void setInterests(String interests) {
        this.interests = interests;
    }

    public List<Favorite> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<Favorite> favorites) {
        this.favorites = favorites;
    }

    public List<Posts> getPost() {
        return post;
    }

    public void setPost(List<Posts> post) {
        this.post = post;
    }

    public User(){}

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
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

    public String getProfile_pic() {
        return this.profile_pic;
    }

    public void setProfile_pic(String profile_pic) {
        this.profile_pic = profile_pic;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getStripePublishableKey() {
        return stripePublishableKey;
    }

    public void setStripePublishableKey(String stripePublishableKey) {
        this.stripePublishableKey = stripePublishableKey;
    }

    public String getStripeUserId() {
        return stripeUserId;
    }

    public void setStripeUserId(String stripeUserId) {
        this.stripeUserId = stripeUserId;
    }


    public User(User user) {

        this.id=user.id;
        this.username = user.username;
        this.email = user.email;
        this.password = user.password;

        this.profile_pic = user.profile_pic;
        this.favorites = user.favorites;
        this.post = user.post;
        this.accessToken = user.accessToken;
        this.refreshToken = user.refreshToken;
        this.stripePublishableKey = user.stripePublishableKey;
        this.stripeUserId = user.stripeUserId;
        this.bio=user.bio;
        this.location=user.location;
        this.interests=user.interests;
    }

    public void setaccessToken(String access_token) {
        accessToken = access_token;
    }

    public void setrefreshToken(String refresh_token) {
        refreshToken = refresh_token;
    }

    public void setstripePublishableKey(String stripe_publishable_key) {
        stripePublishableKey = stripe_publishable_key;
    }

    public void setstripeUserId(String stripe_user_id) {
        stripeUserId = stripe_user_id;
    }
}
