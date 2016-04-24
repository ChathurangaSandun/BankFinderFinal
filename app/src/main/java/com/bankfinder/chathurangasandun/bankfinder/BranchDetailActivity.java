package com.bankfinder.chathurangasandun.bankfinder;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bankfinder.chathurangasandun.bankfinder.model.Bank;
import com.bankfinder.chathurangasandun.bankfinder.model.Branches;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


public class BranchDetailActivity extends AppCompatActivity implements OnMapReadyCallback {

    int selectedbranchID;
    Branches selectedBranch;
    GoogleMap branchMap;
    private GoogleMap mMap;


    TextView tvAddress,tvtp,tvWeekOpne,tvWeekClose,tvSatOpen,tvSAtClose;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branch_detail);

        Bundle extras = getIntent().getExtras();
        selectedbranchID = extras.getInt("SELECTEDBRANCH");
        Log.d("details",String.valueOf(selectedbranchID));



        DatabaseOpenHelper db =new DatabaseOpenHelper(getApplicationContext());
        selectedBranch = db.getBranchDetail(selectedbranchID).get(0);


        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(selectedBranch.getName());

        loadBackdrop();



       SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mmap);
        mapFragment.getMapAsync(this);




        tvAddress = (TextView) findViewById(R.id.tvAddress);
        tvtp= (TextView) findViewById(R.id.tvTelephone);
        tvWeekOpne= (TextView) findViewById(R.id.tvWeekdaysOpen);
        tvWeekClose= (TextView) findViewById(R.id.tvWeekdaysclose);
        tvSatOpen= (TextView) findViewById(R.id.tvSatdaysOpen);
        tvSAtClose= (TextView) findViewById(R.id.tvSatdaysClose);

        tvAddress.setText(selectedBranch.getAddress());
        tvtp.setText(selectedBranch.getTp());
        tvWeekOpne.setText(selectedBranch.getWeekOpen());
        tvWeekClose.setText(selectedBranch.getWeekClose());
        tvSatOpen.setText(selectedBranch.getSatOpen());
        tvSAtClose.setText(selectedBranch.getSatClose());


        FloatingActionButton toMapfab = (FloatingActionButton) findViewById(R.id.fabToMap);
        toMapfab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                HomeFragment mapFragment = new HomeFragment();
                fragmentTransaction.replace(R.id.container, mapFragment);
                fragmentTransaction.commit();

            }
        });



    }

    private void loadBackdrop() {
        //
        //
        // final ImageView imageView = (ImageView) findViewById(R.id.backdrop);
        //Glide.with(this).load(true).centerCrop().into(imageView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {

       mMap = googleMap;


        // Add a marker in Sydney, Australia, and move the camera.
        LatLng branch = new LatLng(selectedBranch.getLatitude(), selectedBranch.getLongtitude());
        mMap.addMarker(new MarkerOptions().position(branch).title(selectedBranch.getName()));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(branch,15));



    }



}
