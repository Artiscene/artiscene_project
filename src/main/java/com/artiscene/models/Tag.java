package com.artiscene.models;

import javax.persistence.*;
import java.util.List;

/**
 * Created by vanessamnoble on 2/17/17.
 */

@Entity
@Table(name = "tags")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 50)
    private String tag_name;

    @ManyToMany(mappedBy = "tags")
    private List<Project> projects;

    public Tag(){}

    public Tag(String tag_name) {
        this.tag_name = tag_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTag_name() {
        return tag_name;
    }

    public void setTag_name(String tag_name) {
        this.tag_name = tag_name;
    }
}
