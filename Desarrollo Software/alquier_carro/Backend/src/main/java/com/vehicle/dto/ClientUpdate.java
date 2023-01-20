package com.vehicle.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientUpdate {
    private Integer idClient;
    private String email;
    private String name;
    private Integer age;
}
