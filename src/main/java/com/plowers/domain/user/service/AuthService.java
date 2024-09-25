package com.plowers.domain.user.service;

import com.plowers.domain.user.dto.request.SignUpRequest;
import com.plowers.domain.user.dto.response.UserResponse;
import com.plowers.domain.user.entity.User;
import com.plowers.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public UserResponse signup(SignUpRequest signUpRequest) {
        User user = new User(
                signUpRequest.getUsername(),
                passwordEncoder.encode(signUpRequest.getPassword()),
                signUpRequest.getEmail()
        );
        return UserResponse.of(userRepository.save(user));
    }

}
