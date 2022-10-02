package com.rooter.carportv8.security;

import com.rooter.carportv8.model.UserCredentials;
import com.rooter.carportv8.repo.interfaces.UserCredentialsRepository;
import com.rooter.carportv8.searchPredicates.UserCredentialsPredicates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserCredentialsRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserCredentialsRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserCredentials user = userRepository.findOne(UserCredentialsPredicates.hasUsername(username)).
                orElseThrow(()->new UsernameNotFoundException("User " + username + " not found."));
        return UserDetailsImpl.build(user);
    }
}
