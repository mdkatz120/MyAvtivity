package com.example.katz.myavtivity.src.model.datasource;

import com.example.katz.myavtivity.src.entities.Book;
import com.example.katz.myavtivity.src.entities.BookProvider;
import com.example.katz.myavtivity.src.entities.Cart;
import com.example.katz.myavtivity.src.entities.Critics;
import com.example.katz.myavtivity.src.entities.Customer;
import com.example.katz.myavtivity.src.entities.Order;
import com.example.katz.myavtivity.src.entities.Provider;
import com.example.katz.myavtivity.src.model.backend.Backend;
import com.example.katz.myavtivity.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by katz on 24/11/2015.
 */
public class DatabaseList implements Backend {
    protected ArrayList<Book> books = new ArrayList<>();
    protected ArrayList<Critics> critics = new ArrayList<>();
    protected ArrayList<Provider> providers = new ArrayList<>();
    protected ArrayList<Customer> customers = new ArrayList<>();
    protected ArrayList<BookProvider> bookProviders = new ArrayList<BookProvider>();
    protected ArrayList<Order> orders = new ArrayList<Order>();
    String[] BookName = new String[]{"Harry Potter", "The Hunger Games", "The Hobbit","The Lord Of The Rings","bobo","amam","popo","bzbz","hjhj"};
    String[] ProviderName = new String[]{"Avi King","Yosi Levi","Moshe Israeli","Moshe Israeli","Moshe Israeli","Moshe Israeli","Moshe Israeli","Moshe Israeli","Moshe Israeli"};
    String[] ProviderWorking = new String[]{"Center","Judea and Samaria","North","South","Jerusalem","Jerusalem","Jerusalem","Jerusalem","Jerusalem"};
    Double[] BookPrice = new Double[]{38.0,45.0,32.0,32.0,32.0,32.0,32.0,32.0,32.0};
    int[] Amount = new int[]{5,2,3,4,1,5,6,4,3};
    Double[] CBrating = new Double[]{0.0,5.0,4.5,3.5,5.0,5.0,5.0,5.0,5.0};
    String[] AuthorName = new String[]{"J.K Rolling", "Suzanne Collins", "J.R.R Tolkien","J.R.R Tolkien","bobo","amam","popo","bzbz","hjhj"};
    String[] Ganre = new String[]{"Fantasy","Fantasy","Fantasy","Fantasy","Fantasy","Fantasy","Fantasy","Fantasy","Fantasy"};
    String[] Summry = new String[]{"The series chronicles the life of a young wizard and his friends. all of whom are students at Hogwarts School of Witchcraft and Wizardry. The main story arc concerns Harry's struggle to kill the Dark wizard Lord Voldemort."};
    String[] Critic = new String[]{"Choose a crirtic","Irit Linor","Arik Glesner","Eli Hirsh","Yuval Avivi","Uri Wiess","Uri Wiess","Uri Wiess","Uri Wiess"};
    String[] Ccomments = new String[]{"All of the BookStore app critics are well-known book critics","Great book! a real one of a kind fantasy sutiable for all the family","Great book! a real one of a kind fantasy sutiable for all the family","Great book! a real one of a kind fantasy sutiable for all the family","Great book! a real one of a kind fantasy sutiable for all the family","Great book! a real one of a kind fantasy sutiable for all the family","Great book! a real one of a kind fantasy sutiable for all the family","Great book! a real one of a kind fantasy sutiable for all the family","Great book! a real one of a kind fantasy sutiable for all the family"};
    int[] FlagId = new int[]{R.drawable.greenbook3};

    /// Counter for id number of each of the elements
    public  static int CounterBook = 10000;
    public  static int CounterOrder = 1000;
    public  static int CounterCostumer = 100;
    public  static  int CounterProvider = 0;
    public  static  int CounterCritic = 50;

    public DatabaseList() {


        for(int i=0;i<BookName.length;i++)
        {
            Book book = new Book();

            book.setName(BookName[i]);
            book.setAuthor(AuthorName[i]);
            book.setGenre(Ganre[i]);
            book.setSummary(Summry[0]);
            //bookP.setPrice(BookPrice[i]);
            try {
                addBook(book);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        //BookProvider is the Book in the eye of 1 Provider
         for(int i=0;i<ProviderName.length;i++)
        {
            Provider provider = new Provider();

            provider.setName(ProviderName[i]);
            provider.setLivingArea(ProviderWorking[i]);

            try {
                addProvider(provider);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        for(int i=0;i<Critic.length;i++)
        {
            Critics critic = new Critics();
            ArrayList<Critics> c = new ArrayList<Critics>();
            critic.setName(Critic[i]);
            critic.setCommentary(Ccomments[i]);
            critic.setBrating(CBrating[i]);

            try {
               // c.add(critic);
               // books.get(i).setCommends(c);
                addCritic(critic);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        for(int i=0;i<BookName.length;i++)
        {
            //Book book = new Book();
            BookProvider bookP = new BookProvider();
            bookP.setProvider(getProvider(ProviderName[i]));
            bookP.setBook(getBook(BookName[i]));
            bookP.setPrice(BookPrice[i]);
            bookP.setAmount(Amount[i]);
            /*String booki =(books.get(i).getName());
            for(int j=0;j<booki.length();j++)
            {

                bookP.setPrice(BookPrice[j]);
            }*/
            try {
                addBookProvider(bookP);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }



    }

   @Override
    public int[] getFlagId() {
        return FlagId;
    }



    /// func of bookList
    @Override
    public void addBook(Book book) throws Exception {
        for (Book item : books) {
            if (item.equals(book))
                throw new Exception("The book is already exsit");
        }
        book.setId(CounterBook++);
        books.add(book);
    }

    @Override
    public void addCritic(Critics critic) throws Exception
    {
        for (Critics item : critics) {
            if (item.getId() == critic.getId())
                throw new Exception("It's critic alreday exist");
        }
        critic.setId(CounterCritic++);
        critics.add(critic);
    }

    @Override
    public void deleteCritic(int id) {
        for (Critics item : critics) {
            if (item.getId() == id) {
                critics.remove(item);
                return;
            }
        }
    }

    @Override
    public Critics findCriticById(int id, ArrayList<Critics> criticses)throws Exception
    {
        if (criticses == null)
            criticses = critics;
        for (Critics b : criticses)
        {
            if (b.getId() == id)
                return b;
        }
        return null;
    }
    @Override
    public boolean checkPasswordProvider(int pas, int id) throws Exception {
        if (findProviderById(id)!=null )
        {
            if(findProviderById(id).getPassword()== pas)
                return true;
        }
        return false;
    }

    @Override
    public boolean checkPasswordCustomer(int pas, int id) throws Exception {
        if(findCustomerById(id)!=null) {
            if (findCustomerById(id).getPassword() == pas) {
                return true;
            }
        }
        return false;
    }

        @Override
    public boolean checkPasswordCritics(int pas, int id) throws Exception {
        if(findCriticById(id, null)!=null) {
            if (findCriticById(id, null).getPassword() == pas) {
                return true;
            }
        }
        return false;
    }
    @Override
    public Book findBookById(int id) throws Exception {
        for (Book b : books)
        {
            if (b.getId() == id)
                return b;
        }
        return null;
    }

    @Override
    public void deleteBook(int id) throws Exception {
        for (Book item:books){
            if (item.getId() == id)
            {books.remove(item);
            return;}

        }
        throw new Exception("�� ���� ��� ������");
    }

    @Override
    public ArrayList<Book> getAllBooks() throws Exception {
        return books;
    }

    @Override
    public ArrayList<Critics> getAllCritics(){
        return critics;
    }

/// func of provider
    @Override
    public void addProvider(Provider provider) throws Exception {
        for (Provider item : providers) {
            if (item.equals(provider))
                throw new Exception("��� �� ��� ���� ������ ������");
        }
        provider.setId(CounterProvider++);
        providers.add(provider);
    }

    @Override
    public Provider findProviderById(int id) throws Exception {
        for (Provider p : providers)
        {
            if(p.getId() == id)
                return p;
        }
        return null;
    }

    @Override
    public void deleteProvider(int id) throws Exception {
        for (Provider item : providers){
            if (item.getId() == id)
            {providers.remove(item);
            return;}
        }
        throw new Exception("�� ���� ��� ������");
    }

    @Override
    public ArrayList<Provider> getAllProviders() throws Exception {
        return providers;
    }

    @Override
    public ArrayList<Order> getAllOrders() throws Exception {
        return orders;
    }

    /// func of bookProvider
    @Override
    public void addBookProvider(BookProvider bookProvider) throws Exception {
        bookProviders.add(bookProvider);
    }

    @Override
    public BookProvider findBpById(int bookID, int providerID) throws Exception {
        for (BookProvider bp : bookProviders)
        {
            if((bp.getBook().getId() == bookID) && (bp.getProvider().getId() == providerID))
                return bp;
        }
        return null;    }

    @Override
    public void deleteBookProvider(int bookID, int providerID) throws Exception {
            if (findBpById(bookID, providerID) == null) {
                throw new Exception("��� �� �� ���� ����� �� ��� �������");
            }

            bookProviders.remove(findBpById(bookID, providerID));

    }

    @Override
    public ArrayList<BookProvider> getAllBookProviders() throws Exception {
        return bookProviders;    }

    ///func of Costumers
    @Override
    public void addCustomer(Customer customer) throws Exception {
        for (Customer item : customers) {
            if (item.equals(customer))
                throw new Exception("���� ����");
        }
        customer.setSerialNumber(CounterCostumer++);
        customers.add(customer);    }

    @Override
    public Customer findCustomerById(int id) throws Exception {
        for (Customer c : customers) {
            if (c.getId() == id)
                return c;
        }
        return null;    }

    @Override
    public void deleteCustomer(int id) throws Exception {

            if (findCustomerById(id) == null) {
                throw new Exception("���� �� ���� ����");
            }
            customers.remove(findCustomerById(id));

    }

    @Override
    public ArrayList<Customer> getAllCestomers() throws Exception {
        return customers;    }


    /// func for choose the best seller
    @Override
    public ArrayList<Book> getBestSellersBooks() throws Exception {
        ArrayList<Book> bookList = new ArrayList<Book>();
        List<Book> tempBookList = books;
        Book tempBook;
        for (int i = 0; i<3; i++)
        {
            if (!books.isEmpty())
                tempBook = books.get(0);
            else
                break;
            for (Book b : tempBookList)
            {
                if (b.getSoldNumber()>tempBook.getSoldNumber())
                    tempBook = b;
            }
            books.remove(tempBook);
            tempBookList.add(tempBook);
        }
        return bookList;
    }

    @Override
    public ArrayList<Critics> getCommendsOfBook(Book book) throws Exception {
        for(Book item:books) {
         if(findBookById(book.getId()) == book)
            {
                if(item.getCommends().isEmpty())
                {throw new Exception("Don't have any comments");}
                return item.getCommends();
            }
        }
        throw new Exception("Don't have any comments");
    }

    public void setList() {
        Book book = new Book();
        Critics critic = new Critics();
        BookProvider bookp = new BookProvider();

        for(int i=0;i<BookName.length;i++)
        {
            ArrayList<Critics> c = new ArrayList<Critics>();
            book.setName(BookName[i]);
            book.setAuthor(AuthorName[i]);
            book.setGenre(Ganre[i]);
            book.setSummary(Summry[0]);
            critic.setName(Critic[i]);
            critic.setCommentary(Ccomments[i]);
            critic.setBrating(CBrating[i]);
            c.add(critic);
            book.setCommends(c);
            //bookp.setPrice(BookPrice[i]);
            try {
                addCritic(critic);
                //addBookProvider(bookp);
                addBook(book);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        for(int j=0;j<ProviderName.length;j++)
        {
            bookp.setPrice(BookPrice[j]);
            try {
                addBookProvider(bookp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Book getBook(String bookName) {
        for (Book b : books)
        {
            if(b.getName().equals(bookName))
                return b;
        }
        return null;
    }

    @Override
    public BookProvider getBookp(String bookName) {
        for (BookProvider bp : bookProviders)
        {
            if(bp.getBook().getName().equals(bookName))
                return bp;
        }
        return null;
    }

    @Override
    public BookProvider getbookprice(Double BookPrice) {
        for (BookProvider bp : bookProviders)
        {
            if(bp.getPrice().equals(BookPrice))
                return bp;
        }
        return null;
    }

    @Override
    public Provider getProvider(String providerName) {
        for (Provider p : providers)
        {
            if(p.getName().equals(providerName))
                return p;
        }
        return null;
    }

        @Override
    public Critics getCritic(String criticName) {
        for (Critics c : critics)
        {
            if(c.getName().equals(criticName))
                return c;
        }
        return null;
    }

    @Override
    public void updateCritic(Critics critics) throws Exception {
        if(findCriticById(critics.getId(), null)==null)
        { throw new Exception("This Book Not Exist");}
        //save id, delete old critic , add new critic with same id and new details , insert to counter preview Counter
        int id = (findCriticById(critics.getId(),null)).getId();
        deleteCritic(id);
        int Counter = CounterCritic;
        CounterCritic = id--;
        addCritic(critics);
        CounterCritic = Counter;
    }

    @Override
    public void UpdateBp(BookProvider bookProvider)throws Exception {
        if(findBpById(bookProvider.getBook().getId(),bookProvider.getProvider().getId())==null)
        { throw new Exception("This BookProvider Not Exist");}
        deleteBookProvider(bookProvider.getBook().getId(),bookProvider.getProvider().getId());
        addBookProvider(bookProvider);

    }
    @Override
    public void updateBook(Book book) throws Exception {
        if(findBookById(book.getId())==null)
        { throw new Exception("This Book Not Exist");}
        //save id, delete old book , add new book with same id , insert to counter preview Counter
        int id = (findBookById(book.getId())).getId();
        deleteBook(id);
        int Counter = CounterBook;
        CounterBook = id--;
        addBook(book);
        CounterBook = Counter;
    }
    @Override
    public void makeOrder(Order order) throws Exception {
        for (Cart c : order.getBooks()) {
            c.getBookProvider().getBook().setSoldNumber(c.getAmount());
            //update soldnumber of book
            Book book =    findBookById(c.getBookProvider().getBook().getId());
            book.setSoldNumber((findBookById(c.getBookProvider().getBook().getId())).getSoldNumber() + c.getAmount());
            updateBook(book);
            // reduce amount from Bookprovider what that buy
            BookProvider bp =    findBpById(c.getBookProvider().getBook().getId(), c.getBookProvider().getProvider().getId());
            int amount = bp.getAmount();
            bp.setAmount(amount - c.getAmount());
            UpdateBp(bp);
            //update cart
            c.getBookProvider() .setAmount(amount - c.getAmount());
            c.getBookProvider().setSoldNumber(c.getAmount());
        }
        order.setOrderNumber(CounterOrder++);
        orders.add(order);
    }


}
