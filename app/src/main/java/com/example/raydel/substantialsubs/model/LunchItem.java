package com.example.raydel.substantialsubs.model;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LunchItem extends MenuItem {

    private String meatType;
    private String breadType;
    private ArrayList<AddOn> cookedIn = new ArrayList<>();
    private ArrayList<AddOn> extras = new ArrayList<>();

    @Override
    public double calculatePrice() {
        double total = 0;

        total += getBasePrice();
        total += cookedIn.stream().mapToDouble(AddOn::getPrice).sum();
        total += extras.stream().mapToDouble(AddOn::getPrice).sum();

        return total;
    }
}
