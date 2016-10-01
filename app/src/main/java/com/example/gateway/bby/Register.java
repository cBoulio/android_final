package com.example.gateway.bby;

import android.content.Intent;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register extends AppCompatActivity {


    Pattern pattern;
    Matcher matcher;
    final String USERNAME_PATTERN = "^[a-z0-9_-]{3,15}$";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText userName = (EditText) findViewById(R.id.etUsername);
        final EditText password = (EditText) findViewById(R.id.etPassword);
        final EditText confirmPassword = (EditText) findViewById(R.id.etConfirmPassword);
        final EditText email = (EditText) findViewById(R.id.etEmail);
        final EditText confirmEmail = (EditText) findViewById(R.id.etConfirmEmail);
        final Spinner gender = (Spinner) findViewById(R.id.genderSpinner);
        final Spinner month = (Spinner) findViewById(R.id.monthSpinner);
        Spinner day = (Spinner) findViewById(R.id.daySpinner);
        Spinner year = (Spinner) findViewById(R.id.yearSpinner);
        final Spinner ethnicity = (Spinner) findViewById(R.id.ethnicitySpinner);
        final Spinner country = (Spinner) findViewById(R.id.countrySpinner);
        final CheckBox tos = (CheckBox) findViewById(R.id.cbTos);
        final Button continueButton1 = (Button) findViewById(R.id.registerButton);


        List<String> daySpinnerData = new ArrayList<String>();
        for(int x = 1; x<32;x++) {
            daySpinnerData.add("" + x);
        }
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, daySpinnerData);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        day.setAdapter(spinnerArrayAdapter);


        List<String> yearSpinnerData = new ArrayList<String>();
        for(int x =1970; x<2016; x++){
            yearSpinnerData.add(""+x);
        }
        ArrayAdapter<String> spinnerArrayAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, yearSpinnerData);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        year.setAdapter(spinnerArrayAdapter2);

        continueButton1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                final String regUsername = userName.getText().toString();
                final String regPassword = password.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success) {
                                Intent intent = new Intent(Register.this, LoginActivity.class);
                                Register.this.startActivity(intent);
                            } else {

                                AlertDialog.Builder builder = new AlertDialog.Builder(Register.this);
                                builder.setMessage("Register failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                RegisterRequest registerRequest = new RegisterRequest(regUsername,regPassword, responseListener);
                RequestQueue queue = Volley.newRequestQueue(Register.this);
                queue.add(registerRequest);
            }
        });
    }






}
