package com.artiscene.repositories;

import com.artiscene.models.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Brian on 2/23/17.
 */
@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {

}
