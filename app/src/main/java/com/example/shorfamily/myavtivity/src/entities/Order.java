package com.example.shorfamily.myavtivity.src.entities;


import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Shor family on 16 ������ 2015.
 */

public class Order implements Serializable
{
    private int orderNumber;
    private Customer customer;
    private ArrayList<Cart> books;
    private Double price;



    public Order(ArrayList<Cart> books, Customer customer)//constructor
    {
        this.books = books;
        this.customer = customer;
    }

    public Order() {
    }//default constructor
    public void setPrice(Double price) {
        this.price = price;
    }

    public void setBooks(ArrayList<Cart> books) {
        this.books = books;
    }

    public void setCustomer(Customer customer) {
    this.customer = customer;
}
    public void addToCart(Cart cart)
    {
        this.books.add(cart);
    }

    public void deleteFromCart(Cart cart)
    {
        this.books.remove(cart);
    }

    //getters
    public int getOrderNumber() {
        return orderNumber;
    }

    public Double getPrice() {
        return price;
    }

    public ArrayList<Cart> getBooks() {
        return books;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    /////////////////////////////////////////////
    private Double calculate(ArrayList<Cart> books)
    {
        Double sum = 0.0;
        for (Cart c : books)
        {
            sum += c.getAmount()*c.getBookProvider().getPrice();
        }
        return sum;
    }
}

