package com.example.raydel.substantialsubs.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by raydelmesa on 5/7/18.
 */

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private boolean isDelivery;
    private String name;
    private String phone;
    private String address;
    private boolean isCard;
    private String ccNumber;
    private String ccExpMM;
    private String ccExpYYYY;
    private String ccCVV;
    private double distanceToAddress;

}
