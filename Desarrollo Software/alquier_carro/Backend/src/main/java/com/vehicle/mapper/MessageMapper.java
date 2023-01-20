package com.vehicle.mapper;

import com.vehicle.dto.MessageRequest;
import com.vehicle.dto.MessageResponse;
import com.vehicle.model.Message;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface MessageMapper {
    Message toMessage(MessageRequest messageRequest);
    default MessageResponse toMessageResponse(Message message){
        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setMessageText(message.getMessageText());
        messageResponse.setScore(message.getScore());
        messageResponse.setVehicle(message.getVehicle());
        messageResponse.setClient(message.getClient());
        messageResponse.setReservation(message.getReservation());
        return messageResponse;
    }
    default List<MessageResponse> toMessageResponseList(List<Message> messages){
        List<MessageResponse> messageResponseList = new ArrayList<>(messages.size());
        for (Message message : messages) {
            messageResponseList.add(toMessageResponse(message));
        }
        return messageResponseList;
    }
}
