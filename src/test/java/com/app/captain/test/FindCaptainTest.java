package com.app.captain.test;

import com.app.captain.dto.ParticipantInfoDTO;
import com.app.captain.model.Captain;
import com.app.captain.repository.CaptainRepository;
import com.app.captain.service.CaptainService;
import com.app.captain.service.CaptainServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class FindCaptainTest {
    @MockBean
    CaptainRepository captainRepository;

    @Autowired
    CaptainService captainService;

    @Before
    public void setUp() {
        Captain captain = new Captain();
        captain.setId("someid");
        captain.setParticipantId(44L);
        captain.setParticipantIdentifier("Билл Дуров");
        captain.setTeamId(33L);
        when(captainRepository.findByTeamId(33L)).thenReturn(Optional.of(captain));

        when(captainRepository.findByTeamId(44L)).thenReturn(Optional.empty());
    }

    @Test
    public void findCaptainByExistTeamId(){
        ParticipantInfoDTO participantInfoDTO = captainService.findCaptainByTeamId(33L);
        Assert.assertEquals(44L, participantInfoDTO.getParticipantId().longValue());
        Assert.assertEquals("Билл Дуров", participantInfoDTO.getParticipantIdentifier());
    }

    @Test
    public void findCaptainIfDoesntExistTeamId(){
        ParticipantInfoDTO participantInfoDTO = captainService.findCaptainByTeamId(44L);
        Assert.assertNull(participantInfoDTO);
    }

    @Test
    public void findCaptainIfTeamIdEqualsNull(){
        ParticipantInfoDTO participantInfoDTO = captainService.findCaptainByTeamId(null);
        Assert.assertNull(participantInfoDTO);
    }

    @TestConfiguration
    static class CaptainServiceImplTestContextConfiguration {
        @Bean
        public CaptainService captainService() {
            return new CaptainServiceImpl();
        }
    }

}
