package com.example.shorfamily.myavtivity.src.entities;

import java.io.Serializable;

/**
 * Created by Shor family on 16 ������ 2015.
 */

public class Provider implements Serializable {
    private String name;
    private int id;
    private int password;
    private String livingArea;
    private String address;
    private int telephone;
    private double rating;
    private int rateNumber;
    private String E_mail;

    public Provider(String name,  String address, int telephone) {
        this.name = name;
        this.address = address;
        this.telephone = telephone;
    }

    public Provider(){}

    //Getters and Setters


    public String getE_mail() {
        return E_mail;
    }

    public void setE_mail(String e_mail) {
        E_mail = e_mail;
    }

    public String getLivingArea() {
        return livingArea;
    }

    public void setLivingArea(String livingArea) {
        this.livingArea = livingArea;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getRateNumber() {
        return rateNumber;
    }

    public void setRateNumber(int rateNumber) {
        this.rateNumber = rateNumber;
    }

    public int getPassword() {

        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }
    ///////////////////////////////////////////////////


    public void rate(int x)
    {
        this.rating = ((this.rating*this.rateNumber) + x)/++this.rateNumber;
    }

}//end class

