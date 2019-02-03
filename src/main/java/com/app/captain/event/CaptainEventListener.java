package com.app.captain.event;

import com.app.captain.model.Captain;
import com.app.captain.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterDeleteEvent;
import org.springframework.data.mongodb.core.mapping.event.AfterSaveEvent;
import org.springframework.stereotype.Component;

@Component
public class CaptainEventListener extends AbstractMongoEventListener<Captain> {
    private final MessageService messageService;

    @Autowired
    public CaptainEventListener(MessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public void onAfterSave(AfterSaveEvent<Captain> event) {
        messageService.printMessage("Назначен капитан команды. ParticipantId = " + event.getDocument().get("participantId"));
    }

    @Override
    public void onAfterDelete(AfterDeleteEvent<Captain> event) {
        messageService.printMessage("Капитан команды удалён. ParticipantId = " + event.getDocument().get("participantId"));
    }
}
