package com.example.mikechen.ma4;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by mikechen on 10/5/15.
 */
public class Addskin extends Activity {
    private SharedPreferences addsharedpreference;
    private Spinner spinner1;
    private Spinner spinner2;
    private Button btnSubmit;

//    initial some buttons


    protected DBHelper DB = new DBHelper(Addskin.this);
// still need to access DB
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addskin);

        addItemsOnSpinner();
        addListenerOnButton();

    }
    public void addItemsOnSpinner(){
        spinner2 = (Spinner) findViewById(R.id.skillA_spinner);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(
                this, R.array.skillA_array, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);

        spinner1 = (Spinner) findViewById(R.id.skillB_spinner);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(
                this, R.array.skillA_array, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);


    }

    public void addListenerOnButton() {
        spinner2 = (Spinner) findViewById(R.id.skillA_spinner);
        spinner1 = (Spinner) findViewById(R.id.skillB_spinner);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Toast.makeText(Addskin.this,
                        "OnClickListener : " +

                                "\nSpinner 2 : " + String.valueOf(spinner2.getSelectedItem()),
                        Toast.LENGTH_SHORT).show();
                getSkill1();
                getInterest();
            }

        });
    }
    public String getSkill1(){
        Spinner Spinner1=(Spinner) findViewById(R.id.skillA_spinner);

        String text = Spinner1.getSelectedItem().toString();

        return text;
    }
    public  String getInterest(){
        Spinner Spinner2=(Spinner) findViewById(R.id.skillB_spinner);
        String text = Spinner2.getSelectedItem().toString();
        return text;
    }
   }


