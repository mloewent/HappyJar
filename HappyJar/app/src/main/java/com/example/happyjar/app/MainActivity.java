package com.example.happyjar.app;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.HashSet;
import java.util.Set;

public class MainActivity extends ActionBarActivity {

    JarModel jar;
    Button addThought;
    Button getThought;
    TextView thoughtText;
    TextView dateText;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        jar = new JarModel();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addThought = (Button)findViewById(R.id.addThoughtButton);
        getThought = (Button)findViewById(R.id.getThoughtButton);
        thoughtText = (TextView)findViewById(R.id.thoughtTextView);
        dateText = (TextView)findViewById(R.id.thoughtDateView);

        prefs = this.getSharedPreferences("com.example.happyjar.app", Context.MODE_PRIVATE);
        Set<String> saved = new HashSet<String>();
        jar.addAllThoughts(prefs.getStringSet("1", saved));

        addThought.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                addThought();
            }
        });

        getThought.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                getThought();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void getThought()
    {
        Thought t = jar.getThought();
        if (t != null)
        {
            thoughtText.setText(t.getText());
            dateText.setText(t.getDate());
        }
        prefs.edit().putStringSet("1", jar.getAllThoughts()).commit();
    }

    public void addThought()
    {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        final EditText message;
        message = new EditText(this);
        message.setText(":}");
        message.setPadding(10, 5, 10, 5);

        builder1.setMessage("Drop a happy thought: ");
        builder1.setCancelable(true);
        builder1.setView(message);
        builder1.setPositiveButton("Insert Happy",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        jar.addThought(new Thought(message.getText().toString());
                        prefs.edit().putStringSet("1", jar.getAllThoughts()).commit();
                    }
                });
        builder1.setNegativeButton("I'm not happy",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });


        AlertDialog alert11 = builder1.create();
        alert11.show();
    }


}
