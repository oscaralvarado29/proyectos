package com.vehicle.model;

import com.vehicle.pojo.CategoryPojo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Oscar Alvarado
 */
@Getter
@Setter
@AllArgsConstructor                             // Le dice a LOMBO que cree un constructor con todos los argumentos
@NoArgsConstructor                              // Le dice a LOMBO que cree un constructor sin argumentos
@Entity
@Table(name = "category")
public class Category implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCategory;
    @Column(length=45)
    private String name;
    @Column(length=250)
    private String description;

    public Category(CategoryPojo categoryPojo) {
        this.idCategory = categoryPojo.getIdCategory();
        this.name = categoryPojo.getName();
        this.description = categoryPojo.getDescription();
        this.vehicle = categoryPojo.getVehicle();

    }

    @Column()
    @OneToMany(cascade = {CascadeType.PERSIST},mappedBy="category")
    @JsonIgnoreProperties("category")
    private List<Vehicle> vehicle;
}
