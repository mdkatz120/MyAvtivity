package com.example.shorfamily.myavtivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shorfamily.myavtivity.src.entities.Book;
import com.example.shorfamily.myavtivity.src.entities.BookProvider;
import com.example.shorfamily.myavtivity.src.entities.ConstValue;
import com.example.shorfamily.myavtivity.src.entities.Critics;
import com.example.shorfamily.myavtivity.src.entities.Customer;
import com.example.shorfamily.myavtivity.src.entities.Provider;
import com.example.shorfamily.myavtivity.src.model.backend.Backend;
import com.example.shorfamily.myavtivity.src.model.backend.BackendFactory;

import java.util.ArrayList;

public class BooksActivity extends AppCompatActivity {

    private ArrayList<Book> myItemList;
    private ArrayList<Critics> myCriticList;
    Item item = null;
    Itemc itemc = null;
    private ArrayList<Item> mylist = new ArrayList<Item>();
    private ArrayList<Itemc> mylistc = new ArrayList<Itemc>();
    ArrayList<Book> books1= new ArrayList<Book>();
    private ArrayList<Book> books;
    //private ArrayList<BookProvider> booksp;
    private ArrayList<Critics> critics;
    private static String[] momo = null;
    int mPosition; //  position item selected
    BookProvider bp; // this object add to bookproviderlist
    Provider provider;
    Customer customer;
    Critics critic;
    boolean flugAdd; // check if user he is provider or not
    Context context = this;
    ListView listView;



    public BackendFactory instance = new BackendFactory();
    private Backend backend=BackendFactory.getInstance();


    String[] BookName = new String[]{"Harry Potter", "The Hunger Games", "The Hobbit", "mo", "bobo", "amam", "popo", "bzbz", "hjhj"};
    String[] AuthorName = new String[]{"J.K Rolling", "Suzanne Collins", "J.R.R Tolkien", "momo", "bobo", "amam", "popo", "bzbz", "hjhj"};
    int[] FlagId = new int[]{R.drawable.greenbook3, R.drawable.greenbook3, R.drawable.greenbook3, R.drawable.greenbook3, R.drawable.greenbook3, R.drawable.greenbook3, R.drawable.greenbook3, R.drawable.greenbook3, R.drawable.greenbook3};

    void initItemList() throws Exception {
        myItemList = BackendFactory.getInstance().getAllBooks();
        //mylist = new ArrayList<Item>();
       //
        for (Book book : myItemList) {

            try {
                item = new Item(book.getName(), book.getAuthor(), FlagId[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
            mylist.add(item);
        }

        myCriticList = BackendFactory.getInstance().getAllCritics();

        for (Critics critic : myCriticList) {

            try {
                itemc = new Itemc(critic.getName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            mylistc.add(itemc);
        }

    }

    void initItemByListView() {
        listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Bundle bund = new Bundle();
                String name=books.get(position).getName();
                bund.putString("book", name);// here we insert to the bund the book

                Intent intent = new Intent(BooksActivity.this, BookDetails.class);
                //Get the value of the item you clicked
                if(getIntent().getExtras() != null) {  // get the details of user
                    if (getIntent().getExtras().containsKey(ConstValue.getProviderObj())) {
                        bund.putSerializable(ConstValue.getProviderObj(), provider);
                    }
                    if (getIntent().getExtras().containsKey(ConstValue.getCostumerObj())) {
                        bund.putSerializable(ConstValue.getCostumerObj(), customer);
                    }
                    if (getIntent().getExtras().containsKey(ConstValue.getCriticObj())) {
                        bund.putSerializable(ConstValue.getCriticObj(),critic);
                    }
                }
                intent.putExtras(bund); // send
                startActivity(intent);
            }
        });
        listView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mPosition = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(), "msg msg", Toast.LENGTH_LONG).show();
            }
        });

        ArrayList<Book> books1=null;
        try {
            books1 = new GetBookListTask().execute().get();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
         books=books1;
        ArrayList<Critics> critics1=null;
        try {
            critics1 = new GetBookListTaskc().execute().get();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        critics=critics1;
        ArrayAdapter<Book> adapter = new ArrayAdapter<Book>(this, R.layout.row_book, books) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = View.inflate(BooksActivity.this, R.layout.row_book, null);
                }
                TextView BookNameTextView = (TextView) convertView.findViewById(R.id.text_title);

                TextView BookAuthorTextView = (TextView) convertView.findViewById(R.id.text_working);

                ImageView FlagId = (ImageView) convertView.findViewById(R.id.img_thumbnail);

                BookNameTextView.setText((books.get(position).getName()));

                BookAuthorTextView.setText((books.get(position).getAuthor()));

                return convertView;
            }
        };
        listView.setAdapter(adapter);
    }




    private class GetBookListTask extends AsyncTask<Void, Void, ArrayList<Book>> {
        private String exception = "";

        @Override
        protected ArrayList<Book> doInBackground(Void... params) {
            ArrayList<Book> books;

            try {
                books = (ArrayList<Book>)backend.getAllBooks();
            } catch (Exception e) {
                exception = e.getMessage();
                books = null;
            }

            return books;
        }


        @Override
        protected void onPostExecute(ArrayList<Book> books) {
            if (!exception.equals("")){

            }
        }
    }

    private class GetBookListTaskc extends AsyncTask<Void, Void, ArrayList<Critics>> {
        private String exception = "";

        @Override
        protected ArrayList<Critics> doInBackground(Void... params) {
            ArrayList<Critics> critics;

            try {
                critics = (ArrayList<Critics>)backend.getAllCritics();
            } catch (Exception e) {
                exception = e.getMessage();
                critics = null;
            }

            return critics;
        }


        @Override
        protected void onPostExecute(ArrayList<Critics> critics) {
            if (!exception.equals("")){

            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);
        provider= new Provider();
        customer = new Customer();
        critic = new Critics();
        bp = new BookProvider();
        flugAdd = false;
        if(getIntent().getExtras() != null) {
            if (getIntent().getExtras().containsKey(ConstValue.getProviderObj())) {
                provider = (Provider) getIntent().getSerializableExtra(ConstValue.getProviderObj());
                flugAdd = true;
            }
            if (getIntent().getExtras().containsKey(ConstValue.getCostumerObj())) {
                customer = (Customer) getIntent().getSerializableExtra(ConstValue.getCostumerObj());
                flugAdd = false;
            }
            if (getIntent().getExtras().containsKey(ConstValue.getCriticObj())) {
                critic = (Critics) getIntent().getSerializableExtra(ConstValue.getCriticObj());
                flugAdd = false;
            }
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        bp = new BookProvider();
        provider = new Provider();
        // instance.getInstance(this).setList();
        //progressDialog = new ProgressDialog(this);
        //progressDialog.dismiss();
        //startActivity(intent);
        setProgressBarIndeterminateVisibility(false);

        initItemByListView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_add_circle, menu);
        // user not provider can just log out
        if(flugAdd == false) {
            menu.findItem(R.id.ic_add_circle_).setVisible(false);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        item.setChecked(true);
        int id = item.getItemId();


        Book book = null;
        //book get data of book in position selected
        try {
            book = books.get(mPosition);

            //noinspection SimplifiableIfStatement
            switch(id) {
                case R.id.ic_add_circle_:
                    getFragmentManager().findFragmentById(R.id.fragment_add);
                    Fragment fragment = new NewAddBookFragment();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_add, fragment)
                            .addToBackStack(null)
                            .commit();
                    break;
                case R.id.log_out:
                    Intent intent = new Intent(BooksActivity.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                    break;


            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return super.onOptionsItemSelected(item);
    }

    class Item {
        public String nameBook;
        public String nameAuoter;
        public int flag;

        Item(String nameBook, String nameAuoter, int flag) {
            this.nameBook = nameBook;
            this.nameAuoter = nameAuoter;
            this.flag = flag;
        }

    }

    class Itemc {
        public String nameCritic;

        Itemc(String nameCritic)
        {
            this.nameCritic = nameCritic;
        }
    }


}





