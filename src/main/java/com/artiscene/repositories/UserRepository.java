package com.artiscene.repositories;

import com.artiscene.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Brian on 2/21/17.
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
    User findByEmail(String email);
    @Query(value="select * from users\n" +
            "ORDER BY rand()\n" +
            "LIMIT 1", nativeQuery = true)
    User randomUser();
}
