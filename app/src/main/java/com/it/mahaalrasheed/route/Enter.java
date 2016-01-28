package com.it.mahaalrasheed.route;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Enter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);

        final Button enter_bus_station=(Button)findViewById(R.id.button6);
        final Button enter_metro_station=(Button)findViewById(R.id.button7);
        final Button enter_land_mark=(Button)findViewById(R.id.button8);


        enter_bus_station.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),EnterBusStation.class);
                startActivity(i);
            }
        });

        enter_metro_station.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), EnterMetroStation.class);
                startActivity(i);
            }
        });

        enter_land_mark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), EnterLandMark.class);
                startActivity(i);
            }
        });

    }
}
