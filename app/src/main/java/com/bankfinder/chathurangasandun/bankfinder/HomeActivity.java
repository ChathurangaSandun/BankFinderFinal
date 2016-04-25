package com.bankfinder.chathurangasandun.bankfinder;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.bankfinder.chathurangasandun.bankfinder.model.Bank;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.mikepenz.materialdrawer.model.interfaces.Nameable;

public class HomeActivity extends AppCompatActivity {
    private static final int PROFILE_SETTING = 1;
    AccountHeader headerDrawer;
    Drawer drawer;

    final String LOG_TAG = "HomeActivity";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(!isConnected(getApplicationContext())) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("No Internet Connection");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User clicked OK button
                }
            });


            builder.show();
        }






        //defualt fragment

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        HomeFragment homeFragment = new HomeFragment();
        fragmentTransaction.replace(R.id.container, homeFragment);
        fragmentTransaction.commit();



        //create navigation drawer

         headerDrawer = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.header)
                .addProfiles(
                        new ProfileDrawerItem().withName(Bank.allBanksnames[0]).withNameShown(true).withEmail("").withIcon(getResources().getDrawable(R.drawable.ic_bank)),
                        new ProfileDrawerItem().withName(Bank.allBanksnames[1]).withNameShown(true).withEmail("").withIcon(getResources().getDrawable(R.drawable.ic_bank)),
                        new ProfileDrawerItem().withName(Bank.allBanksnames[2]).withNameShown(true).withEmail("").withIcon(getResources().getDrawable(R.drawable.ic_bank)),
                        new ProfileDrawerItem().withName(Bank.allBanksnames[3]).withNameShown(true).withEmail("").withIcon(getResources().getDrawable(R.drawable.ic_bank)),
                        new ProfileDrawerItem().withName(Bank.allBanksnames[4]).withNameShown(true).withEmail("").withIcon(getResources().getDrawable(R.drawable.ic_bank)),
                        new ProfileDrawerItem().withName(Bank.allBanksnames[5]).withNameShown(true).withEmail("").withIcon(getResources().getDrawable(R.drawable.ic_bank)),
                        new ProfileDrawerItem().withName(Bank.allBanksnames[6]).withNameShown(true).withEmail("").withIcon(getResources().getDrawable(R.drawable.ic_bank)),
                        new ProfileDrawerItem().withName(Bank.allBanksnames[7]).withNameShown(true).withEmail("").withIcon(getResources().getDrawable(R.drawable.ic_bank)),
                        new ProfileDrawerItem().withName(Bank.allBanksnames[8]).withNameShown(true).withEmail("").withIcon(getResources().getDrawable(R.drawable.ic_bank)),
                        new ProfileDrawerItem().withName(Bank.allBanksnames[9]).withNameShown(true).withEmail("").withIcon(getResources().getDrawable(R.drawable.ic_bank)),
                        new ProfileDrawerItem().withName(Bank.allBanksnames[10]).withNameShown(true).withEmail("").withIcon(getResources().getDrawable(R.drawable.ic_bank)),
                        new ProfileDrawerItem().withName(Bank.allBanksnames[11]).withNameShown(true).withEmail("").withIcon(getResources().getDrawable(R.drawable.ic_bank)),
                        new ProfileDrawerItem().withName(Bank.allBanksnames[12]).withNameShown(true).withEmail("").withIcon(getResources().getDrawable(R.drawable.ic_bank)),
                        new ProfileDrawerItem().withName(Bank.allBanksnames[13]).withNameShown(true).withEmail("").withIcon(getResources().getDrawable(R.drawable.ic_bank)),
                        new ProfileDrawerItem().withName(Bank.allBanksnames[14]).withNameShown(true).withEmail("").withIcon(getResources().getDrawable(R.drawable.ic_bank)),
                        new ProfileDrawerItem().withName(Bank.allBanksnames[15]).withNameShown(true).withEmail("").withIcon(getResources().getDrawable(R.drawable.ic_bank)),
                        new ProfileDrawerItem().withName(Bank.allBanksnames[16]).withNameShown(true).withEmail("").withIcon(getResources().getDrawable(R.drawable.ic_bank)),
                        new ProfileDrawerItem().withName(Bank.allBanksnames[17]).withNameShown(true).withEmail("").withIcon(getResources().getDrawable(R.drawable.ic_bank)),
                        new ProfileDrawerItem().withName(Bank.allBanksnames[18]).withNameShown(true).withEmail("").withIcon(getResources().getDrawable(R.drawable.ic_bank)),
                        new ProfileDrawerItem().withName(Bank.allBanksnames[19]).withNameShown(true).withEmail("").withIcon(getResources().getDrawable(R.drawable.ic_bank)),
                        new ProfileDrawerItem().withName(Bank.allBanksnames[20]).withNameShown(true).withEmail("").withIcon(getResources().getDrawable(R.drawable.ic_bank))
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {

                        Log.d("profile", profile.getName().toString());
                        String profileName = profile.getName().toString();

                        Bank.selectedBank = profileName;



                        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        HomeFragment homeFragment = new HomeFragment();
                        fragmentTransaction.replace(R.id.container, homeFragment);
                        fragmentTransaction.commit();


                        return false;
                    }
                })
                 .build();

        //if you want to update the items at a later time it is recommended to keep it in a variable
        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withName("Home").withDescription("Brief Detail about Bank").withDescriptionTextColorRes(R.color.discriptionGray).withIcon(getResources().getDrawable(R.drawable.ic_home));
        PrimaryDrawerItem item2 = new PrimaryDrawerItem().withName("Branch Finder").withDescription("Branch List").withDescriptionTextColorRes(R.color.discriptionGray).withIcon(getResources().getDrawable(R.drawable.ic_bank));
        PrimaryDrawerItem item3 = new PrimaryDrawerItem().withName("ATM Finder").withDescription("ATM List").withDescriptionTextColorRes(R.color.discriptionGray).withDescriptionTextColorRes(R.color.discriptionGray).withIcon(getResources().getDrawable(R.drawable.ic_atm));
        PrimaryDrawerItem item4 = new PrimaryDrawerItem().withName("Nearest ATM and Branch").withDescription("View nearest ATM & Branch").withDescriptionTextColorRes(R.color.discriptionGray).withIcon(getResources().getDrawable(R.drawable.ic_map));
        PrimaryDrawerItem item5 = new PrimaryDrawerItem().withName("Official Web Site").withDescription("View their official site").withDescriptionTextColorRes(R.color.discriptionGray).withIcon(getResources().getDrawable(R.drawable.ic_webview));





//create the drawer and remember the `Drawer` result object
        drawer = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withAccountHeader(headerDrawer)

                .addDrawerItems(
                        item1,
                        item2,
                        item3,
                        item4,
                        item5
                )

                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {

                        if (drawerItem != null) {
                            String selectedItem = ((Nameable) drawerItem).getName().getText(HomeActivity.this);
                            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                            if ("Home".equals(selectedItem)) {
                                Log.d(LOG_TAG, "Home");
                                HomeFragment homeFragment = new HomeFragment();
                                fragmentTransaction.replace(R.id.container, homeFragment);
                            }if ("Branch Finder".equals(selectedItem)){
                                Log.d(LOG_TAG, "Branch Finder");
                                BranchFragment branchFragment = new BranchFragment();
                                fragmentTransaction.replace(R.id.container, branchFragment);
                            }else if ("ATM Finder".equals(selectedItem)) {
                                Log.d(LOG_TAG, "ATM Finder");
                                ATMFragment atmFragment = new ATMFragment();
                                fragmentTransaction.replace(R.id.container, atmFragment);
                            } else if ("Nearest ATM and Branch".equals(selectedItem)) {
                                Log.d(LOG_TAG, "Nearest ATM and Branch");
                                MapFragment mapFragment = new MapFragment();
                                fragmentTransaction.replace(R.id.container, mapFragment);
                            }else if("Check Update".equals(selectedItem)){
                                Log.d(LOG_TAG, "Check Update");
                            }else if("Developer".equals(selectedItem)){
                                Log.d(LOG_TAG, "Developer");
                                Intent i = new Intent(getApplication(),MyProfile.class);
                                startActivity(i);
                            }else if("GitHub".equals(selectedItem)){
                                Log.d(LOG_TAG, "GitHub");
                            }else if("Official Web Site".equals(selectedItem)){
                                Log.d(LOG_TAG, "Official Web Site");
                            }
                            fragmentTransaction.commit();
                        }
                        return false;
                    }
                })
                .build();


        drawer.addStickyFooterItem(new PrimaryDrawerItem().withName("Check Update").withIcon(getResources().getDrawable(R.drawable.ic_update)));

        drawer.addStickyFooterItem(new DividerDrawerItem());
        drawer.addStickyFooterItem(new PrimaryDrawerItem().withName("Developer").withIcon(getResources().getDrawable(R.drawable.developer)));

        drawer.addStickyFooterItem(new DividerDrawerItem());
        //drawer.addStickyFooterItem(new PrimaryDrawerItem().withName("GitHub").withIcon(getResources().getDrawable(R.drawable.ic_github)));


        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        drawer.getActionBarDrawerToggle().setDrawerIndicatorEnabled(true);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean isConnected(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = cm.getActiveNetworkInfo();

        if (netinfo != null && netinfo.isConnectedOrConnecting()) {
            android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if((mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting())) return true;
            else return false;
        } else return false;
    }


}
