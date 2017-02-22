package com.artiscene.repositories;


import com.artiscene.models.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Brian on 2/21/17.
 */
@Repository
public interface RolesRepository extends CrudRepository<Role, Long> {
    @Query("select r.role from Role r, User u where u.username=?1 and r.userId = u.id")
    public List<String> ofUserWith(String username);
}
