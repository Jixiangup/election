package com.bnyte.election.api.controller;

import com.bnyte.election.api.entity.Candidate;
import com.bnyte.election.api.service.ICandidateService;
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

import java.util.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@EnableAutoConfiguration(exclude = MailSenderAutoConfiguration.class)
class ElectionControllerTest {

    @Autowired
    private IUserService userService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ICandidateService candidateService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void editor() throws Exception {

        Candidate candidate = new Candidate();
        candidate.setNickname(UUID.randomUUID().toString());
        candidate.setDeleted(false);
        candidate.setCreateTime(new Date());
        candidate.setModifiedTime(new Date());

        Candidate candidate1 = new Candidate();
        candidate1.setNickname(UUID.randomUUID().toString());
        candidate1.setDeleted(false);
        candidate1.setCreateTime(new Date());
        candidate1.setModifiedTime(new Date());
        candidateService.saveOrUpdateById(candidate);
        candidateService.saveOrUpdateById(candidate1);

        String payload = """
                {
                     "candidateIds": [
                         1,
                         2
                     ]
                 }
                """;
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/election/editor")
                                .header("userid", UserUtils.registerAdmin(userService))
                                .content(payload)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}