package com.app.captain.model;

import org.springframework.data.annotation.Id;

public class Captain {
    @Id
    private String id;

    private Long teamId;

    private Long participantId;

    private String participantIdentifier;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
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

    @Override
    public String toString() {
        return "Captain{" +
                "id='" + id + '\'' +
                ", teamId=" + teamId +
                ", participantId=" + participantId +
                ", participantIdentifier='" + participantIdentifier + '\'' +
                '}';
    }
}
