package com.app.captain.repository;

import com.app.captain.model.Captain;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CaptainRepository extends MongoRepository<Captain, String> {
    Optional<Captain> findByTeamId(Long teamId);

    void removeByParticipantId(Long participantId);
}
