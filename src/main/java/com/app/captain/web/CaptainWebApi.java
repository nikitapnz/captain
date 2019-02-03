package com.app.captain.web;

import com.app.captain.dto.ParticipantInfoDTO;
import com.app.captain.model.Captain;
import com.app.captain.service.CaptainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
public class CaptainWebApi {
    @Autowired
    CaptainService captainService;

    @Autowired
    Map<Long, Object> uniqueObjectForParticipant;

    @RequestMapping(path = "captain/{teamId}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getCaptainInfoByTeamId(@PathVariable("teamId") Long teamId) {
        ParticipantInfoDTO participantInfoDTO = captainService.findCaptainByTeamId(teamId);

        if (participantInfoDTO == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(participantInfoDTO, HttpStatus.OK);
    }

    @RequestMapping(path = "captain", method = RequestMethod.POST)
    public ResponseEntity saveNewCaptain(@RequestBody Captain captain) {
        uniqueObjectForParticipant.putIfAbsent(captain.getParticipantId(), new Object());

        synchronized (uniqueObjectForParticipant.get(captain.getParticipantId())) {
            ParticipantInfoDTO participantInfoDTO = captainService.findCaptainByTeamId(captain.getTeamId());
            if (participantInfoDTO != null) {
                return new ResponseEntity(Collections.singletonMap("success", false), HttpStatus.CONFLICT);
            }

            captainService.save(captain);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(path = "captain/{participantId}", method = RequestMethod.DELETE)
    public ResponseEntity removeCaptain(@PathVariable("participantId") Long participantId) {
        captainService.remove(participantId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
