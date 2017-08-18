package com.example.katz.myavtivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.katz.myavtivity.src.model.backend.Backend;
import com.example.katz.myavtivity.R;
import com.example.katz.myavtivity.src.entities.ConstValue;
import com.example.katz.myavtivity.src.entities.Critics;
import com.example.katz.myavtivity.src.entities.Customer;
import com.example.katz.myavtivity.src.entities.Provider;
import com.example.katz.myavtivity.src.model.backend.BackendFactory;

public class Log_In extends AppCompatActivity {

    public BackendFactory instance1 = new BackendFactory();
    final Backend instance = BackendFactory.getInstance();//TODO:ASK DAVID
    int id;
    int pas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log__in);

        final ImageButton imageButton = (ImageButton) findViewById(R.id.imageButton);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  Toast.makeText(getApplicationContext(), "Data saved", Toast.LENGTH_LONG).show();
                DialogFragment dialog = new MyDialogFragment();
                dialog.show(getFragmentManager(), "MyDialogFragmentTag");
                // imageButton.setText(helloLine);
            }


        });

        final Context context = this;

        final Button button3 = (Button) findViewById(R.id.button3);

        // thiscontext = container.getContext();
       // Intent intent = new Intent(Log_In.this ,BooksActivity.class);
        //startActivity(intent);

        button3.setOnClickListener(new View.OnClickListener() {

                Provider provider = new Provider();
                Customer customer = new Customer();
                Critics critics = new Critics();
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Log_In.this,BooksActivity.class);
                    EditText ID = (EditText) findViewById(R.id.editText);// ------------from here-----------
                    EditText Pas = (EditText) findViewById(R.id.editText2);
                    try {
                    id = Integer.parseInt(ID.getText().toString());
                    pas = Integer.parseInt(Pas.getText().toString());


                    // check if exist this user with pas id

                        if ( instance.checkPasswordProvider(pas, id))
                        {
                            provider = instance.findProviderById(id);
                            intent.putExtra(ConstValue.getProviderObj(),provider);
                        }
                        else if(instance.checkPasswordCustomer(pas, id)) {
                            customer = instance.findCustomerById(id);
                            intent.putExtra(ConstValue.getCostumerObj(),customer);
                        }
                        else if (instance.checkPasswordCritics(pas, id)) {
                            critics = instance.findCriticById(id,null);
                            intent.putExtra(ConstValue.getCriticObj(),critics);
                        }
                        else
                            throw new Exception("mo");
                    }
                    catch (Exception e) {
                        AlertDialog dialog = new AlertDialog.Builder(context).create();
                        dialog.setTitle("ERROR");
                        dialog.setMessage("The password or ID not correct");
                        dialog.show();
                        return ;
                    }
                    startActivity(intent);
                }
            });

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
                    // You don't have to do anything here if you just want it dismissed when clicked
                }
            });

            // Create the AlertDialog object and return it
            return builder.create();
        }
    }
    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_log__in, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_send) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    } */
}
