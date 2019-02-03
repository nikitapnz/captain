package com.app.captain.test;


import com.app.captain.model.Captain;
import com.app.captain.repository.CaptainRepository;
import com.app.captain.service.CaptainService;
import com.app.captain.web.CaptainWebApi;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.junit.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class WebCaptainTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    CaptainWebApi captainWebApi;

    @Autowired
    CaptainService captainService;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    CaptainRepository captainRepository;

    Captain captain;

    @Before
    public void createCaptain() {
        captain = new Captain();
        captain.setId("someid");
        captain.setParticipantId(44L);
        captain.setParticipantIdentifier("Билл Дуров");
        captain.setTeamId(33L);

        captainRepository.deleteAll();
    }

    @Test
    public void saveCaptain() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/captain")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(captain)))
                .andExpect(MockMvcResultMatchers.status().isOk());

        captainRepository.delete(captain);
    }

    @Test
    public void saveCaptainIfCaptainAlreadyExist() throws Exception {
        captainRepository.save(captain);

        mockMvc.perform(MockMvcRequestBuilders.post("/captain")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(captain)))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.success").value(false));

        captainRepository.delete(captain);
    }

    @Test
    public void getCaptain() throws Exception {
        captainRepository.save(captain);

        mockMvc.perform(MockMvcRequestBuilders.get("/captain/33"))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.participantIdentifier").value("Билл Дуров"))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.participantId").value(44));

        captainRepository.delete(captain);
    }

    @Test
    public void getCaptainIfCaptainDoesntExist() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/captain/4444"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void removeCaptain() throws Exception {
        captainRepository.save(captain);
        mockMvc.perform(MockMvcRequestBuilders.delete("/captain/44"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        Assert.assertFalse(captainRepository.findById("44").isPresent());
    }
}
