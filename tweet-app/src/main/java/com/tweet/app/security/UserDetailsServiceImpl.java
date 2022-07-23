package com.tweet.app.security;

import com.tweet.app.entity.Users;
import com.tweet.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            Users user = userRepository.findByUserName(username).get(0);
            return new UserDetailsImpl(user);
        } catch (UsernameNotFoundException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
