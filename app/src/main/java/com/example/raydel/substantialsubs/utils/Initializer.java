package com.example.raydel.substantialsubs.utils;


//This class will be used to initialize all menu items. Eventually this will replace by a database

import com.example.raydel.substantialsubs.model.MenuItem;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Initializer {

    private List<MenuItem> lunchItems = new ArrayList<>();
    private List<MenuItem> breakfastItems = new ArrayList<>();
    private List<MenuItem> sidesItems = new ArrayList<>();
    private List<MenuItem> drinksItems = new ArrayList<>();
    private List<MenuItem> dessertItems = new ArrayList<>();


    public Initializer(){

        //Lunch
        lunchItems.add(new MenuItem("Bomb Sub", 9.99, 0, "Steak or Chicken with grilled with onions, peppers, mushrooms, bacon and provolone cheese"));
        lunchItems.add(new MenuItem("Cordon Blu Sub", 9.99, 0, "Steak or Chicken cooked with ham and honey mustard sauce"));
        lunchItems.add(new MenuItem("Hawaiian Sub", 9.99, 0, "Steak or Chicken cooked with ham, pineapples and Teriyaki sauce"));
        lunchItems.add(new MenuItem("Burger Bomb", 9.99, 0, "Double Burger with grilled with onions, peppers, mushrooms, bacon and provolone cheese"));
        lunchItems.add(new MenuItem("Burger Bacon", 6.99, 0, "Single Burger with bacon and provolone cheese"));
        lunchItems.add(new MenuItem("Chicken Wings", 8.99, 0, "10 chicken wings with choice of sauce"));
        lunchItems.add(new MenuItem("Chef Salad", 7.99, 0, "Ham, turkey and provolone cheese on top of a custom made salad"));

        //Breakfast
        breakfastItems.add(new MenuItem("All American Breakfast", 7.99, 0, "Two eggs, hashbrowns or grits and choice of ham, turkey, bacon or sausages. Served with toast"));
        breakfastItems.add(new MenuItem("Big Breakfast", 7.99, 0, "Two large pancakes, two eggs and choice of ham, turkey, bacon or sausages. Served with toast"));
        breakfastItems.add(new MenuItem("Fish and grits", 6.99, 0, "Classic fish and grits served with two eggs and toast"));
        breakfastItems.add(new MenuItem("Breakfast Sandwich", 8.99, 0, "Large eggs and cheese sandwich with choice of ham, turkey, bacon or sausages"));

        //Sides
        sidesItems.add(new MenuItem("French Fries", 1.79, 0, "A small order of french fries with seasoned salt"));
        sidesItems.add(new MenuItem("Potatoes Chips", 1.29, 0, "A bag of potatoes chips"));
        sidesItems.add(new MenuItem("Onion Rings", 3.99, 0, "A small order of onion rings"));
        sidesItems.add(new MenuItem("Mozzarella Sticks", 4.99, 0, "Five mozzarella sticks served with home made marinara sauce"));

        //Drinks
        drinksItems.add(new MenuItem("Fountain Drinks", 1.79, 0, "20 Oz Fountain Drink"));
        drinksItems.add(new MenuItem("Bottled Drinks", 1.79, 0, "Any bottled drink"));
        drinksItems.add(new MenuItem("Can Drinks", 1.29, 0, "Any can soda"));
        drinksItems.add(new MenuItem("Cafe con Leche", 1.99, 0, "Milk with coffee"));

        //Dessert
        drinksItems.add(new MenuItem("Chocolate Cake", 2.99, 0, "A slice of Six-Layer Chocolate Cake"));
        drinksItems.add(new MenuItem("Carrot Cake", 2.99, 0, "A slice of Carrot Cake"));
        drinksItems.add(new MenuItem("Chocolate Chips Cookies", 1.99, 0, "A small bag of homemade chocolate chips cookies"));
    }

}
