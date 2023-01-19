package com.vehicle.service;

import java.util.List;
import java.util.Optional;

import com.vehicle.dto.MessageRequest;
import com.vehicle.dto.MessageResponse;
import com.vehicle.dto.MessageUpdate;
import com.vehicle.exception.MessageAlreadyExistsException;
import com.vehicle.exception.MessageNotFoundException;
import com.vehicle.mapper.MessageMapper;
import com.vehicle.model.Message;
import com.vehicle.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Oscar Alvarado
 */
@RequiredArgsConstructor
@Service
@Transactional
public class MessageService implements IMessageService {

    private final MessageRepository messageRepository;
    private final MessageMapper messageMapper;

    /**
     * @return  all messages from database
     */
    @Override
    public List<MessageResponse> getAllMessages() {
        return messageMapper.toMessageResponseList(messageRepository.findAll());
    }

    /**
     * @param messageId id of message to search
     * @return message with id equals to messageId
     */
    @Override
    public MessageResponse getMessage(int messageId) {
        Message message = messageRepository.findById(messageId).orElseThrow(MessageNotFoundException::new);
        return messageMapper.toMessageResponse(message);
    }

    /**
     * @param messageRequest message to save
     */
    @Override
    public void saveMessage(MessageRequest messageRequest) {
        Optional<Message> messageInDB = messageRepository.findByMessageTextAndVehicleAndClientAndReservation(messageRequest.getMessageText(), messageRequest.getVehicle(), messageRequest.getClient(), messageRequest.getReservation());
        if (messageInDB.isPresent()) {
            throw new MessageAlreadyExistsException();
        }
        messageRepository.save(messageMapper.toMessage(messageRequest));
    }

    /**
     * @param messageId id of message to delete
     */
    @Override
    public void deleteMessage(int messageId) {
        if (!messageRepository.existsById(messageId)) {
            throw new MessageNotFoundException();
        }
        messageRepository.deleteById(messageId);
    }

    /**
     * @param messageUpdate message to update
     */
    @Override
    public void updateMessage(MessageUpdate messageUpdate) {
        Message messageInDB = messageRepository.findById(messageUpdate.getIdMessage()).orElseThrow(MessageNotFoundException::new);
        if (messageUpdate.getMessageText() != null) {
            messageInDB.setMessageText(messageUpdate.getMessageText());
        }
        if (messageUpdate.getScore() >=0) {
            messageInDB.setScore(messageUpdate.getScore());
        }
        messageRepository.save(messageInDB);
    }
}
