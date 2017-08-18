package com.example.katz.myavtivity.src.entities;


import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Shor family on 16 ������ 2015.
 */


public class Book implements Serializable {

    private String name;
    private int id;
    private String author;
    private String genre;
    private int pageNumber;
    private String summary;
    protected int soldNumber;
    private double rating;
    private int rateNumber;
    private ArrayList<Critics> commends;


    public Book(String name, String author, String genre, int pageNumber, String summary) {
        this.name = name;
        this.author = author;
        this.genre = genre;
        this.pageNumber = pageNumber;
        this.summary = summary;
    }

    public Book() {// v
        super();
    }

    //getters and setters
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

    public String getAuthor() {
        return author;
    }

    public void setCommends(ArrayList<Critics> commends) {
        this.commends = commends;
    }

    public int getRateNumber() {

        return rateNumber;
    }

    public void setRateNumber(int rateNumber) {
        this.rateNumber = rateNumber;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getSoldNumber() {
        return soldNumber;
    }

    public void setSoldNumber(int soldNumber) {
        this.soldNumber = soldNumber;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public ArrayList<Critics> getCommends() {
        return commends;
    }
    /////////////////////////////////////////////////////////////////////

    public void rate(int x)
    {
        this.rating = ((this.rating*this.rateNumber) + x)/++this.rateNumber;
    }

    public void addCommend(Critics commend)
    {
        this.commends.add(commend);
    }


}//end class
