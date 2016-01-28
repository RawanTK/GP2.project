package com.it.mahaalrasheed.route;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class EnterLandMark extends AppCompatActivity {

    public static final String ROOT_URL = "http://192.168.1.69/";
    EditText category, name, coord;
    String n, c, co;
    int LandmarkID = 1;
    String adminId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_land_mark);

        Button enter = (Button) findViewById(R.id.button16);
        name = (EditText) findViewById(R.id.editText8);
        category = (EditText) findViewById(R.id.editText2);
        coord = (EditText) findViewById(R.id.editText10);
       // adminId= getIntent().getExtras().getString("ID");

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                n = name.getText().toString();
                c = category.getText().toString();
                co = coord.getText().toString();

                if (n.equals("") || c.equals("") || co.equals("")) {

                    if (name.getText().toString().equals("")) {
                        name.setError("Please fill the name");
                    }
                    if (category.getText().toString().equals("")) {
                        category.setError("Please fill the category");
                    }
                    if (coord.getText().toString().equals("")) {
                        coord.setError("Please fill the coordination");
                    }


                } else {

                    coord.setError(null);
                    category.setError(null);
                    name.setError(null);


                    new AlertDialog.Builder(EnterLandMark.this)
                            .setMessage("are you sure you want to continue the  entering process ?")
                            .setPositiveButton("Enter", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    EnterLandmark();
                                    finish();


                                }
                            })

                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            })
                            .show();
                }
            }
        });

    }

    private void EnterLandmark() {
        //Here we will handle the http request to insert user to mysql db
        //Creating a RestAdapter
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL) //Setting the Root URL
                .build(); //Finally building the adapter

        //Creating object for our interface
        routeAPI api = adapter.create(routeAPI.class);

        String LocationID = "0." + "0." + LandmarkID++;

        //Defining the method insertuser of our interface
        api.EnterLandmark(

                //Passing the values by getting it from editTexts
                LocationID,
                coord.getText().toString(),
                name.getText().toString(),
                category.getText().toString(),
                "1",


                //Creating an anonymous callback
                new Callback<Response>() {
                    @Override
                    public void success(Response result, Response response) {
                        //On success we will read the server's output using bufferedreader
                        //Creating a bufferedreader object
                        BufferedReader reader = null;

                        //An string to store output from the server
                        String output = "";

                        try {
                            //Initializing buffered reader
                            reader = new BufferedReader(new InputStreamReader(result.getBody().in()));

                            //Reading the output in the string
                            output = reader.readLine();

                            Toast.makeText(EnterLandMark.this, output, Toast.LENGTH_LONG).show();


                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        //Displaying the output as a toast
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        //If any error occured displaying the error as toast
                        Toast.makeText(EnterLandMark.this,error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

}

