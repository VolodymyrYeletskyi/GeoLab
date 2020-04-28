package com.example.geolab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;


import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;


public class MainActivity extends AppCompatActivity implements LocationListener{

    Button btn;
    TextView tvSorry, tvtLat, tvtLon, tvvLat, tvvLon;
    Location location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.button);

        tvSorry = findViewById(R.id.tvSorry);
        tvtLat = findViewById(R.id.tvtLat);
        tvtLon = findViewById(R.id.tvtLon);
        tvvLat = findViewById(R.id.tvvLat);
        tvvLon = findViewById(R.id.tvvLon);

        tvSorry.setVisibility(View.INVISIBLE);
        tvtLat.setVisibility(View.INVISIBLE);
        tvtLon.setVisibility(View.INVISIBLE);
        tvvLat.setVisibility(View.INVISIBLE);
        tvvLon.setVisibility(View.INVISIBLE);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tvSorry.setVisibility(View.INVISIBLE);
                tvtLat.setVisibility(View.INVISIBLE);
                tvtLon.setVisibility(View.INVISIBLE);
                tvvLat.setVisibility(View.INVISIBLE);
                tvvLon.setVisibility(View.INVISIBLE);

                LocationManager locationManager = (LocationManager)
                        MainActivity.this.getSystemService(Context.LOCATION_SERVICE);
                try {

                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);
                    TimeUnit.SECONDS.sleep(5);
                }
                catch (InterruptedException e) {}



                if(ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
                {


                    locationManager.requestLocationUpdates(
                            LocationManager.GPS_PROVIDER,
                            5000,
                            10,
                            MainActivity.this);
                    location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

                    tvvLat.setText(Double.toString(location.getLatitude()));
                    tvvLon.setText(Double.toString(location.getLongitude()));

                    tvtLat.setVisibility(View.VISIBLE);
                    tvtLon.setVisibility(View.VISIBLE);
                    tvvLat.setVisibility(View.VISIBLE);
                    tvvLon.setVisibility(View.VISIBLE);
                }
                else
                {
                    tvSorry.setVisibility(View.VISIBLE);
                }

            }
        });
    }

    @Override
    public void onLocationChanged(Location loc) {
        location = loc;
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
