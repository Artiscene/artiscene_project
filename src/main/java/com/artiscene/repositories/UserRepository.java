package com.artiscene.repositories;

import com.artiscene.models.User;
import com.sun.tools.javac.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Brian on 2/21/17.
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    public User findByUsername(String username);
//    List<User> findByUserName(String UserName);
}
