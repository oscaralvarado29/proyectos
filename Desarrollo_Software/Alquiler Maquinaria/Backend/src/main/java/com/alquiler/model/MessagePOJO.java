package com.alquiler.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class MessagePOJO {
    private Integer idMessage;
    private String messageText;
    private Machine machine;
    private Client client;
}
