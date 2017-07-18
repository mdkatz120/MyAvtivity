package com.example.shorfamily.myavtivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.example.shorfamily.myavtivity.src.entities.Book;
import com.example.shorfamily.myavtivity.src.entities.BookProvider;
import com.example.shorfamily.myavtivity.src.entities.Cart;
import com.example.shorfamily.myavtivity.src.entities.ConstValue;
import com.example.shorfamily.myavtivity.src.entities.Customer;
import com.example.shorfamily.myavtivity.src.entities.Order;
import com.example.shorfamily.myavtivity.src.entities.Provider;
import com.example.shorfamily.myavtivity.src.model.backend.Backend;
import com.example.shorfamily.myavtivity.src.model.backend.BackendFactory;

import java.util.ArrayList;

public class provider extends AppCompatActivity {
    public BookProvider bookp = new BookProvider();
    public BookProvider bookprice = new BookProvider();
    public Book book = new Book();
    public Customer customer = new Customer();
    public Order order = new Order();
    public Provider provider = new Provider();
    private ArrayList<Cart> carts;
    public BookProvider bookprovider = new BookProvider();
    private ArrayList<Provider> myItemList;
    private ArrayList<BookProvider> myItemListBP;
    Item item = null;
    ItemBP itemBP = null;
    private ArrayList<Item> mylist = new ArrayList<Item>();
    private ArrayList<ItemBP> mylistBP = new ArrayList<ItemBP>();
    ArrayList<Provider> providers1 = new ArrayList<Provider>();
    ArrayList<BookProvider> bookproviders1 = new ArrayList<BookProvider>();
    private ArrayList<Provider> providers;
    private ArrayList<BookProvider> bookproviders;
    private Context mContext;
    int tmp=1;
//    final NumberPicker aNumberPicker = new NumberPicker(mContext);

    public BackendFactory instance = new BackendFactory();
    ;
    private Backend backend = BackendFactory.getInstance();

    void initItemList() throws Exception {
        myItemList = BackendFactory.getInstance().getAllProviders();
        //mylist = new ArrayList<Item>();
        //
        for (Provider provider : myItemList) {
            //Item item = null;
            try {
                item = new Item(provider.getName(), provider.getLivingArea());
            } catch (Exception e) {
                e.printStackTrace();
            }
            mylist.add(item);
        }
    }

    void initItemListBP() throws Exception {
        myItemListBP = BackendFactory.getInstance().getAllBookProviders();
        //mylist = new ArrayList<Item>();
        //
        for (BookProvider price : myItemListBP) {
            //Item item = null;
            try {
                itemBP = new ItemBP(price.getPrice().toString(), price.getProvider().getName().toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
            mylistBP.add(itemBP);
        }
    }


    void initItemByListView2() {
        final ListView listView2 = (ListView) findViewById(R.id.listView2);
        ArrayList<BookProvider> bookproviders1 = null;
        try {
            bookproviders1 = new GetProvidersListTaskBP().execute().get();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        ArrayList<Provider> providers1 = null;
        try {
            providers1 = new GetProvidersListTask().execute().get();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //providers = providers1;
        bookproviders = bookproviders1;

        ArrayAdapter<BookProvider> bpadapter = new ArrayAdapter<BookProvider>(this, R.layout.row_provider, bookproviders) {
            @Override
            public View getView(int position, View convertView2, ViewGroup parent) {
                if (convertView2 == null) {
                    convertView2 = View.inflate(provider.this, R.layout.row_provider, null);
                }
                TextView BookNameTextView = (TextView) convertView2.findViewById(R.id.text_title);

                TextView BookWorkingTextView = (TextView) convertView2.findViewById(R.id.text_working);

                try {
                    bookp = instance.getInstance().getBookp(book.getName()); // TODO:check later
                } catch (Exception e) {
                    e.printStackTrace();
                }

                BookNameTextView.setText((bookproviders.get(position).getProvider().getName()));

                BookWorkingTextView.setText((bookproviders.get(position).getProvider().getLivingArea()));

                TextView BookPrice = (TextView) convertView2.findViewById(R.id.textView10);
                try {
                bookprice = backend.getbookprice(bookp.getPrice());
            } catch (Exception e) {
                e.printStackTrace();
            }
            BookPrice.setText(bookproviders.get(position).getPrice().toString() + '₪');


                return convertView2;
            }
        };
        listView2.setAdapter(bpadapter);
    }


    private class GetProvidersListTask extends AsyncTask<Void, Void, ArrayList<Provider>> {
        private String exception = "";

        @Override
        protected ArrayList<Provider> doInBackground(Void... params) {
            ArrayList<Provider> providers;

            try {
                providers = (ArrayList<Provider>) backend.getAllProviders();
            } catch (Exception e) {
                exception = e.getMessage();
                providers = null;
            }

            return providers;
        }


        @Override
        protected void onPostExecute(ArrayList<Provider> providers) {
            if (!exception.equals("")) {

            }
        }
    }

    private class GetProvidersListTaskBP extends AsyncTask<Void, Void, ArrayList<BookProvider>> {
        private String exception = "";

        @Override
        protected ArrayList<BookProvider> doInBackground(Void... params) {
            ArrayList<BookProvider> bookproviders;

            try {
                bookproviders = new ArrayList<BookProvider>();
                for(BookProvider bookProvider:instance.getInstance().getAllBookProviders())
                {
                    if(bookProvider.getBook().getName().equals(book.getName())) {
                        bookproviders.add(bookProvider);
                    }
                }
            } catch (Exception e) {
                exception = e.getMessage();
                bookproviders = null;
            }

            return bookproviders;
        }

        @Override
        protected void onPostExecute(ArrayList<BookProvider> bookproviders) {
            if (!exception.equals("")) {

            }
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider);
        Bundle bund = getIntent().getExtras();
        customer = new Customer();
        customer = (Customer) bund.getSerializable(ConstValue.getCostumerObj());

        order.setCustomer(customer);
        book = new Book();
        book = (Book) bund.getSerializable(ConstValue.getBookObj());
        //initItemByListView();
        initItemByListView2();
        initItemByListView3();

    }

    void initItemByListView3() {
        final ListView listView = (ListView) findViewById(R.id.listView2);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

                String name = bookproviders.get(position).getProvider().getName();

                try {
                    bookp = bookproviders.get(position);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    //          bookp = instance.getInstance().getBookp(name); //TODO its the name of the provider but the func want's the name of the book!! need to be checked depply
                } catch (Exception e) {
                    e.printStackTrace();
                }

                final double price =bookp.getPrice();
                final View v = provider.this.getLayoutInflater().inflate(R.layout.fragmentdialog_book_puraches_amount, null);
                ((TextView) v.findViewById(R.id.textView11)).setText("The total price of your book(s) is: " + bookp.getPrice().toString() + '₪');
                final NumberPicker numberPicker = (NumberPicker) v.findViewById(R.id.numberPicker);
                numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                    @Override
                    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                        ((TextView) v.findViewById(R.id.textView11)).setText("The total price of your book(s) is: " + (price * newVal) + '₪');
                        order.setPrice(price * newVal);
                    }
                });
                try {
                    numberPicker.setMaxValue(bookp.getAmount());
                    numberPicker.setMinValue(1);
                    final AlertDialog.Builder builder = new AlertDialog.Builder(provider.this);
                    builder.setTitle("Select the amount wanted:");
                    builder.setView(v).
                            setPositiveButton("Buy", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    try {
                                        //--------------check if its  work----------------
                                        Cart cart = new Cart(bookp,numberPicker.getValue());
                                        bookp.setAmount(bookp.getAmount()-numberPicker.getValue());
                                        carts.add(cart);
                                        order.setBooks(carts); //----add---
                                        new AddNewOrder().execute().get();

//-------------------until its
                                        provider.this.onBackPressed();
                                        dialog.dismiss();
                                    } catch (Exception e) {
                                        //Handling.exceptionHandeling(e.getMessage(), provider.this);
                                    }
                                }
                            }).
                            setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            }).create().show();
                } catch (Exception e) {

                }
            }
        });

    }


    /*public class MyDialogFragment extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            // Use the Builder class for convenient dialog construction
            String selectedFromList = getActivity().getIntent().getStringExtra("bookp");
            book = backend.getBook(selectedFromList);
            //provider = backend.getProvider(providers.toString()).getName().toString();
            bookp = backend.getBookp(book.getName());
            final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            RelativeLayout linearLayout = new RelativeLayout(getActivity());
            final NumberPicker aNumberPicker = new NumberPicker(getActivity());
            aNumberPicker.setMaxValue(bookp.getAmount());
            aNumberPicker.setMinValue(1);
            //final int price = Integer.parseInt(bookp.getPrice().toString());


            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(50, 50);
            RelativeLayout.LayoutParams numPicerParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            numPicerParams.addRule(RelativeLayout.CENTER_HORIZONTAL);

            linearLayout.setLayoutParams(params);
            linearLayout.addView(aNumberPicker, numPicerParams);

            aNumberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                @Override
                public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                    //((TextView) v.findViewById(R.id.bookPuraches_text)).setText("cost" + (price * newVal) + "\u20AA");

                    builder.setMessage("The total price of your book(s) is:" + Double.toString(bookp.getPrice() * newVal) + '₪');
                }
            });
            //AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mContext);bookp.getPrice().toString()
            builder.setTitle("Select the amount wanted:");
            builder.setView(linearLayout);
            //builder.setMessage("The total price of your book(s) is:" + Double.toString(bookp.getPrice() * tmp) + '₪');
            builder
                    .setCancelable(false)
                    .setPositiveButton("Buy",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int id) {
                                    Log.e("", "New Quantity Value : " + aNumberPicker.getValue());

                                }
                            })
                    .setNegativeButton("Cancel",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int id) {
                                    dialog.cancel();
                                }



                            });

            // Create the AlertDialog object and return it
            return builder.create();
        }

    } */

        class Item {
            public String nameProvider;
            public String WorkingProvider;

            Item(String nameProvider, String WorkingProvider) {

                this.nameProvider = nameProvider;
                this.WorkingProvider = WorkingProvider;
            }


        }

        class ItemBP {
            public String BookPrice;
            public String BookProvider;

            ItemBP(String BookPrice, String BookProvider) {
                this.BookPrice = BookPrice;
                this.BookProvider = BookProvider;

            }

        }

    private class AddNewOrder extends AsyncTask<Order, Void, Void> {
        private String exception = "";

        @Override
        protected Void doInBackground(Order... params) {
            try {
                instance.getInstance().makeOrder(order);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    }




