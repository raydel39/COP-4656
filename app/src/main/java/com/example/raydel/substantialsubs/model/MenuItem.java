package com.example.raydel.substantialsubs.model;

import android.os.Parcel;
import android.os.Parcelable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MenuItem implements Parcelable{

    private String name;
    private double price;
    private int quantity;
    private String description;

    public MenuItem(Parcel in) {
        super();
    }

    public static final Parcelable.Creator<MenuItem> CREATOR = new Parcelable.Creator<MenuItem>() {
        public MenuItem createFromParcel(Parcel in) {
            return new MenuItem(in);
        }

        public MenuItem[] newArray(int size) {
            return new MenuItem[size];
        }

    };

    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeDouble(price);
        dest.writeInt(quantity);
        dest.writeString(description);
    }

}
