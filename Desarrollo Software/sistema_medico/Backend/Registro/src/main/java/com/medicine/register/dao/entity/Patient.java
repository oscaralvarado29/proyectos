package com.medicine.register.dao.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamoDBTable(tableName = "pacientes")
public class Patient {

    @DynamoDBHashKey
    @NotEmpty(message = "Debe ingresar su cedula")
    private String id;

    @DynamoDBAttribute
    @NotEmpty(message = "Debe ingresar un nombre")
    private String firstName;
    @DynamoDBAttribute
    private String secondName;
    @DynamoDBAttribute
    @NotEmpty(message = "Debe ingresar su primer apellido")
    private String firstSurName;
    @DynamoDBAttribute
    @NotEmpty(message = "Debe ingresar su segundo apellido")
    private String secondSurName;
    @DynamoDBAttribute
    @NotEmpty(message = "La direccion no puede estar vacío")
    private String address;
    @DynamoDBAttribute
    @Email(message = "Por favor, ingrese una dirección de correo electrónico válida")
    @NotEmpty(message = "El correo electrónico no puede estar vacío")
    private String email;
    @DynamoDBAttribute
    private String landline;
    @DynamoDBAttribute
    @NotEmpty(message = "El celular no puede estar vacío")
    private String cellPhone;
    @DynamoDBAttribute
    @NotEmpty(message = "El tipo de residencia no puede estar vacío")
    private String residencesType;
    @DynamoDBAttribute
    private String descriptionResidence;
    @DynamoDBAttribute
    @NotEmpty(message = "El barrio no puede estar vacío")
    private String neighborhood;
    @DynamoDBAttribute
    @NotEmpty(message = "La contraseña no puede estar vacío")
    private String password;

    public Patient(Patient patient, String password){
        this.firstName = patient.getFirstName();
        this.secondName = patient.getSecondName();
        this.firstSurName = patient.getFirstSurName();
        this.secondSurName = patient.getSecondSurName();
    }
}
