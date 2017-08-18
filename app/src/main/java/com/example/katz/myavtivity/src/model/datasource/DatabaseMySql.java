package com.example.katz.myavtivity.src.model.datasource;

import android.os.AsyncTask;

import com.example.katz.myavtivity.src.entities.Book;
import com.example.katz.myavtivity.src.entities.BookProvider;
import com.example.katz.myavtivity.src.entities.Critics;
import com.example.katz.myavtivity.src.entities.Customer;
import com.example.katz.myavtivity.src.entities.Order;
import com.example.katz.myavtivity.src.entities.Provider;
import com.example.katz.myavtivity.src.model.backend.Backend;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Shor family on 18 ינואר 2016.
 */



public class DatabaseMySql implements Backend {
    public static final String web_url = "http://syonatan.vlab.jct.ac.il/";

    @Override
    public void addBook(Book book) throws Exception {

            Map<String,Object> params = new LinkedHashMap<>();
            params.put("Book_ID", book.getId());
            params.put("Book_Name", book.getName());
            params.put("Book_Author", book.getAuthor());
            params.put("Book_Ganre",book.getGenre());
            params.put("Book_Summery",book.getSummary());
            POST("http://syonatan.vlab.jct.ac.il" + "AddBook.php", params);
        }

        @Override
        public ArrayList<Book> getAllBooks() throws Exception {
            final ArrayList<Book> BookList = new ArrayList<Book>();
            try {
                new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected Void doInBackground(Void... voids) {
                        try {
                            Book tempProduct;
                            JSONArray products = new JSONObject(GET(web_url +
                                    "GetBook.php")).getJSONArray("products");
                            for (int i = 0; i < products.length(); i++) {
                                tempProduct = new Book();
                                tempProduct.setId(products.getJSONObject(i).getInt("Book_ID"));
                                tempProduct.setName(products.getJSONObject(i).getString("Book_Name"));
                                tempProduct.setAuthor(products.getJSONObject(i).getString("Book_Author"));
                                tempProduct.setGenre(products.getJSONObject(i).getString("Book_Ganre"));
                                tempProduct.setSummary(products.getJSONObject(i).getString("Book_Summery"));
                                BookList.add(tempProduct);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return null;
                    }
                    @Override
                    protected void onPreExecute() {
                    }
                    @Override
                    protected void onPostExecute(Void aVoid) {
                    }
                }.execute();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            return BookList;
        }



    @Override
    public void addCritic(Critics critic) throws Exception{
        Map<String,Object> params = new LinkedHashMap<>();
        params.put("Critic_ID", critic.getId());
        params.put("Critic_Name", critic.getName());
        params.put("Critic_Password", critic.getPassword());
        params.put("Critic_Rating", critic.getBrating());
        params.put("Critic_Adress", critic.getAddress());
        params.put("Critic_Phone", critic.getTelephone());
        params.put("Critic_Commnetry", critic.getCommentary());
        POST("http://syonatan.vlab.jct.ac.il" + "AddCritic.php", params);
    }

    @Override
    public Book findBookById(int id) throws Exception {
        return null;
    }

    @Override
    public void updateBook(Book book) throws Exception {

    }

    @Override
    public void deleteBook(int id) throws Exception {

    }


    @Override
    public void addProvider(Provider provider) throws Exception {
        Map<String,Object> params = new LinkedHashMap<>();
        params.put("Provider_ID", provider.getId());
        params.put("Provider_Name", provider.getName());
        params.put("Provider_Password", provider.getPassword());
        params.put("Provider_Working_Area", provider.getLivingArea());
        params.put("Provider_Adress", provider.getAddress());
        params.put("Provider_Phone", provider.getTelephone());
        POST(web_url + "AddProvider.php", params);
    }

    @Override
    public Provider findProviderById(int id) throws Exception {
        return null;
    }

    @Override
    public void updateCritic(Critics critics) throws Exception {

    }

    @Override
    public void deleteProvider(int id) throws Exception {

    }

    @Override
    public ArrayList<Provider> getAllProviders() throws Exception {
        return null;
    }

    @Override
    public void makeOrder(Order order) throws Exception {

    }

    @Override
    public ArrayList<Order> getAllOrders() throws Exception {
        return null;
    }

    @Override
    public void addBookProvider(BookProvider bookProvider) throws Exception {

    }

    @Override
    public BookProvider findBpById(int bookID, int providerID) throws Exception {
        return null;
    }

    @Override
    public void deleteBookProvider(int bookID, int providerID) throws Exception {

    }

    @Override
    public ArrayList<BookProvider> getAllBookProviders() throws Exception {
        return null;
    }

    @Override
    public void addCustomer(Customer customer) throws Exception {
        Map<String,Object> params = new LinkedHashMap<>();
        params.put("Customer_ID", customer.getId());
        params.put("Customer_Name", customer.getName());
        params.put("Customer_Password", customer.getPassword());
        //params.put("Provider_Living_Area", customer.);
        //params.put("Provider_Adress", customer.getAddress());
        //params.put("Provider_Phone", customer.getTelephone());
        POST(web_url + "AddProvider.php", params);

    }

    @Override
    public Customer findCustomerById(int id) throws Exception {
        return null;
    }

    @Override
    public void deleteCustomer(int id) throws Exception {

    }

    @Override
    public ArrayList<Customer> getAllCestomers() throws Exception {
        return null;
    }

    @Override
    public ArrayList<Critics> getAllCritics() {
        return null;
    }

    @Override
    public void deleteCritic(int id) throws Exception {

    }

    @Override
    public Critics findCriticById(int id , ArrayList<Critics> criticses) throws Exception {
        return null;
    }

    @Override
    public ArrayList<Book> getBestSellersBooks() throws Exception {
        return null;
    }

    @Override
    public ArrayList<Critics> getCommendsOfBook(Book book) throws Exception {
        return null;
    }

    @Override
    public void setList() {

    }

    @Override
    public Book getBook(String bookName) {
        return null;
    }

    @Override
    public BookProvider getBookp(String bookName) {
        return null;
    }

    @Override
    public void UpdateBp(BookProvider bookProvider) throws Exception {

    }

    @Override
    public BookProvider getbookprice(Double BookPrice) {
        return null;
    }

    @Override
    public Provider getProvider(String providerName) {
        return null;
    }

    @Override
    public Critics getCritic(String criticName) {
        return null;
    }

    @Override
    public int[] getFlagId() {
        return new int[0];
    }

    @Override
    public boolean checkPasswordProvider(int pas, int id) throws Exception {
        return false;
    }

    @Override
    public boolean checkPasswordCustomer(int pas, int id) throws Exception {
        return false;
    }

    @Override
    public boolean checkPasswordCritics(int pas, int id) throws Exception {
        return false;
    }

    //Server Connection
    private static String GET(String url) throws Exception {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
    con.setRequestMethod("GET");
    if (con.getResponseCode() == HttpURLConnection.HTTP_OK) { // success
           BufferedReader in = new BufferedReader(new InputStreamReader(
                   con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
            in.close();
        // print result
        return response.toString();
} else {
        return "";
        }
    }

    private static String POST(String url, Map<String,Object> params) throws
            IOException {

        //Convert Map<String,Object> into key=value&key=value pairs.
        StringBuilder postData = new StringBuilder();
        for (Map.Entry<String,Object> param : params.entrySet()) {
            if (postData.length() != 0)
                postData.append('&');
            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
            postData.append('=');
        postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
        }

    URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
    // For POST only - START
       con.setDoOutput(true);
        OutputStream os = con.getOutputStream();
        os.write(postData.toString().getBytes("UTF-8"));
        os.flush();
        os.close();
        // For POST only - END

    int responseCode = con.getResponseCode();
        System.out.println("POST Response Code :: " + responseCode);

    if (responseCode == HttpURLConnection.HTTP_OK) { //success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }
    else return ""; }

}
