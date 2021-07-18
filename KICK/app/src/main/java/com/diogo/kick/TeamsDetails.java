package com.diogo.kick;

import android.app.ProgressDialog;
import android.content.Intent;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TeamsDetails extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static RecyclerView recyclerView;
    public static RecyclerView.Adapter adapter;
    public static RecyclerView recyclerView2;
    public static RecyclerView.Adapter adapter2;
    public static RecyclerView recyclerView3;
    public static RecyclerView.Adapter adapter3;
    public static ProgressDialog progressDialog;
    public static String country;
    public static String flag;
    public static String name_team;
    public static String imageUrl;
    public static String id_team;
    private boolean var = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teams_details);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);

        country = getIntent().getStringExtra("country");
        flag = getIntent().getStringExtra("flag");
        name_team = getIntent().getStringExtra("name");
        imageUrl = getIntent().getStringExtra("imageUrl");
        id_team = getIntent().getStringExtra("id");

        getSupportActionBar().setTitle(name_team);

        ImageView imageView = (ImageView) findViewById(R.id.flagView);
        Picasso.with(this).load(flag).into(imageView);
        TextView countryView = (TextView) findViewById(R.id.countryView);
        countryView.setText(country);
        ImageView logoView = (ImageView) findViewById(R.id.logoView);
        Picasso.with(this).load(imageUrl).into(logoView);
        TextView nameView = (TextView) findViewById(R.id.nameView);
        nameView.setText(name_team);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView2 = (RecyclerView) findViewById(R.id.recyclerViewRes);
        recyclerView2.setHasFixedSize(true);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        recyclerView3 = (RecyclerView) findViewById(R.id.recyclerViewGames);
        recyclerView3.setHasFixedSize(true);
        recyclerView3.setLayoutManager(new LinearLayoutManager(this));

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading data...");
        progressDialog.show();
        new TeamsDetailsData(getBaseContext()).execute();

        findViewById(R.id.playersButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.playersView).setVisibility(View.VISIBLE);
                findViewById(R.id.resultsView).setVisibility(View.GONE);
                findViewById(R.id.gamesView).setVisibility(View.GONE);
                findViewById(R.id.playersButton).setBackgroundColor(getResources().getColor(R.color.colorButtonV));
                findViewById(R.id.resultsButton).setBackgroundColor(getResources().getColor(R.color.colorButton));
                findViewById(R.id.gamesButton).setBackgroundColor(getResources().getColor(R.color.colorButton));
            }
        });
        findViewById(R.id.resultsButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.playersView).setVisibility(View.GONE);
                findViewById(R.id.resultsView).setVisibility(View.VISIBLE);
                findViewById(R.id.gamesView).setVisibility(View.GONE);
                findViewById(R.id.playersButton).setBackgroundColor(getResources().getColor(R.color.colorButton));
                findViewById(R.id.resultsButton).setBackgroundColor(getResources().getColor(R.color.colorButtonV));
                findViewById(R.id.gamesButton).setBackgroundColor(getResources().getColor(R.color.colorButton));
                if (var){
                    progressDialog.show();
                    new TeamsDetailsResultsData(getBaseContext()).execute();
                    var = false;
                }

            }
        });
        findViewById(R.id.gamesButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.playersView).setVisibility(View.GONE);
                findViewById(R.id.resultsView).setVisibility(View.GONE);
                findViewById(R.id.gamesView).setVisibility(View.VISIBLE);
                findViewById(R.id.playersButton).setBackgroundColor(getResources().getColor(R.color.colorButton));
                findViewById(R.id.resultsButton).setBackgroundColor(getResources().getColor(R.color.colorButton));
                findViewById(R.id.gamesButton).setBackgroundColor(getResources().getColor(R.color.colorButtonV));
                if (var){
                    progressDialog.show();
                    new TeamsDetailsResultsData(getBaseContext()).execute();
                    var = false;
                }
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        int id = menuItem.getItemId();

        if (id == R.id.leagues) {
            Intent intent = new Intent(TeamsDetails.this, Leagues.class);
            startActivity(intent);
            finish();
        }else if (id == R.id.livescores) {
            Intent intent = new Intent(TeamsDetails.this, LivescoresDetails.class);
            startActivity(intent);
            finish();
        }else if (id == R.id.tops) {
            Intent intent = new Intent(TeamsDetails.this, Tops.class);
            startActivity(intent);
            finish();
        }else if (id == R.id.exit) {
            finish();
            System.exit(0);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
