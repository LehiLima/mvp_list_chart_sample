package com.example.lehiteixeira.banco_neon.Data.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by Lehi on 04/09/2017.
 */

public class Person implements Serializable {

    private String id;

    private String name;

    private String phone;

    private Double value;


    private String initials;

    private int userimage;

    public Person(String id, String name, String phone, Double value, int userimage) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.value = value;
        this.userimage = userimage;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }


    public int getUserimage() {
        return userimage;
    }

    public void setUserimage(int userimage) {
        this.userimage = userimage;
    }

    public String getInitials(String name) {

        String[] parts = name.split(" ");
        int lastnameindex = parts.length - 1;
        String firstName = parts[0]; // Lehi
        String lastName = parts[lastnameindex]; // Teixeira
        // get first letter of each
        initials = firstName.substring(0,1) + lastName.substring(0,1); // LT

        return initials;

    }

}
