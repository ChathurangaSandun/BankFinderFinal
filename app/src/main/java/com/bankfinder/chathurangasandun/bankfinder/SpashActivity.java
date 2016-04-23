package com.bankfinder.chathurangasandun.bankfinder;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.bankfinder.chathurangasandun.bankfinder.model.Branches;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class SpashActivity extends AppCompatActivity {
    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private static final boolean AUTO_HIDE = true;
    private final String SERVER_URL = "http://192.168.56.1/bank/getBankList.php";

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;
    private static int SPLASH_TIME_OUT = 5000;

    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */
    private static final int UI_ANIMATION_DELAY = 300;
    private final Handler mHideHandler = new Handler();
    private View mContentView;
    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            // Delayed removal of status and navigation bar

            // Note that some of these constants are new as of API 16 (Jelly Bean)
            // and API 19 (KitKat). It is safe to use them, as they are inlined
            // at compile-time and do nothing on earlier devices.
            mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    };

    private View mControlsView;
    private final Runnable mShowPart2Runnable = new Runnable() {
        @Override
        public void run() {
            hide();
            // Delayed display of UI elements
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.hide();
            }
            mControlsView.setVisibility(View.VISIBLE);
        }
    };
    private boolean mVisible;
    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };
    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */
    private final View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (AUTO_HIDE) {
                delayedHide(AUTO_HIDE_DELAY_MILLIS);
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_spash);

        mVisible = true;
        mControlsView = findViewById(R.id.fullscreen_content_controls);
        mContentView = findViewById(R.id.fullscreen_content);
        //create database
        DatabaseOpenHelper db =new DatabaseOpenHelper(this);


        // Set up the user interaction to manually show or hide the system UI.
        mContentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hide();
            }
        });


        SharedPreferences databasecreate = getSharedPreferences("databasecreate",MODE_PRIVATE);
        final String DBVERSION = "dbversion";
        int currentVersion = databasecreate.getInt(DBVERSION, 0);
        Log.d("dbversions", String.valueOf(currentVersion));
        SharedPreferences.Editor edit = databasecreate.edit();

        //TODO : remove comment
       /* if(currentVersion == 0){
            accessServer();
        }*/

        db.truncateTable("branches");

        ArrayList<Branches> branchesArrayList = accessServer();
        for (Branches b: branchesArrayList) {
            db.addBranch(b);

        }

        //edit.putInt(DBVERSION,(currentVersion+1));
        //edit.commit();









        db.addBranch(new Branches(001,"Amana Bank PLC","Main Branch",6.905825,79.851470,"480, Galle Road,Colombo 3","011-7756000","0800", "1500", "0900", "1500"));

        db.addBranch(new Branches(002,"Amana Bank PLC","Pettah",6.937672,79.851277,"129, Main Street, Colombo 11","011-7756000","0800", "1500", "0900", "1500"));
        db.addBranch(new Branches(003,"Amana Bank PLC","Kandy",7.2955357,80.6355777,"480, Galle Road,Colombo 3","011-7756000","0800", "1500", "0900", "1500"));

        db.addBranch(new Branches(004,"Amana Bank PLC","Kattankudy",7.6836273,81.7246413,"480, Galle Road,Colombo 3","011-7756000","0800", "1500", "0900", "1500"));

        db.addBranch(new Branches(005,"Amana Bank PLC","Dehiwala",6.8618673,79.8615613,"28, Galle Road,Dehiwala","011-7756000","0800", "1500", "0900", "1500"));















        // Upon interacting with UI controls, delay any scheduled hide()
        // operations to prevent the jarring behavior of controls going away
        // while interacting with the UI.

        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity

                //set to








                Intent i = new Intent(SpashActivity.this, HomeActivity.class);
                startActivity(i);

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);







    }

    private ArrayList<Branches> accessServer() {
        //getData from server

        final ArrayList<Branches> branchesArrayList  = new ArrayList<>();

        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(SERVER_URL,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {

                        for(int i=0;i<response.length();i++){
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                int id = jsonObject.getInt("id");
                                //Log.d("serverjsonid",id+"" );
                                String bank = jsonObject.getString("bank");
                                String name = jsonObject.getString("name");
                                double latitude = jsonObject.getDouble("latitude");
                                double longtitude = jsonObject.getDouble("longtitude");
                                String address = jsonObject.getString("address");
                                String tp = jsonObject.getString("tp");
                                String weekopen = jsonObject.getString("weekopen");
                                String weekclose = jsonObject.getString("weekclose");
                                String satopen = jsonObject.getString("satopen");
                                String satclose = jsonObject.getString("satclose");


                                branchesArrayList.add(new Branches(id,bank,name,latitude,longtitude,address,tp,weekopen,weekclose,satopen,satclose));


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                Log.d("serverjsonid",error.getMessage() );
            }
        });

        queue.add(jsonArrayRequest);

        return branchesArrayList;
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(100);
    }

    private void toggle() {
        if (mVisible) {
            hide();
        } else {
            show();
        }
    }

    private void hide() {
        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        mControlsView.setVisibility(View.GONE);
        mVisible = false;

        // Schedule a runnable to remove the status and navigation bar after a delay
        mHideHandler.removeCallbacks(mShowPart2Runnable);
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY);
    }

    @SuppressLint("InlinedApi")
    private void show() {
        // Show the system bar
        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        mVisible = true;

        // Schedule a runnable to display UI elements after a delay
        mHideHandler.removeCallbacks(mHidePart2Runnable);
        mHideHandler.postDelayed(mShowPart2Runnable, UI_ANIMATION_DELAY);
    }

    /**
     * Schedules a call to hide() in [delay] milliseconds, canceling any
     * previously scheduled calls.
     */
    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }
}
