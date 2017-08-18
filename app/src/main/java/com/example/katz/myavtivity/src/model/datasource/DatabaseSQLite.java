package com.example.katz.myavtivity.src.model.datasource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.katz.myavtivity.src.model.backend.Backend;
import com.example.katz.myavtivity.src.entities.Book;
import com.example.katz.myavtivity.src.entities.BookProvider;
import com.example.katz.myavtivity.src.entities.Critics;
import com.example.katz.myavtivity.src.entities.Customer;
import com.example.katz.myavtivity.src.entities.Order;
import com.example.katz.myavtivity.src.entities.Provider;

import java.util.ArrayList;

/**
 * Created by Shor family on 15 פברואר 2016.
 */
public class DatabaseSQLite extends SQLiteOpenHelper implements Backend {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "libraryBooks.db";
    private static final String TABLE_BOOKS = "books";

    public static final String COLUMN_ID_BOOK = "_id";
    public static final String COLUMN_BOOKNAME = "name";
    public static final String COLUMN_AUTHOR = "author";
    public static final String COLUMN_GENRE = "genre";
    public static final String COLUMN_PAGENUMBER = "pageNumber";
    public static final String COLUMN_SOLDNUMBER = "soldNumber";
    public static final String COLUMN_COMMENTS ="comments";

    private static final String TABLE_PROVIDERS = "providers";

    public static final String COLUMN_ID_PROVIDER = "_id";
    public static final String COLUMN_PROVIDERNAME = "name";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_LIVING_AREA = "livingArea";
    public static final String COLUMN_ADDRESS_PROVIDER = "address";
    public static final String COLUMN_TELEPHONE_PROVIDER = "telephone";
    public static final String COLUMN_EMAIL_PROVIDER ="E_mail";

    // ctor sql
    public DatabaseSQLite(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public DatabaseSQLite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DatabaseSQLite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    //Overrides
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_BOOKS_TABLE = "CREATE TABLE "
                + TABLE_BOOKS
                + "("
                + COLUMN_ID_BOOK + " INTEGER PRIMARY KEY,"
                + COLUMN_AUTHOR + " TEXT,"
                + COLUMN_BOOKNAME + " TEXT,"
                + COLUMN_GENRE + " TEXT,"
                + COLUMN_PAGENUMBER + " INTEGER,"
                + COLUMN_SOLDNUMBER + " INTEGER,"
                + COLUMN_COMMENTS + " TEXT"+ ")";
        db.execSQL(CREATE_BOOKS_TABLE);
        String CREATE_PROVIDERS_TABLE = "CREATE TABLE" +
                TABLE_PROVIDERS + "("
                + COLUMN_ID_PROVIDER +"INTEGER PRIMARY KEY,"
                + COLUMN_PROVIDERNAME + " TEXT,"
                + COLUMN_PASSWORD + " INTEGER,"
                + COLUMN_LIVING_AREA + " TEXT,"
                + COLUMN_ADDRESS_PROVIDER + " TEXT,"
                + COLUMN_TELEPHONE_PROVIDER + " INTEGER,"
                + COLUMN_EMAIL_PROVIDER + " TEXT" + ")";
        db.execSQL(CREATE_PROVIDERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOKS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROVIDERS);
        onCreate(db);

    }

    @Override
    public void addBook(Book book) throws Exception {
        ContentValues values = new ContentValues();
        values.put(COLUMN_BOOKNAME, book.getName());
        values.put(COLUMN_AUTHOR, book.getAuthor());
        values.put(COLUMN_GENRE, book.getGenre());
        values.put(COLUMN_ID_BOOK, book.getId());
        values.put(COLUMN_PAGENUMBER, book.getPageNumber());
        values.put(COLUMN_SOLDNUMBER, book.getSoldNumber());
        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_BOOKS, null, values);
        db.close();

    }

    @Override
    public Book findBookById(int id) throws Exception {
        String query = "Select * FROM " + TABLE_BOOKS + " WHERE " + COLUMN_ID_BOOK + " =  \"" + id + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        Book book = new Book();

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            book.setId(Integer.parseInt(cursor.getString(0)));
            book.setName(cursor.getString(1));
            book.setAuthor((cursor.getString(2)));
            book.setGenre((cursor.getString(3)));
            book.setPageNumber(Integer.parseInt(cursor.getString(4)));
            book.setSoldNumber(Integer.parseInt(cursor.getString(5)));
            //    book.setCommends((ArrayList<Critics>)(cursor.getString(6)));
            cursor.close();
        } else {
            book = null;
        }
        db.close();
        return book;
    }

    @Override
    public void updateBook(Book book) throws Exception {
        deleteBook(book.getId());
        addBook(book);
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOKS);
        onCreate(db);
    }

    @Override
    public void deleteBook(int id) throws Exception {


        String query = "Select * FROM " + TABLE_BOOKS + " WHERE " + COLUMN_ID_BOOK + " =  \"" + id + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        Book book = new Book();

        if (cursor.moveToFirst()) {
            book.setId(Integer.parseInt(cursor.getString(0)));
            db.delete(TABLE_BOOKS, COLUMN_ID_BOOK + " = ?",
                    new String[] { String.valueOf(book.getId()) });
            cursor.close();

        }
        db.close();


    }

    @Override
    public ArrayList<Book> getAllBooks() throws Exception {
        return null;
    }

    @Override
    public Book getBook(String bookName) {
        String query = "Select * FROM " + TABLE_BOOKS + " WHERE " + COLUMN_BOOKNAME + " =  \"" + bookName + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        Book book = new Book();

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            book.setId(Integer.parseInt(cursor.getString(0)));
            book.setName(cursor.getString(1));
            book.setAuthor((cursor.getString(2)));
            book.setGenre((cursor.getString(3)));
            book.setPageNumber(Integer.parseInt(cursor.getString(4)));
            book.setSoldNumber(Integer.parseInt(cursor.getString(5)));
            //    book.setCommends((ArrayList<Critics>)(cursor.getString(6)));
            cursor.close();
        } else {
            book = null;
        }
        db.close();
        return book;
    }

    @Override
    public Critics getCritic(String criticName) {
        return null;
    }

    @Override
    public void addProvider(Provider provider) throws Exception {
        ContentValues values = new ContentValues();
        values.put(COLUMN_PROVIDERNAME, provider.getName());
        values.put(COLUMN_ADDRESS_PROVIDER, provider.getAddress());
        values.put(COLUMN_EMAIL_PROVIDER, provider.getE_mail());
        values.put(COLUMN_LIVING_AREA, provider.getLivingArea());
        values.put(COLUMN_PASSWORD, provider.getPassword());
        values.put(COLUMN_TELEPHONE_PROVIDER, provider.getTelephone());
        values.put(COLUMN_ID_PROVIDER,provider.getId());
        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_PROVIDERS, null, values);
        db.close();
    }

    @Override
    public Provider findProviderById(int id) throws Exception {
        return null;
    }

    @Override
    public void deleteProvider(int id) throws Exception {

    }

    @Override
    public ArrayList<Provider> getAllProviders() throws Exception {
        return null;
    }

    @Override
    public boolean checkPasswordProvider(int pas, int id) throws Exception {
        return false;
    }

    @Override
    public Provider getProvider(String ProviderName){
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
    public BookProvider getBookp(String bookName) {
        return null;
    }

    @Override
    public void UpdateBp(BookProvider bookProvider) throws Exception {

    }

    @Override
    public void addCustomer(Customer customer) throws Exception {

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
    public boolean checkPasswordCustomer(int pas, int id) throws Exception {
        return false;
    }

    @Override
    public void addCritic(Critics critics) throws Exception {

    }

    @Override
    public Critics findCriticById(int id, ArrayList<Critics> criticses) throws Exception {
        return null;
    }

    @Override
    public void deleteCritic(int id) throws Exception {

    }

    @Override
    public void updateCritic(Critics critics) throws Exception {

    }

    @Override
    public ArrayList<Critics> getAllCritics()  {
        return null;
    }

    @Override
    public boolean checkPasswordCritics(int pas, int id) throws Exception {
        return false;
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
    public BookProvider getbookprice(Double BookPrice) {
        return null;
    }

    @Override
    public int[] getFlagId() {
        return new int[0];
    }
}
