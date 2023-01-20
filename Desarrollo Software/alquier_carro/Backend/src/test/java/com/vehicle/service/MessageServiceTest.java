package com.vehicle.service;

import com.vehicle.dto.MessageResponse;
import com.vehicle.factory.MessageDataTest;
import com.vehicle.mapper.MessageMapper;
import com.vehicle.model.Message;
import com.vehicle.repository.MessageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class MessageServiceTest {

    @InjectMocks
    MessageService messageService;
    @Mock
    MessageRepository messageRepository;
    @Mock
    MessageMapper messageMapper;
    private final Message message = new Message();
    @BeforeEach
    void setUp() {
        message.setIdMessage(1);
        message.setMessageText("Message 1");
        message.setScore(5.0);
    }

    @Test
    void mustGetAllMessagesOfDB() {
        MessageResponse messageResponse = MessageDataTest.getMessageResponse();

        when(messageRepository.findAll()).thenReturn(List.of(message));
        when(messageMapper.toMessageResponseList(List.of(message))).thenReturn(List.of(messageResponse));

        messageService.getAllMessages();
        verify(messageRepository).findAll();
    }

    @Test
    void mustGetSpecificMessageByIdOfDB() {


    }

    @Test
    void saveMessage() {
    }

    @Test
    void deleteMessage() {
    }

    @Test
    void updateMessage() {
    }
}