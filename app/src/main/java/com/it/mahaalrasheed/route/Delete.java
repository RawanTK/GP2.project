package com.it.mahaalrasheed.route;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Delete extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        final Button delete_bus_station=(Button)findViewById(R.id.button10);
        final Button delete_metro_station=(Button)findViewById(R.id.button11);
        final Button delete_land_mark=(Button)findViewById(R.id.button12);


        delete_bus_station.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),DeleteBusStation.class);
                startActivity(i);
            }
        });

        delete_metro_station.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), DeleteMetroStation.class);
                startActivity(i);
            }
        });

        delete_land_mark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), DeleteLandMark.class);
                startActivity(i);
            }
        });
    }
}
