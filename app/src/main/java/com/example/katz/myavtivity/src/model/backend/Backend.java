package com.example.katz.myavtivity.src.model.backend;

import com.example.katz.myavtivity.src.entities.Book;
import com.example.katz.myavtivity.src.entities.BookProvider;
import com.example.katz.myavtivity.src.entities.Critics;
import com.example.katz.myavtivity.src.entities.Customer;
import com.example.katz.myavtivity.src.entities.Order;
import com.example.katz.myavtivity.src.entities.Provider;

import java.util.ArrayList;

/**
 * Created by katz on 24/11/2015.
 */
public interface Backend {

    //book func
    public void addBook(Book book)throws Exception;
    public Book findBookById(int id)throws Exception;
    public void updateBook(Book book)throws  Exception;
    public void deleteBook(int id)throws  Exception;
    public ArrayList<Book> getAllBooks()throws Exception;
    public Book getBook(String bookName);
    public Critics getCritic(String criticName);

    //provider func
    public void addProvider(Provider provider)throws Exception;
    public Provider findProviderById(int id)throws Exception;
    public void deleteProvider(int id)throws Exception;
    public ArrayList<Provider> getAllProviders()throws Exception;
    public boolean checkPasswordProvider(int pas,int id)throws Exception;
    public Provider getProvider(String providerName);

    //order func
    public void makeOrder(Order order)throws Exception;
    public ArrayList<Order> getAllOrders()throws Exception;

    // BookProvider
    public void addBookProvider(BookProvider bookProvider)throws Exception;
    public BookProvider findBpById(int bookID, int providerID)throws  Exception;
    public void deleteBookProvider(int bookID, int providerID)throws Exception;
    public ArrayList<BookProvider> getAllBookProviders()throws Exception;
    public BookProvider getBookp(String bookName);
    public void UpdateBp(BookProvider bookProvider)throws Exception;
    public BookProvider getbookprice(Double BookPrice);

    //Customer func
    public void addCustomer(Customer customer)throws Exception;
    public Customer findCustomerById(int id)throws Exception;
    public void deleteCustomer(int id)throws Exception;
    public ArrayList<Customer> getAllCestomers()throws Exception;
    public boolean checkPasswordCustomer(int pas , int id)throws Exception;

    //critic func
    public void addCritic(Critics critic)throws Exception;
    public ArrayList<Critics> getAllCritics();
    public void updateCritic(Critics critics)throws  Exception;
    public void deleteCritic(int id)throws Exception;
    public Critics findCriticById(int id, ArrayList<Critics> criticses)throws Exception;
    public boolean checkPasswordCritics(int pas,int id)throws Exception;

    //other func
    public ArrayList<Book> getBestSellersBooks()throws Exception;
    public ArrayList<Critics> getCommendsOfBook(Book book)throws Exception;
    public void setList();
    public int[] getFlagId();


}
