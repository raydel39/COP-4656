package com.example.raydel.substantialsubs.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DessertItem extends MenuItem {

    @Override
    public double calculatePrice() {
        return 0;
    }
}