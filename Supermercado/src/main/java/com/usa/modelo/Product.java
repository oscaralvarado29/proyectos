/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.usa.modelo;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 *
 * @author Oscar Alvarado
 */
@Table("productos")
public class Product {
    @Id
    @Column ("codigo")
    private int code;
    @Column ("nombre")
    private String name;
    @Column ("precio")
    private double price;
    @Column ("inventario")
    private int inventory;
      
    public Product(){
          
      }
    
    public Product(String name, double price, int inventory) {
        this.name = name;
        this.price = price;
        this.inventory = inventory;
    }
      
    public Product(int code, String name, double price, int inventory) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.inventory = inventory;
    }
    
    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getInventory() {
        return inventory;
    }

}
