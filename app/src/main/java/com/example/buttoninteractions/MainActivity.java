package com.example.buttoninteractions;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;


public class MainActivity extends AppCompatActivity {

    // Initializing and declaring UI with their respective elements
    RadioButton HP, Lenovo, Microsoft;
    Spinner spinner;
    TextView result, result2;
    Button submit, clear;

    //Initializing string variables in order to store laptop, it's models and the result
    String laptop,model,answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initializing the IDs
        spinner = findViewById(R.id.spinner);
        submit = findViewById(R.id.submit);
        clear = findViewById(R.id.clear);
        result = findViewById(R.id.result);
        result2 = findViewById(R.id.result2);

        //Initializing the IDs for the laptops
        HP = findViewById(R.id.HP);
        Lenovo = findViewById(R.id.Lenovo);
        Microsoft = findViewById(R.id.Microsoft);

        //Initializing the text views to set text for selected laptop and model
        result.setText("");
        result2.setText("");

        //Creating an Arrayadapter
        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(
                        this,
                        R.array.empty,
                        android.R.layout.simple_spinner_item);

        // to showcase the layout within the string
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Initializing the adapter to the spinner
        spinner.setAdapter(adapter);

        // using onClickListener to activate the clear button
        clear.setOnClickListener(view -> {
            spinner.setSelection(0); // Sets the spinner back to default
            result.setText(""); // Clear selected laptop
            result2.setText(""); // Clear selected model
        });

        // using onClickListener to activate the submit button
        submit.setOnClickListener(view -> {
            String spinneroutput = spinner.getSelectedItem().toString(); // Obtains the selected value from the spinner

            //Selecting the laptop according to the user input from radiobuttion
            if (HP.isChecked()) {
                answer = "HP";
            } else if (Lenovo.isChecked()) {
                answer = "Lenovo";
            } else if (Microsoft.isChecked()) {
                answer = "Microsoft";
            }
            laptop = answer;

            String[] modeles;

            //Obtain the array of models according to the chosen laptop type
            if (HP.isChecked()) {
                modeles = getResources().getStringArray(R.array.HP_model);
            } else if (Lenovo.isChecked()) {
                modeles = getResources().getStringArray(R.array.Lenovo_model);
            } else if (Microsoft.isChecked()) {
                modeles = getResources().getStringArray(R.array.Microsoft_model);
            } else {
                modeles = new String[0];
            }

            //checks the model is available in the array of laptops or not
            if (Arrays.asList(modeles).contains(spinneroutput)) {
                model = spinneroutput;
            } else {
                model = "\n model invalid";
            }

            // shows selected laptop and model as an output in given text views
            result.setText("\n Selected laptop:" + laptop );
            result2.setText("\n Selected model:" + model);
        });

    }
}