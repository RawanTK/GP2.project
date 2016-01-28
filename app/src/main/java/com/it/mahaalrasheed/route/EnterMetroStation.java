package com.it.mahaalrasheed.route;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class EnterMetroStation extends AppCompatActivity {
    String s,n,c;
    public static final String ROOT_URL = "http://192.168.1.69/";
    EditText station,name,coor;
    Spinner MetroLine;
    List<String> spin = new ArrayList<String>();
    String adminId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_metro_station);
        spin.add(" ");

        Button enter=(Button)findViewById(R.id.button18);
        MetroLine=(Spinner)findViewById(R.id.MetroLine);
        station =(EditText) findViewById(R.id.metroId);
        name =(EditText) findViewById(R.id.metroName);
        coor =(EditText) findViewById(R.id.metroCoordinates);
        Retrieve();
        //adminId= getIntent().getExtras().getString("AdminID");
        //Toast.makeText(getApplicationContext(), adminId , Toast.LENGTH_LONG).show();

        enter.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                s = station.getText().toString();
                n = name.getText().toString();
                c = coor.getText().toString();

                if (s.equals("") || n.equals("") || c.equals("")) {

                    if (station.getText().toString().equals("")) {
                        station.setError("Please fill station ID");
                    }
                    if (name.getText().toString().equals("")) {
                        name.setError("Please fill the name");
                    }
                    if (coor.getText().toString().equals("")) {
                        coor.setError("Please fill the coordination");
                    }
                } else {

                    coor.setError(null);
                    station.setError(null);
                    name.setError(null);

                    new AlertDialog.Builder(EnterMetroStation.this)
                            .setMessage("are you sure you want to continue the  entering process ?")
                            .setPositiveButton("Enter", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    EnterMetroStation();
                                    finish();
                                    Toast.makeText(getApplicationContext(), "your metro station Entered successfully", Toast.LENGTH_LONG).show();


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

   private void EnterMetroStation(){
        //Here we will handle the http request to insert user to mysql db
        //Creating a RestAdapter
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL) //Setting the Root URL
                .build(); //Finally building the adapter

        //Creating object for our interface
        routeAPI api = adapter.create(routeAPI.class);

        String LocationID = "1"+ MetroLine.getSelectedItemPosition()+station.getText().toString();

        //Defining the method insertuser of our interface
        api.EnterMetroStation(

                //Passing the values by getting it from editTexts
                LocationID,
                coor.getText().toString(),
                name.getText().toString(),
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

                            Toast.makeText(EnterMetroStation.this, output, Toast.LENGTH_LONG).show();


                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        //Displaying the output as a toast
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        //If any error occured displaying the error as toast
                        Toast.makeText(EnterMetroStation.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    private void Retrieve(){
        //Here we will handle the http request to insert user to mysql db
        //Creating a RestAdapter
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL) //Setting the Root URL
                .build(); //Finally building the adapter

        //Creating object for our interface
        routeAPI api = adapter.create(routeAPI.class);
        //Defining the method insertuser of our interface
        api.Retrieve(

                //Passing the values by getting it from editTexts
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

                            while(!output.equals("")) {
                                Toast.makeText(EnterMetroStation.this, output, Toast.LENGTH_LONG).show();
                                String color = output.substring(0, output.indexOf(" "));
                                output = output.substring(output.indexOf(" ") + 1);
                                if (!spin.contains(color)) {
                                    spin.add(color);
                                }
                            }

                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(EnterMetroStation.this,android.R.layout.simple_dropdown_item_1line, spin);

                            MetroLine.setAdapter(adapter);

                            Toast.makeText(EnterMetroStation.this, output, Toast.LENGTH_LONG).show();


                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        //Displaying the output as a toast
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        //If any error occured displaying the error as toast
                        Toast.makeText(EnterMetroStation.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

}
