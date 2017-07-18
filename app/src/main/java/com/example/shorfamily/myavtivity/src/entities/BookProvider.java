package com.example.shorfamily.myavtivity.src.entities;


import java.io.Serializable;

/**
 * Created by Shor family on 16 ������ 2015.
 */


public class BookProvider implements Serializable{
    private Book book;
    private Provider provider;
    protected int amount;
    private Double price;
    int soldNumber;

    public BookProvider(Book book, Provider provider, int amount, Double price) { // V
        this.book = book;
        this.provider = provider;
        this.amount = amount;
        this.price = price;
        this.soldNumber = 0;
    }
    // Ctor for the custumer
    public BookProvider(Book book, Double price) {
        this.book = book;
        this.price = price;
    }

    public BookProvider() {
    }

    //getters and setters

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getSoldNumber() {
        return soldNumber;
    }

    public void setSoldNumber(int soldNumber) {
        this.soldNumber = soldNumber;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
    ///////////////////////////////////////////////////////

}//end class

