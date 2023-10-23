package com.medicine.register.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientRequest {
    @NotEmpty(message = "Debe ingresar su cedula")
    private String id;
    @NotEmpty(message = "Debe ingresar un nombre")
    private String firstName;
    private String secondName;
    @NotEmpty(message = "Debe ingresar su primer apellido")
    private String firstSurName;
    @NotEmpty(message = "Debe ingresar su segundo apellido")
    private String secondSurName;
    @NotEmpty(message = "La direccion no puede estar vacío")
    private String address;
    @Email(message = "Por favor, ingrese una dirección de correo electrónico válida")
    @NotEmpty(message = "El correo electrónico no puede estar vacío")
    private String email;
    private String landline;
    @NotEmpty(message = "El celular no puede estar vacío")
    private String cellPhone;
    @NotEmpty(message = "El tipo de residencia no puede estar vacío")
    private String residencesType;
    private String descriptionResidence;
    @NotEmpty(message = "El barrio no puede estar vacío")
    private String neighborhood;
    @NotEmpty(message = "La contraseña no puede estar vacío")
    private String password;
}
