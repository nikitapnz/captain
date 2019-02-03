package com.app.captain.service;

import com.app.captain.dto.ParticipantInfoDTO;
import com.app.captain.model.Captain;
import com.app.captain.repository.CaptainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CaptainServiceImpl implements CaptainService {
    @Autowired
    CaptainRepository captainRepository;

    @Override
    public ParticipantInfoDTO findCaptainByTeamId(Long teamId) {
        if (teamId == null) {
            return null;
        }

        Optional<Captain> captain = captainRepository.findByTeamId(teamId);

        return captain.map(ParticipantInfoDTO::new)
                .orElse(null);
    }

    @Override
    public void save(Captain captain) {
        captainRepository.save(captain);
    }

    @Override
    public void remove(Long participantId) {
        captainRepository.removeByParticipantId(participantId);
    }
}
