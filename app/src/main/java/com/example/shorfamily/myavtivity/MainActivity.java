package com.example.shorfamily.myavtivity;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.app.DialogFragment;
import android.app.Dialog;
import android.app.AlertDialog;
import android.content.DialogInterface;

import com.example.shorfamily.myavtivity.src.model.backend.BackendFactory;


public class MainActivity extends AppCompatActivity {
    BackendFactory instance = new BackendFactory();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        this.setContentView(R.layout.activity_main);

        //instance.getInstance().setList();

        final ImageButton imageButton = (ImageButton) findViewById(R.id.imageButton);

        final Button button = (Button) findViewById(R.id.button);

        final Button button2 = (Button) findViewById(R.id.button2);

        final TextView textView2 = (TextView) findViewById(R.id.textView2);

        textView2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, BooksActivity.class);
                startActivity(intent);

            }

        });

        button2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, sign_in.class);
                startActivity(intent);

            }

        });

        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                // show click effect on button pressed
                //final AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.2F);
                //buttonClick.setDuration(5);
                //v.startAnimation(buttonClick);
                Intent intent = new Intent(MainActivity.this, Log_In.class);
                startActivity(intent);

            }

        });


        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //   Toast.makeText(getApplicationContext(), "Data saved", Toast.LENGTH_LONG).show();
                DialogFragment dialog = new MyDialogFragment();
                dialog.show(getFragmentManager(), "MyDialogFragmentTag");
                // imageButton.setText(helloLine);
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


    //public void sendMes(View view)
    //{
    //    System.out.println("Hello World");
    //}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        item.setChecked(true);
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id){

            case R.id.ic_web: {
                getFragmentManager().findFragmentById(R.id.layout);
            Fragment fragment = new WebViewFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.layout, fragment)
                    .commit();
                break;
        }
            case R.id.ic_log:
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
                break;
    }
        return super.onOptionsItemSelected(item);
    }
}
