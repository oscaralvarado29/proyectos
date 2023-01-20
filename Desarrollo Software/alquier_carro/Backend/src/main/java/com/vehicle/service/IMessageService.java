package com.vehicle.service;

import com.vehicle.dto.MessageRequest;
import com.vehicle.dto.MessageResponse;
import com.vehicle.dto.MessageUpdate;


import java.util.List;

public interface IMessageService {
    List<MessageResponse> getAllMessages();
    MessageResponse getMessage (int messageId);
    void saveMessage(MessageRequest messageRequest);
    void deleteMessage(int messageId);
    void updateMessage(MessageUpdate messageUpdate);
}
