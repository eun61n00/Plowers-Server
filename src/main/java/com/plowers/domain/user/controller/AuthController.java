package com.plowers.domain.user.controller;

import com.plowers.domain.user.dto.request.SignUpRequest;
import com.plowers.domain.user.dto.response.UserResponse;
import com.plowers.domain.user.service.AuthService;
import com.plowers.domain.user.service.UserService;
import com.plowers.global.dto.response.ResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<Object> signup(@RequestBody SignUpRequest request) {
        UserResponse newUser = authService.signup(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ResultResponse.create("Sign Up Success", newUser));
    }

}
