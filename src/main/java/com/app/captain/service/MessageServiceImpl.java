package com.app.captain.service;

import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {
    @Override
    public void printMessage(String message) {
        System.out.println(message);
    }
}
