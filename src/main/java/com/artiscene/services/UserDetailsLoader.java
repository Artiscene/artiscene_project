package com.artiscene.services;

import com.artiscene.models.Role;
import com.artiscene.models.User;

import com.artiscene.repositories.RolesRepository;
import com.artiscene.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Brian on 2/21/17.
 */
@Service
public class UserDetailsLoader implements UserDetailsService {
    private final UserRepository users;
    private final RolesRepository roles;

    @Autowired
    public UserDetailsLoader(UserRepository users, RolesRepository roles){
        this.users=users;
        this.roles=roles;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        User user = users.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("No user found for: " + username);
        }

        List<String> userRoles = roles.ofUserWith(username);
        return new UsersWithRoles(user, userRoles);
    }
}
