package com.bankfinder.chathurangasandun.bankfinder;


import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.location.Location;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bankfinder.chathurangasandun.bankfinder.JSONParser.DirectionsJSONParser;
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
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;


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

    Map<Branches, Double> treemap = new  HashMap<Branches, Double>();


    TextView tvSlideupdwn,tvTPNearestBranch,tvAddressNearestBranch;

    Branches nearestBranchLocation;
    SlidingUpPanelLayout slidingLayout;
    private ArrayList<Branches> branchesList;
    private Branches nearestBranch;

    boolean isDrawBranchPath = false;

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


        tvAddressNearestBranch = (TextView) v.findViewById(R.id.tvAdressNearestBranch);
        tvTPNearestBranch = (TextView) v.findViewById(R.id.tvTpNearestBranch);


        final FloatingActionButton fab1 = (FloatingActionButton) v.findViewById(R.id.fab1);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(isDrawBranchPath){

                    isDrawBranchPath = false;
                    fab1.setImageResource(R.drawable.ic_bank);
                    fab1.setColorFilter(Color.argb(255, 255, 255, 255));
                    mMap.clear();
                    setMarkers();


                }else {

                    nearestBranchLocation = getNearestBranch();
                    drawPathToBranch();


                    //
                    mMap.clear();
                    LatLng branchLocaiton = new LatLng(nearestBranchLocation.getLatitude(), nearestBranchLocation.getLongtitude());
                    mMap.addMarker(new MarkerOptions().position(branchLocaiton).title(nearestBranchLocation.getName()));

                    getPath(new LatLng(myLocationLat, myLocationLong), new LatLng(nearestBranchLocation.getLatitude(), nearestBranchLocation.getLongtitude()));


                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(myLocationLat, myLocationLong), 14.0f));



                    tvAddressNearestBranch.setText(nearestBranchLocation.getAddress());
                    tvTPNearestBranch.setText(nearestBranchLocation.getTp());




                    fab1.setImageResource(R.drawable.cast_ic_notification_on);
                    isDrawBranchPath = true;
                }



            }
        });

        FloatingActionButton fab2 = (FloatingActionButton) v.findViewById(R.id.fab2);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Click action

            }
        });


        FloatingActionButton fab3 = (FloatingActionButton) v.findViewById(R.id.fab2);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Click action

            }
        });





        return v;
    }

    private void drawPathToBranch() {

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMyLocationEnabled(true);
        mMap.setOnMyLocationChangeListener(myLocationChangeListener);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(myLocationLat, myLocationLong), 12.0f));
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
                //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(loc, 12.0f));
            }

            //nearestBranchLocation = getNearestBranch();
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

    public double distanceFrom(double lat1, double lng1, double lat2, double lng2) {
        double earthRadius = 3958.75;
        double dLat = Math.toRadians(lat2-lat1);
        double dLng = Math.toRadians(lng2 - lng1);
        double a = Math.sin(dLat/2) * Math.sin(dLat/2) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.sin(dLng/2) * Math.sin(dLng/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double dist = earthRadius * c;
        int meterConversion = 1609;
        return new Double(dist * meterConversion).floatValue();    // this will return distance
    }

    public Branches getNearestBranch() {
        for (Branches b: branchesList){
            Log.d("Clivekumara", b.getName());
            double v = distanceFrom(myLocationLat, myLocationLong, b.getLatitude(), b.getLongtitude());

            treemap.put(b,v);
        }

        Map sortedMap = sortByComparator(treemap);
        Set set = sortedMap.keySet();
        Branches[] branches = (Branches[]) set.toArray(new Branches[sortedMap.size()]);



        Log.d("nearest", branches[0].getName());


        return branches[0];
    }


    private static Map<Branches, Double> sortByComparator(Map<Branches, Double> unsortMap) {

        // Convert Map to List
        List<Map.Entry<Branches, Double>> list =
                new LinkedList<Map.Entry<Branches, Double>>(unsortMap.entrySet());

        // Sort list with comparator, to compare the Map values
        Collections.sort(list, new Comparator<Map.Entry<Branches, Double>>() {
            public int compare(Map.Entry<Branches, Double> o1,
                               Map.Entry<Branches, Double> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        // Convert sorted map back to a Map
        Map<Branches, Double> sortedMap = new LinkedHashMap<Branches, Double>();
        for (Iterator<Map.Entry<Branches, Double>> it = list.iterator(); it.hasNext();) {
            Map.Entry<Branches, Double> entry = it.next();
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }


    private void getPath(LatLng origin, LatLng dest) {

        // Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;

        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;

        // Sensor enabled
        String sensor = "sensor=false";

        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + sensor;

        // Output format
        String output = "json";

        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters;


        RequestQueue queue = Volley.newRequestQueue(getActivity());

        //showDialog();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null, new Response.Listener<JSONObject>() {//string cast - null

            @Override
            public void onResponse(JSONObject response) {
                DirectionsJSONParser parser = new DirectionsJSONParser();
                List<List<HashMap<String, String>>> r = parser.parse(response);

                List<LatLng> points = null;

                MarkerOptions markerOptions = new MarkerOptions();

                // Traversing through all the routes
                for (int i = 0; i < r.size(); i++) {
                    points = new ArrayList<LatLng>();
                    lineOptions = new PolylineOptions();

                    // Fetching i-th route
                    List<HashMap<String, String>> path = r.get(i);

                    // Fetching all the points in i-th route
                    for (int j = 0; j < path.size(); j++) {
                        HashMap<String, String> point = path.get(j);

                        double lat = Double.parseDouble(point.get("lat"));
                        double lng = Double.parseDouble(point.get("lng"));
                        LatLng position = new LatLng(lat, lng);

                        points.add(position);
                    }

                    // Adding all the points in the route to LineOptions
                    lineOptions.addAll(points);
                    lineOptions.width(2);
                    lineOptions.color(Color.RED);
                }
                if (mMap != null) {
                    // Drawing polyline in the Google Map for the i-th route

                    mMap.addPolyline(lineOptions);
                }

                //hideDialog();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("APP", "Error: " + error.getMessage());
                Toast.makeText(getActivity(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
                // hide the progress dialog
                //hideDialog();
            }
        });
        // Adding request to request queue
        queue.add(jsonObjReq);
    }


}
