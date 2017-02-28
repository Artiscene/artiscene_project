package com.artiscene.services;

import com.artiscene.models.Project;
import com.artiscene.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * Created by vanessamnoble on 2/28/17.
 */
@Service("usersSvc")
public class usersSvc {

    public boolean isLoggedIn() {
        boolean isAnonymousUser =
                SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken;
        return ! isAnonymousUser;
    }

    public User loggedInUser() {
        if (! isLoggedIn()) {
            return null;
        }
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
