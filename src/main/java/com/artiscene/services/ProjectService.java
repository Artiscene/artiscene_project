package com.artiscene.services;

import com.artiscene.models.Project;
import com.artiscene.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Brian on 2/23/17.
 */
@Service
public class ProjectService {
    private ProjectRepository repository;

    @Autowired
    public ProjectService(ProjectRepository repository){
        this.repository=repository;
    }

    public void save(Project project){
        repository.save(project);
    }

    public List<Project> all(){
        return (List<Project>) repository.findAll();
    }

    public Project findOneProject(Long id){
        return repository.findOne(id);
    }

    public void update(Project project){
        repository.save(project);
    }
}
