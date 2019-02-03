package com.app.captain.service;

import com.app.captain.dto.ParticipantInfoDTO;
import com.app.captain.model.Captain;

public interface CaptainService {
    ParticipantInfoDTO findCaptainByTeamId(Long teamId);
    void save(Captain captain);
    void remove(Long participantId);
}
