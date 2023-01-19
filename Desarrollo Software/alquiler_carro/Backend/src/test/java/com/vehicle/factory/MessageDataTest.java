package com.vehicle.factory;

import com.vehicle.dto.MessageRequest;
import com.vehicle.dto.MessageResponse;
import com.vehicle.dto.MessageUpdate;

public class MessageDataTest {
    public static MessageResponse getMessageResponse() {
        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setMessageText("Message 1");
        messageResponse.setScore(5.0);
        return messageResponse;
    }
    public static MessageRequest getMessageRequest() {
        MessageRequest messageRequest = new MessageRequest();
        messageRequest.setMessageText("Message 1");
        messageRequest.setScore(5.0);
        return messageRequest;
    }
    public static MessageUpdate getMessageUpdate() {
        MessageUpdate messageUpdate = new MessageUpdate();
        messageUpdate.setIdMessage(1);
        messageUpdate.setMessageText("Message 1");
        messageUpdate.setScore(5.0);
        return messageUpdate;
    }
}
