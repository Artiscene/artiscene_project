package com.artiscene.repositories;

import com.artiscene.models.Project;
import com.artiscene.models.Role;
import com.artiscene.models.Tag;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by vanessamnoble on 2/27/17.
 */
@Repository
public interface TagRepository extends CrudRepository<Tag, Long>  {
    @Query("select p\n" +
            "from Project p join p.tags t\n" +
            "WHERE  t.id = ?1")
    public List<Project> projectWithTag(long tagId);
}
