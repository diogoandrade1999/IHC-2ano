package com.diogo.kick;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class LivescoresDetails extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static RecyclerView recyclerView;
    public static RecyclerView.Adapter adapter;
    public static ProgressDialog progressDialog;
    public static TextView oddsTeam1name;
    public static TextView oddsTeam2name;
    public static TextView oddsTeam1;
    public static TextView oddsTeam2;
    public static TextView oddstie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_livescores);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("LiveScores");

        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);


        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewRes);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading data...");
        progressDialog.show();

        oddsTeam1name = findViewById(R.id.oddsteam1name);
        oddsTeam2name = findViewById(R.id.oddsteam2name);
        oddsTeam1 = findViewById(R.id.oddsteam1);
        oddsTeam2 = findViewById(R.id.oddsteam2);
        oddstie = findViewById(R.id.oddstie);

        new LivescoresData(getApplicationContext(), null, null).execute();

        Button button = findViewById(R.id.betButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.bet.pt/apostas-desportivas/"));
                startActivity(intent);
                finish();
                System.exit(0);
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        int id = menuItem.getItemId();

        if (id == R.id.leagues) {
            Intent intent = new Intent(LivescoresDetails.this, Leagues.class);
            startActivity(intent);
            finish();
        }else if (id == R.id.livescores) {
            Intent intent = new Intent(LivescoresDetails.this, LivescoresDetails.class);
            startActivity(intent);
            finish();
        }else if (id == R.id.tops) {
            Intent intent = new Intent(LivescoresDetails.this, Tops.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.exit) {
            finish();
            System.exit(0);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
