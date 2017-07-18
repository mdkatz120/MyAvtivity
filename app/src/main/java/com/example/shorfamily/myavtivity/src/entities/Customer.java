package com.example.shorfamily.myavtivity.src.entities;


import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Shor family on 16 ������ 2015.
 */

public class Customer implements Serializable
{
    private String name;
    private int id;
    private String mail;
    private int password;
    private String preferredGenre;
    private int creditCard;
    private String workArea;
    private String Address;
    private int PhoneNumber;
    protected int  purchaseNumber ; //number of orders
    private int serialNumber;
    ArrayList<Cart> cartList;

    public Customer(String name, int id, String mail, int password, String preferredGenre,int Credit) {
        this.name = name;
        this.id = id;
        this.mail = mail;
        this.password = password;
        this.preferredGenre = preferredGenre;
        this.purchaseNumber = 0;
        this.creditCard = Credit;
        cartList = new ArrayList<Cart>();
    }

    public Customer() {
    }

    //getters and setters


    public int getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getWorkArea() {
        return workArea;
    }

    public void setWorkArea(String workArea) {
        this.workArea = workArea;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public int getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(int creditCard) {
        this.creditCard = creditCard;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public String getPreferredGenre() {
        return preferredGenre;
    }

    public void setPreferredGenre(String preferredGenre) {
        this.preferredGenre = preferredGenre;
    }

    public int getPurchaseNumber() {
        return purchaseNumber;
    }

    public void setPurchaseNumber(int purchaseNumber) {
        this.purchaseNumber = purchaseNumber;
    }

    public int getSerialNumber(){return serialNumber;}

    public void setSerialNumber(int serial){this.serialNumber = serial;}
    //////////////////////////////////////////////////////////////


}//end class

