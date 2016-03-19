package com.bankfinder.chathurangasandun.bankfinder;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

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

public class HomeActivity extends AppCompatActivity {
    private static final int PROFILE_SETTING = 1;
    AccountHeader headerDrawer;
    Drawer drawer;

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

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });



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
        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withName("Home");
        PrimaryDrawerItem item2 = new PrimaryDrawerItem().withName("Map");





//create the drawer and remember the `Drawer` result object
        drawer = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withAccountHeader(headerDrawer)

                .addDrawerItems(
                        item1,
                        new DividerDrawerItem(),
                        item2,
                        item2,
                        item2, item2, item2, item2, item2, item2, item2, item2, item2, item2, item2, item2, item2,


                        new SecondaryDrawerItem().withName("ddddd")
                )

                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {


                        return false;
                    }
                })
                .build();




//to update only the name, badge, icon you can also use one of the quick methods
        //result.updateName(1, "name");

//the result object also allows you to add new items, remove items, add footer, sticky footer, ..
        drawer.addItem(new DividerDrawerItem());
        drawer.addStickyFooterItem(new PrimaryDrawerItem().withName("StickyFooterItem"));

//remove items with an identifier
        drawer.removeItem(2);

//open / close the drawer
        drawer.openDrawer();
        drawer.closeDrawer();

//get the reference to the `DrawerLayout` itself
        drawer.getDrawerLayout();

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
