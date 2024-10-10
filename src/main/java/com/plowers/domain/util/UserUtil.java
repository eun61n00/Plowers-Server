package com.plowers.domain.util;

import com.plowers.domain.user.repository.UserRepository;
import com.plowers.global.exception.PlowersException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Pattern;

import static com.plowers.global.exception.ErrorMap.*;

@Service
@RequiredArgsConstructor
public class UserUtil {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public void isEmailDuplicate(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new PlowersException(USER_ALREADY_EXISTS);
        }
    }

    @Transactional(readOnly = true)
    public void isUsernameDuplicate(String username) {
        if (userRepository.existsByUsername(username)) {
            throw new PlowersException(DUPLICATED_USERNAME);
        };
    }

    public void validateEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(emailRegex);
        if (!pattern.matcher(email).matches()) {
            throw new PlowersException(INVALID_EMAIL_PATTERN);
        }
    }

    public void validatePassword(String password) {
        if (password.length() < 8) {
            throw new PlowersException(PASSWORD_TOO_SHORT);
        }
        String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#\\$%\\^&\\*]).{8,}$";
        Pattern pattern = Pattern.compile(passwordRegex);
        if (!pattern.matcher(password).matches()) {
            throw new PlowersException(INVALID_PASSWORD_PATTERN);
        }
    }

}
