package com.example.shorfamily.myavtivity.src.entities;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Shor family on 16 ������ 2015.
 */
public class Critics implements Serializable
{
    private String name;
    private int id;
    private int password;
    private Double Brating;
    private String address;
    private int telephone;
    private String Commentary;
    private int Srating;
    private String E_mail;

    public void addCommendsToBook(Book book ,Critics Comment){ // maybe change it to addCommendstobook by critic
        book.getCommends().add(Comment);
        book.setRating(Brating);
    }

    // Getters and Setters


    public String getE_mail() {
        return E_mail;
    }

    public void setE_mail(String e_mail) {
        E_mail = e_mail;
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

    public Double getBrating() {
        return Brating;
    }

    public void setBrating(Double brating) {
        Brating = brating;
    }

    public int getSrating() {
        return Srating;
    }

    public void setSrating(int srating) {
        Srating = srating;
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

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public String getCommentary() {
        return Commentary;
    }

    public void setCommentary(String commentary) {
        Commentary = commentary;
    }
}
