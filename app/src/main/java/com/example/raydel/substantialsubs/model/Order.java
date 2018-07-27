package com.example.raydel.substantialsubs.model;

import java.text.DecimalFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static com.example.raydel.substantialsubs.constants.SubstantialConstants.HTML_CENTER;
import static com.example.raydel.substantialsubs.utils.Utils.handleSelectedItems;

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

    public String toString(){
        StringBuilder orderHtml = new StringBuilder(
                "<h2>" + HTML_CENTER + "--- Order Information ---</h2>" +
                "<h2>Name: " + name + "</h2>" +
                "<h2>Phone: " + phone + "</h2>");

        if(isDelivery){
            orderHtml.append(
                "<h2>Order Type: Delivery</h2>" +
                "<h2>Address: " + address + "</h2>");

            if(isCard){
                orderHtml.append(
                "<h2>Payment Method: Credit Card</h2>" +
                "<h2>Card Number: " + ccNumber + "</h2>" +
                "<h2>Card Expiration: " + ccExpMM + "/" + ccExpYYYY + "</h2>" +
                "<h2>Card CVV: " + ccCVV + "</h2>"
                );
            }else{
                orderHtml.append(
                "<h2>Payment Method: Cash</h2>");
            }
        } else{
            orderHtml.append(
                "<h2>Order Type: Pickup</h2>");
        }

        orderHtml.append("<h2>" + HTML_CENTER + "--- Selected Items ---</h2>");

        DecimalFormat f = new DecimalFormat("#0.00");
        double price = handleSelectedItems(orderHtml);

        orderHtml.append("<h2>" + HTML_CENTER + "--- Charges and Fees ---</h2>");

        if(isDelivery) {
            orderHtml.append("<h2>Delivery Fee:\t\t\t\t\t\t\t\t\t$3.00</h2>");
            price += 3;
        }

        double taxes = price * 0.07;

        orderHtml.append(
                "<h2>Taxes:\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t$" + f.format(taxes) + "</h2>" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t----------------" +
                "<b><h1>\nOrder Total:\t\t\t\t\t\t\t\t\t$" + f.format(price + taxes) + "</h1></b>"
        );

        return orderHtml.toString();
    }
}
