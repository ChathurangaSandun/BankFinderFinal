package com.bankfinder.chathurangasandun.bankfinder;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

import com.bankfinder.chathurangasandun.bankfinder.model.Bank;
import com.bankfinder.chathurangasandun.bankfinder.model.Branches;


public class BranchDetailActivity extends AppCompatActivity {

    int selectedbranchID;
    Branches selectedBranch;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branch_detail);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            selectedbranchID = extras.getInt("SELECTEDBRANCH");

        }

        DatabaseOpenHelper db =new DatabaseOpenHelper(getApplicationContext());

        selectedBranch = db.getMainBranch(Bank.selectedBank).get(0);







        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(selectedBranch.getName());

        loadBackdrop();
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
}
