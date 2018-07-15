package com.example.raydel.substantialsubs.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MenuItem {

    private String name;
    private double price;
    private int quantity;
    private String description;

}
