package com.plowers.domain.user.controller;

import com.plowers.domain.user.dto.request.SignUpRequest;
import com.plowers.domain.user.dto.response.UserResponse;
import com.plowers.domain.user.service.AuthService;
import com.plowers.global.BaseControllerTest;
import com.plowers.global.dto.response.ResultResponse;
import com.plowers.global.exception.PlowersException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.ResultActions;

import static com.plowers.global.exception.ErrorMap.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthController.class)
@DisplayName("AuthController")
public class AuthControllerTest extends BaseControllerTest {

    @MockBean
    private AuthService authService;

    @Nested
    @DisplayName("Signup")
    class SignupTest {

        SignUpRequest signUpRequest = new SignUpRequest(
                "test@email.com",
                "P@ssw0rd",
                "tester"
        );

        @Test
        @DisplayName("Success")
        void success() throws Exception {

            // given
            given(authService.signup(any())).willReturn(UserResponse.of(user));

            // when
            ResultActions actions = mockMvc.perform(post("/auth/signup")
                    .content(objectMapper.writeValueAsString(signUpRequest))
                    .contentType(APPLICATION_JSON));

            // then
            actions
                    .andDo(print())
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("data").exists())
                    .andExpect(content().json(objectMapper.writeValueAsString(ResultResponse.create("Sign Up Success", UserResponse.of(user)))));

        }

        @Test
        @DisplayName("Invalid Email Pattern")
        void invalidEmailPatternError() throws Exception {

            // given
            given(authService.signup(any(SignUpRequest.class))).willThrow(new PlowersException(INVALID_EMAIL_PATTERN));

            // when
            ResultActions actions = mockMvc.perform(post("/auth/signup")
                    .content(objectMapper.writeValueAsString(signUpRequest))
                    .contentType(APPLICATION_JSON));

            // then
            actions
                    .andDo(print())
                    .andExpect(status().isBadRequest())  // 400 Bad Request 예상
                    .andExpect(jsonPath("$.errorCode").value(1000))  // 에러 코드 확인
                    .andExpect(jsonPath("$.errorName").value("INVALID_EMAIL_PATTERN"))  // 에러 이름 확인
                    .andExpect(jsonPath("$.message").value("잘못된 이메일 형식입니다."));  // 메시지 확인

        }

        @Test
        @DisplayName("Invalid Password Pattern")
        void invalidPasswordPatternError() throws Exception {

            // given
            given(authService.signup(any(SignUpRequest.class))).willThrow(new PlowersException(INVALID_PASSWORD_PATTERN));

            // when
            ResultActions actions = mockMvc.perform(post("/auth/signup")
                    .content(objectMapper.writeValueAsString(signUpRequest))
                    .contentType(APPLICATION_JSON));

            // then
            actions
                    .andDo(print())
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.errorCode").value(1001))
                    .andExpect(jsonPath("$.errorName").value("INVALID_PASSWORD_PATTERN"))
                    .andExpect(jsonPath("$.message").value("비밀번호는 영문 소문자, 영문 대문자, 숫자, 특수문자가 각각 1개 이상 포함되어야 합니다."));

        }

        @Test
        @DisplayName("Invalid Password Length")
        void invalidPasswordLengthError() throws Exception {

            // given
            given(authService.signup(any(SignUpRequest.class))).willThrow(new PlowersException(INVALID_PASSWORD_LENGTH));

            // when
            ResultActions actions = mockMvc.perform(post("/auth/signup")
                    .content(objectMapper.writeValueAsString(signUpRequest))
                    .contentType(APPLICATION_JSON));

            // then
            actions
                    .andDo(print())
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.errorCode").value(1002))
                    .andExpect(jsonPath("$.errorName").value("INVALID_PASSWORD_LENGTH"))
                    .andExpect(jsonPath("$.message").value("비밀번호는 8자리 이상이어야합니다."));

        }

        @Test
        @DisplayName("User Already Exists")
        void userAlreadyExistsError() throws Exception {

            // given
            given(authService.signup(any(SignUpRequest.class))).willThrow(new PlowersException(USER_ALREADY_EXISTS));

            // when
            ResultActions actions = mockMvc.perform(post("/auth/signup")
                    .content(objectMapper.writeValueAsString(signUpRequest))
                    .contentType(APPLICATION_JSON));

            // then
            actions
                    .andDo(print())
                    .andExpect(status().isConflict())
                    .andExpect(jsonPath("$.errorCode").value(1003))
                    .andExpect(jsonPath("$.errorName").value("USER_ALREADY_EXISTS"))
                    .andExpect(jsonPath("$.message").value("이미 가입된 사용자입니다."));

        }

        @Test
        @DisplayName("Duplicate User Name")
        void duplicateUserNameError() throws Exception {
            // given
            given(authService.signup(any(SignUpRequest.class))).willThrow(new PlowersException(DUPLICATED_USERNAME));

            // when
            ResultActions actions = mockMvc.perform(post("/auth/signup")
                    .content(objectMapper.writeValueAsString(signUpRequest))
                    .contentType(APPLICATION_JSON));

            // then
            actions
                    .andDo(print())
                    .andExpect(status().isConflict())
                    .andExpect(jsonPath("$.errorCode").value(1004))
                    .andExpect(jsonPath("$.errorName").value("DUPLICATED_USERNAME"))
                    .andExpect(jsonPath("$.message").value("이미 사용중인 이름입니다."));

        }
    }

}
