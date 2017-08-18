package com.example.katz.myavtivity.src.entities;


/**
 * Created by Shor family on 16 ������ 2015.
 */
public class Cart
{
    private   BookProvider bookProvider;
    private   int amount;

    public Cart(BookProvider bookProvider, int amount) {
        this.bookProvider = bookProvider;
        this.amount = amount;
    }

    public Cart() {
    }

    public BookProvider getBookProvider() {
        return bookProvider;
    }

    public void setBookProvider(BookProvider bookProvider) {
        this.bookProvider = bookProvider;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
