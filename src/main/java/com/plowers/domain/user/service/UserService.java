package com.plowers.domain.user.service;

import com.plowers.domain.user.repository.UserRepository;
import com.plowers.global.exception.PlowersException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static com.plowers.global.exception.ErrorMap.USER_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new PlowersException(USER_NOT_FOUND));
    }
}

