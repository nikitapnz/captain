package com.app.captain.dto;

import com.app.captain.model.Captain;

public class ParticipantInfoDTO {
    private Long participantId;

    private String participantIdentifier;

    public ParticipantInfoDTO(Captain captain) {
        participantId = captain.getParticipantId();
        participantIdentifier = captain.getParticipantIdentifier();
    }

    public Long getParticipantId() {
        return participantId;
    }

    public void setParticipantId(Long participantId) {
        this.participantId = participantId;
    }

    public String getParticipantIdentifier() {
        return participantIdentifier;
    }

    public void setParticipantIdentifier(String participantIdentifier) {
        this.participantIdentifier = participantIdentifier;
    }
}
