package com.bitbuy.usermanagement.config;


import com.bitbuy.usermanagement.dao.UserRepository;
import com.bitbuy.usermanagement.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> user = Optional.ofNullable(userRepository.findByUsername(username));

        if (!user.isPresent()) {
            throw new UsernameNotFoundException(username);
        }

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.get().getUsername(), user.get().getPassword(), true, true, true, true, new ArrayList<>());

        return userDetails;

    }
}
