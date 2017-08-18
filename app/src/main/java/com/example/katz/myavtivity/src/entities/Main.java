package com.example.katz.myavtivity.src.entities;

import com.example.katz.myavtivity.src.model.datasource.DatabaseList;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws Exception {
	// write your code here
        DatabaseList d = new DatabaseList();
        Book b = new Book("Harry Poter","j.k Rolling","Fantasy",564,"Only Kids");
        Provider p =new Provider("Kol Hasefer","Jerusalem",659432);
        //Customer c = new Customer("Yosef",9865,"Mfsdfg@dfrf",12345,"Fantasy",123);
        Critics cr = new Critics();
        BookProvider bp1 = new BookProvider(b,p,12,32.0);
        BookProvider bp2 = new BookProvider(b,123.0);
        Cart ca = new Cart(bp1,13);
        Cart ca1 = new Cart(bp2,14);
        ArrayList<Cart> cartL = new ArrayList<Cart>();
        cartL.add(ca);
        //Order o = new Order(cartL,c);
        d.addBook(b);
        d.addProvider(p);
        //d.addCustomer(c);
        d.addBookProvider(bp1);
       // d.makeOrder(o);


    }
}
