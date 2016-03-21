package com.bankfinder.chathurangasandun.bankfinder;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.holder.BadgeStyle;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.mikepenz.materialdrawer.model.interfaces.Nameable;

public class HomeActivity extends AppCompatActivity {
    private static final int PROFILE_SETTING = 1;
    AccountHeader headerDrawer;
    Drawer drawer;

    final String LOG_TAG = "HomeActivity";

    String [] allBanksnames = {
            "Amana Bank PLC",
            "Axis Bank Ltd",
            "Bank of Ceylon",
            "Cargills Bank Ltd",
            "Citibank",
            "Commercial Bank of Ceylon PLC",
            "Deutsche Bank AG",
            "DFCC Bank PLC",
            "Habib Bank Ltd",
            "Hatton National Bank PLC",
            "ICICI Bank Ltd.",
            "Indian Bank",
            "Indian Overseas Bank",
            "MCB Bank Ltd",
            "National Development Bank PLC",
            "Nations Trust Bank PLC",
            "Pan Asia Banking Corporation PLC",
            "People's Bank",
            "Public Bank Berhad",
            "Sampath Bank PLC",
            "Seylan Bank PLC",
            "Standard Chartered Bank",
            "State Bank of India",
            "HSBC",
            "Union Bank of Colombo PLC"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



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
                        new ProfileDrawerItem().withName(allBanksnames[0]).withNameShown(true).withEmail("").withIcon(getResources().getDrawable(R.drawable.amana)),
                        new ProfileDrawerItem().withName(allBanksnames[1]).withNameShown(true).withEmail("").withIcon(getResources().getDrawable(R.drawable.amana)),
                        new ProfileDrawerItem().withName(allBanksnames[2]).withNameShown(true).withEmail("").withIcon(getResources().getDrawable(R.drawable.amana)),
                        new ProfileDrawerItem().withName(allBanksnames[3]).withNameShown(true).withEmail("").withIcon(getResources().getDrawable(R.drawable.amana)),
                        new ProfileDrawerItem().withName(allBanksnames[4]).withNameShown(true).withEmail("").withIcon(getResources().getDrawable(R.drawable.amana)),
                        new ProfileDrawerItem().withName(allBanksnames[5]).withNameShown(true).withEmail("").withIcon(getResources().getDrawable(R.drawable.amana)),
                        new ProfileDrawerItem().withName(allBanksnames[6]).withNameShown(true).withEmail("").withIcon(getResources().getDrawable(R.drawable.amana)),
                        new ProfileDrawerItem().withName(allBanksnames[7]).withNameShown(true).withEmail("").withIcon(getResources().getDrawable(R.drawable.amana)),
                        new ProfileDrawerItem().withName(allBanksnames[8]).withNameShown(true).withEmail("").withIcon(getResources().getDrawable(R.drawable.amana)),
                        new ProfileDrawerItem().withName(allBanksnames[9]).withNameShown(true).withEmail("").withIcon(getResources().getDrawable(R.drawable.amana)),
                        new ProfileDrawerItem().withName(allBanksnames[10]).withNameShown(true).withEmail("").withIcon(getResources().getDrawable(R.drawable.amana)),
                        new ProfileDrawerItem().withName(allBanksnames[11]).withNameShown(true).withEmail("").withIcon(getResources().getDrawable(R.drawable.amana)),
                        new ProfileDrawerItem().withName(allBanksnames[12]).withNameShown(true).withEmail("").withIcon(getResources().getDrawable(R.drawable.amana)),
                        new ProfileDrawerItem().withName(allBanksnames[13]).withNameShown(true).withEmail("").withIcon(getResources().getDrawable(R.drawable.amana)),
                        new ProfileDrawerItem().withName(allBanksnames[14]).withNameShown(true).withEmail("").withIcon(getResources().getDrawable(R.drawable.amana)),
                        new ProfileDrawerItem().withName(allBanksnames[15]).withNameShown(true).withEmail("").withIcon(getResources().getDrawable(R.drawable.amana)),
                        new ProfileDrawerItem().withName(allBanksnames[16]).withNameShown(true).withEmail("").withIcon(getResources().getDrawable(R.drawable.amana)),
                        new ProfileDrawerItem().withName(allBanksnames[17]).withNameShown(true).withEmail("").withIcon(getResources().getDrawable(R.drawable.amana)),
                        new ProfileDrawerItem().withName(allBanksnames[18]).withNameShown(true).withEmail("").withIcon(getResources().getDrawable(R.drawable.amana)),
                        new ProfileDrawerItem().withName(allBanksnames[19]).withNameShown(true).withEmail("").withIcon(getResources().getDrawable(R.drawable.amana)),
                        new ProfileDrawerItem().withName(allBanksnames[20]).withNameShown(true).withEmail("").withIcon(getResources().getDrawable(R.drawable.amana))
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {

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
                                HomeFragment homeFragment = new HomeFragment();
                                fragmentTransaction.replace(R.id.container, homeFragment);
                            }else if ("ATM Finder".equals(selectedItem)) {
                                Log.d(LOG_TAG, "ATM Finder");
                                HomeFragment homeFragment = new HomeFragment();
                                fragmentTransaction.replace(R.id.container, homeFragment);
                            } else if ("Nearest ATM and Branch".equals(selectedItem)) {
                                Log.d(LOG_TAG, "Nearest ATM and Branch");
                                MapFragment mapFragment = new MapFragment();
                                fragmentTransaction.replace(R.id.container, mapFragment);
                            }else if("Check Update".equals(selectedItem)){
                                Log.d(LOG_TAG, "Check Update");
                            }else if("Developer".equals(selectedItem)){
                                Log.d(LOG_TAG, "Developer");
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
        drawer.addStickyFooterItem(new PrimaryDrawerItem().withName("GitHub").withIcon(getResources().getDrawable(R.drawable.ic_github)));


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
}
