package com.example.hp.jagrukaamadmi;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderApi;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;


public class Dashboard extends FragmentActivity implements OnMapReadyCallback {

    //GoogleMap map;
    //SupportMapFragment mapFragment;
    //SearchView searchView;
    Location currentlocation;
    FusedLocationProviderClient fusedLocationProviderClient;
    Button callsearch;

    private static final int REQUEST_CODE = 101;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        fetchlastlocation();

        callsearch = findViewById(R.id.button2);

        callsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dashboard.this, search.class);
                startActivity(intent);
            }
        });


      //  searchView = findViewById(R.id.sv_location);
       // mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.google_map);

        //searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            //@Override
          //  public boolean onQueryTextSubmit(String s) {
            //    String location = searchView.getQuery().toString();
              //  List<Address> addressList = null;

                //if (location != null || !location.equals("")) {

                  //  Geocoder geocoder = new Geocoder(Dashboard.this);

                    //try {

                      //  addressList = geocoder.getFromLocationName(location, 1);

                    //} catch (IOException e) {

                      //  e.printStackTrace();
                    //}

                    //Address address = addressList.get(0);
                    //LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                    //map.addMarker(new MarkerOptions().position(latLng).title(location));
                    //map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));


                //}



           // }

           // @Override
           // public boolean onQueryTextChange(String s) {
               /// return false;
            }
       // });


       // mapFragment.getMapAsync(this);
   // }

    private void fetchlastlocation() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

          ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_CODE);
            return;
        }
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location!=null){
                    currentlocation = location;
                    Toast.makeText(getApplicationContext(), currentlocation.getLatitude() +" "+currentlocation.getLongitude(), Toast.LENGTH_SHORT).show();
                SupportMapFragment supportMapFragment =(SupportMapFragment)
                        getSupportFragmentManager().findFragmentById(R.id.google_map);
            supportMapFragment.getMapAsync(Dashboard.this);

                }
            }
        });

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
  //map = googleMap;

  LatLng latLng = new LatLng(currentlocation.getLatitude(),currentlocation.getLongitude());
  MarkerOptions markerOptions = new MarkerOptions().position(latLng)
          .title("Hii I Am Here");
   googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
   googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,5));

   googleMap.addMarker(markerOptions);



    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode)
        {
            case REQUEST_CODE:
                if (grantResults.length > 0  && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    fetchlastlocation();
                }
                break;
        }
    }
}
