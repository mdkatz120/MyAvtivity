package com.example.shorfamily.myavtivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Spinner;
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
import com.example.shorfamily.myavtivity.src.model.datasource.DatabaseList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BookDetails extends AppCompatActivity {

    public BackendFactory instance = new BackendFactory();
    private Backend backend = BackendFactory.getInstance();

    private String[] drawerListViewItems;
    private ListView drawerListView;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private Book book;
    private Customer customer;
    private Provider provider;
    private BookProvider bp;
    boolean flugAddComments;
    private Critics critic;
    ArrayAdapter<String> LTRadapter;
    private Context mContext;
    private static String[] momo = null;
    private ArrayList<BookProvider> booksp;
    private ArrayList<Critics> myCriticList;
    Itemc itemc = null;
    private ArrayList<Itemc> mylistc = new ArrayList<Itemc>();
    private ArrayList<Critics> critics;



    void initItemListBP() throws Exception {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);
        provider = new Provider();
        customer = new Customer();
        critic = new Critics();
        bp = new BookProvider();
        flugAddComments = false;
        findViewById(R.id.button5).setVisibility(View.INVISIBLE);
        if (getIntent().getExtras() != null) {
            if (getIntent().getExtras().containsKey(ConstValue.getProviderObj())) {
                provider = (Provider) getIntent().getSerializableExtra(ConstValue.getProviderObj());
                flugAddComments = false;
            }
            if (getIntent().getExtras().containsKey(ConstValue.getCostumerObj())) {
                customer = (Customer) getIntent().getSerializableExtra(ConstValue.getCostumerObj());
                findViewById(R.id.button5).setVisibility(View.VISIBLE);
                flugAddComments = false;
            }
            if (getIntent().getExtras().containsKey(ConstValue.getCriticObj())) {
                critic = (Critics) getIntent().getSerializableExtra(ConstValue.getCriticObj());
                flugAddComments = true;
            }
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       // getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_launcher);
        mContext = this;
       // get list items from strings.xml
        drawerListViewItems = getResources().getStringArray(R.array.planets_array);

        // get ListView defined in activity_main.xml
        drawerListView = (ListView) findViewById(R.id.left_drawer);

        // Set the adapter for the list view
        drawerListView.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_listview_item, drawerListViewItems));

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        actionBarDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                drawerLayout,         /* DrawerLayout object */
                toolbar,             /* nav drawer icon to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description */
                R.string.drawer_close  /* "close drawer" description */
        );

        // Set actionBarDrawerToggle as the DrawerListener
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        drawerListView.setOnItemClickListener(new DrawerItemClickListener());


        String selectedFromList = getIntent().getStringExtra("book");


        book = backend.getBook(selectedFromList);
        myCriticList = backend.getAllCritics();
        try {
            initItemListBP();
        } catch (Exception e) {
            e.printStackTrace();
        }
        TextView autorname = (TextView) findViewById(R.id.textView6);
        TextView bookname = (TextView) findViewById(R.id.textView5);
        TextView ganre = (TextView) findViewById(R.id.textView8);
        TextView summry = (TextView) findViewById(R.id.textView7);

        summry.setText(book.getSummary());
        ganre.setText(book.getGenre());
        autorname.setText(book.getAuthor());
        bookname.setText((selectedFromList));

        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);

        String criticNames[]=new String[mylistc.size()];
        for (int i=0;i<mylistc.size();i++)
            criticNames[i]=mylistc.get(i).nameCritic;

        final Button button5 = (Button) findViewById(R.id.button5);

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(BookDetails.this, provider.class);
                Bundle bund = new Bundle();
                String bname=book.getName();
                bund.putSerializable(ConstValue.getBookObj(),book);
                bund.putSerializable(ConstValue.getCostumerObj(),customer);
                intent.putExtras(bund);
                startActivity(intent);

            }

        });

        LTRadapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_item, criticNames);
        LTRadapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner2.setAdapter(LTRadapter);

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selected = myCriticList.get(position).getName();
                critic = backend.getCritic(selected);
                RatingBar ratingBar =(RatingBar) findViewById(R.id.ratingBar);
                TextView comment = (TextView) findViewById(R.id.textView9);
                double d = critic.getBrating();
                float f =(float)d;
                ratingBar.setRating(f);
                comment.setText(critic.getCommentary());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });



        final ImageButton imageButton = (ImageButton) findViewById(R.id.imageButton);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment dialog = new MyDialogFragment();
                dialog.show(getFragmentManager(), "MyDialogFragmentTag");
            }


        });


}

    public void initSpinner()
    {
        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);

        String criticNames[]=new String[mylistc.size()];
        for (int i=0;i<mylistc.size();i++)
            criticNames[i]=mylistc.get(i).nameCritic;

        LTRadapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_item, criticNames);
        LTRadapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner2.setAdapter(LTRadapter);

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // call ActionBarDrawerToggle.onOptionsItemSelected(), if it returns true
        // then it has handled the app icon touch event

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }*/
   private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            //Toast.makeText(BookDetails.this, ((TextView)view).getText(), Toast.LENGTH_LONG).show();
            //Fragment fragment = new NewAddBookFragment();
            //FragmentManager fragmentManager = getFragmentManager();
            switch (position) {
                default:
                case 0:
                    final Intent intent = new Intent(BookDetails.this, MainActivity.class);
                    startActivity(intent);
                    drawerLayout.closeDrawer(drawerListView);
                    break;
                case 1:
                    if(getIntent().getExtras().containsKey(ConstValue.getProviderObj())) {
                        bp = new BookProvider();// this line moved from onCreate and fix the problem of double element same val
                        bp.setBook(book);
                        bp.setProvider(provider);
                        final Dialog dialog = new Dialog(mContext);
                        dialog.setContentView(R.layout.dialog_add_exist_book);
                        if (book != null) {
                            dialog.setTitle("ADD PRICE");
                            Button OK = (Button) dialog.findViewById(R.id.OK_Add);
                            OK.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    try {
                                        bp.setPrice(Double.parseDouble(((EditText) dialog.findViewById(R.id.price_insert)).getText().toString()));
                                        bp.setAmount(Integer.parseInt(((EditText) dialog.findViewById(R.id.amount_insert)).getText().toString()));
                                        instance.getInstance().addBookProvider(bp);
                                    } catch (Exception e) {
                                        e = new Exception("You don't fill details");
                                    }
                                    dialog.dismiss();
                                }
                            });
                            dialog.show();
                        } else {
                            AlertDialog alertDialog = new AlertDialog.Builder(mContext).create();
                            alertDialog.setTitle("ERROR");
                            alertDialog.setMessage("The book is empty!");
                            alertDialog.show();
                        }
                    }
                    else{
                        AlertDialog alertDialog = new AlertDialog.Builder(mContext).create();
                        alertDialog.setTitle("ERROR");
                        alertDialog.setMessage("Don't have you permission to add book");
                        alertDialog.show();}
                    drawerLayout.closeDrawer(drawerListView);

                    break;
                case 2:
                    if(getIntent().getExtras().containsKey(ConstValue.getProviderObj())) {
                        try {
                            instance.getInstance().deleteBookProvider(book.getId(), provider.getId());
                        } catch (Exception e) {
                            AlertDialog alertDialog = new AlertDialog.Builder(mContext).create();
                            alertDialog.setTitle("ERROR");
                            alertDialog.setMessage(e.getMessage());
                            alertDialog.show();
                        }
                    }
                    else{
                        AlertDialog alertDialog = new AlertDialog.Builder(mContext).create();
                        alertDialog.setTitle("ERROR");
                        alertDialog.setMessage("Don't have you permission to delete ");
                        alertDialog.show();}
                    drawerLayout.closeDrawer(drawerListView);

                    break;
                case 3:
                  //  Critics c = new Critics();
                    try {
                   //     c =instance.getInstance().findCriticById(critic.getId(),book.getCommends());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if(flugAddComments==true /*&& c == null*/) {
                        final Dialog dialog1 = new Dialog(mContext);
                        dialog1.setContentView(R.layout.dialog_add_comments);
                        dialog1.setTitle("Add Comment");
                        if (book != null) {
                            Button OK = (Button) dialog1.findViewById(R.id.add_comment);
                            OK.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    try {
                                        EditText commentEdit = (EditText) dialog1.findViewById(R.id.editComment);
                                        if ((commentEdit).getText().toString().length() > 100) {
                                            AlertDialog alertDialog = new AlertDialog.Builder(mContext).create();
                                            alertDialog.setTitle("ERROR");
                                            alertDialog.setMessage("Your Comments are too long");
                                            alertDialog.show();
                                            commentEdit.setText("");
                                        } else {
                                            ArrayList<Critics>c = new ArrayList<Critics>();
                                          //  c = book.getCommends();
                                            critic.setCommentary((commentEdit).getText().toString());
                                            RatingBar ratingBar = (RatingBar) dialog1.findViewById(R.id.star_critic);
                                            critic.setBrating(Double.parseDouble(String.valueOf(ratingBar.getRating())));
                                            instance.getInstance().updateCritic(critic);
                                            instance.getInstance().addCritic(critic);
                                          //  book.setCommends(c);
                                          //  instance.getInstance().updateBook(book);
                                            initSpinner();
                                        }
                                    } catch (Exception e) {
                                        e = new Exception("You don't fill details right");
                                    }
                                    dialog1.dismiss();
                                }
                            });
                            dialog1.show();
                        }
                    }
                    else{
                        AlertDialog alertDialog = new AlertDialog.Builder(mContext).create();
                        alertDialog.setTitle("ERROR");
                        alertDialog.setMessage("Don't have you permission to add comment");
                        alertDialog.show();}

                    drawerLayout.closeDrawer(drawerListView);

                    break;
                case 4:
                    Intent intent2 = new Intent(BookDetails.this, MainActivity.class);
                    startActivity(intent2);
                    drawerLayout.closeDrawer(drawerListView);
                    break;
            }

        }
    }
    public static class MyDialogFragment extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            // Use the Builder class for convenient dialog construction
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("About us");
            builder.setMessage("The Book Store app is made by Yonatan Shor and David Katz");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // You don\'t have to do anything here if you just want it dismissed when clicked
                }
            });

            // Create the AlertDialog object and return it
            return builder.create();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bookdetails, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_send || actionBarDrawerToggle.onOptionsItemSelected(item)) {
            Intent intent = new Intent(BookDetails.this, MainActivity.class);
            startActivity(intent);
            return true;
        }
        Intent intent = new Intent(BookDetails.this, MainActivity.class);
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }

    class Itemc {
        public String nameCritic;

        Itemc(String nameCritic)
        {
            this.nameCritic = nameCritic;
        }
    }
}
