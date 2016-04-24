package com.bankfinder.chathurangasandun.bankfinder;


import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bankfinder.chathurangasandun.bankfinder.model.Bank;
import com.bankfinder.chathurangasandun.bankfinder.model.Branches;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MapFragment extends Fragment implements OnMapReadyCallback,View.OnClickListener {


    View v;
    GoogleMap mMap;
    //DatabaseHelper helper;
    double myLocationLat, myLocationLong;
    double nearestLat,nearsetLong;
    Marker myMarker;
    Button btSetpath;
    String locationID = "";
    PolylineOptions lineOptions = null;
    String bankName;


    TextView tvSlideupdwn;

    SlidingUpPanelLayout slidingLayout;
    private ArrayList<Branches> branchesList;

    public MapFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v =inflater.inflate(R.layout.fragment_map, container, false);


        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        initComponents();
        //helper = new DatabaseHelper(getActivity());
        //getNearestBranches();

        //btSetpath = (Button) v.findViewById(R.id.btSetpath);
        // btSetpath.setOnClickListener(this);

        slidingLayout = (SlidingUpPanelLayout)v.findViewById(R.id.sliding_layout);
        slidingLayout.setPanelHeight(40);
        slidingLayout.setShadowHeight(10);
        slidingLayout.setPanelSlideListener(onSlideListener());

        DatabaseOpenHelper db =new DatabaseOpenHelper(getContext());
        branchesList = db.getBankBranches(Bank.selectedBank);






        return v;
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMyLocationEnabled(true);
        mMap.setOnMyLocationChangeListener(myLocationChangeListener);
        setMarkers();
        //getNearestBranch();


    }

    private void setMarkers() {

        for (Branches branch:branchesList){
            LatLng branchLocaiton = new LatLng(branch.getLatitude(), branch.getLongtitude());
            mMap.addMarker(new MarkerOptions().position(branchLocaiton).title(branch.getName()));
        }

    }

    private GoogleMap.OnMyLocationChangeListener myLocationChangeListener = new GoogleMap.OnMyLocationChangeListener() {
        @Override
        public void onMyLocationChange(Location location) {
            myLocationLat = location.getLatitude();
            myLocationLong = location.getLongitude();

            if(myMarker != null){
                myMarker.remove();
            }

            LatLng loc = new LatLng(location.getLatitude(), location.getLongitude());
            myMarker = mMap.addMarker(new MarkerOptions()
                    .position(loc)
                    .snippet(
                            "Lat:" + location.getLatitude() + "Lng:"
                                    + location.getLongitude())
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_man))
                    .title("My Locaiton"));
            if(mMap != null){
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(loc, 12.0f));
            }
        }
    };


    @Override
    public void onClick(View view) {

        // Branch b = helper.getBranch(branchCode);


        /*switch (view.getId()){
            case R.id.btSetpath:
                //getNearestBranch();
                clearMap();

                getPath(new LatLng(myLocationLat,myLocationLong),new LatLng(nearestLat,nearsetLong));

                break;


        }*/

    }

    private void initComponents() {
        tvSlideupdwn = (TextView) v.findViewById( R.id.tvSlideupdown);
    }


    private SlidingUpPanelLayout.PanelSlideListener onSlideListener() {
        return new SlidingUpPanelLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View view, float v) {
                tvSlideupdwn.setText("Slide Up");
            }

            @Override
            public void onPanelCollapsed(View view) {
                tvSlideupdwn.setText("Slide Up");
            }

            @Override
            public void onPanelExpanded(View view) {
                tvSlideupdwn.setText("Slide Down");
            }

            @Override
            public void onPanelAnchored(View view) {

            }

            @Override
            public void onPanelHidden(View view) {

            }
        };
    }
}
