package com.carro.model;

import com.carro.pojo.CarPojo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 *
 * @author Oscar Alvarado
 */
@SuppressWarnings("JpaDataSourceORMInspection")
@Getter
@Setter
@AllArgsConstructor                             // Le dice a LOMBO que cree un constructor con todos los argumentos
@NoArgsConstructor                              // Le dice a LOMBO que cree un constructor sin argumentos
@Entity
@Table(name = "car")
public class Car implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCar;
    @Column(length = 45)
    private String color;
    @Column(length = 45)
    private String branch;
    @Column(length = 45)
    private String model;
    @Column(length = 4)
    private Integer horsepower;
    @Column(length = 3)
    private Integer engineCylinders;
    @Column(length = 3)
    private Integer seating;

    public Car(CarPojo carPojo) {
        this.idCar = carPojo.getIdCar();
        this.color = carPojo.getColor();
        this.branch = carPojo.getBranch();
        this.model = carPojo.getModel();
        this.horsepower = carPojo.getHorsepower();
        this.engineCylinders = carPojo.getEngineCylinders();
        this.seating = carPojo.getSeating();
        this.category = carPojo.getCategory();
    }

    @ManyToOne
    @JoinColumn(name = "idCategory")
    @JsonIgnoreProperties("car")
    private Category category;
}
