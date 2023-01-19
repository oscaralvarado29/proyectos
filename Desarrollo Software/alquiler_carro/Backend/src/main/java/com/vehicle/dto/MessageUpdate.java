package com.vehicle.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageUpdate {
    private Integer idMessage;
    private String messageText;
    private double score;
}
