package com.reto5.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class MessageDTO {
    private Integer idMessage;
    private String messageText;
    private Machine machine;
    private Client client;
}
