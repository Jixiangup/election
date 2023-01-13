package com.bnyte.election.api.controller;

import com.bnyte.election.api.service.IUserService;
import com.bnyte.election.api.utils.UserUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.mail.MailSenderAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@EnableAutoConfiguration(exclude = MailSenderAutoConfiguration.class)
public class CandidateControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    IUserService userService;

    private Long userid = 1L;

    @BeforeEach
    public void setUp() {
        userid = UserUtils.registerAdmin(userService);
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void editor() throws Exception {
        String payload = """
                {
                    "nickname": "测试数据"
                }
                """;
        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/candidate/editor")
                        .header("userid", 1)
                        .content(payload)
                        .contentType(MediaType.APPLICATION_JSON))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(MockMvcResultMatchers.status().isOk());
    }
}