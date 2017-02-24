package com.artiscene.repositories;

import com.artiscene.models.Project;
import com.artiscene.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Brian on 2/23/17.
 */
@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {


    public List<Project> findByUser(User user);//finds user to populate their profile with their projects

    public List<Project> findByUserId(Long userId);

}
