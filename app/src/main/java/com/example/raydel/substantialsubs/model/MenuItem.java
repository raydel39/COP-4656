package com.example.raydel.substantialsubs.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public abstract class MenuItem {

    private String name;
    private double price;
    private int quantity;
    private boolean isAvailable;
    private String comments;
    private double basePrice;

    public abstract double calculatePrice ();

    public double getPrice(){
       return calculatePrice();
    }

}
