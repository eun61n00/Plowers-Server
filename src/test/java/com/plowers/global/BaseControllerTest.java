package com.plowers.global;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.plowers.domain.user.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

public class BaseControllerTest {

    public MockMvc mockMvc;
    public ObjectMapper objectMapper = new ObjectMapper();

    public User user;

    @BeforeEach
    public void setup(WebApplicationContext context) {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .addFilter(new CharacterEncodingFilter("UTF-8", true))
                .build();

        user = new User(
                "tester",
                "P@ssw0rd",
                "test@email.com"
        );

    }

}
