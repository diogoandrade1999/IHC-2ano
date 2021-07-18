package com.diogo.kick;

import android.app.Activity;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class PlayersDetails extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static String country;
    public static String name_team;
    public static String imageUrl;
    public static String player_id;
    public static String flag;
    public static TextView nameView;
    public static TextView numberView;
    public static TextView ageView;
    public static TextView positionView;
    public static TextView injuredView;
    public static RatingBar ratingView;
    public static TextView goalsView;
    public static TextView cardyView;
    public static TextView cardrView;
    public static ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players_details);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);

        country = getIntent().getStringExtra("country");
        name_team = getIntent().getStringExtra("team");
        imageUrl = getIntent().getStringExtra("imageUrl");
        player_id = getIntent().getStringExtra("player_id");
        flag = getIntent().getStringExtra("flag");

        getSupportActionBar().setTitle("PLAYER DETAILS");

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading data...");
        progressDialog.show();
        new PlayersData(getBaseContext()).execute();

        ImageView logoView = findViewById(R.id.logoView);
        Picasso.with(this).load(imageUrl).into(logoView);
        TextView teamView = findViewById(R.id.teamView);
        teamView.setText(name_team);

        nameView = findViewById(R.id.nameView);
        numberView = findViewById(R.id.numberView);
        ageView = findViewById(R.id.ageView);
        positionView = findViewById(R.id.positionView);
        injuredView = findViewById(R.id.injuredView);
        ratingView = findViewById(R.id.ratingView);
        goalsView = findViewById(R.id.goalsView);
        cardyView = findViewById(R.id.cardyView);
        cardrView = findViewById(R.id.cardrView);

        int rand = new Random().nextInt(55)+45;
        ProgressBar passesView = findViewById(R.id.passesView);
        TextView passes2View = findViewById(R.id.passes2View);
        passesView.setProgress(rand);
        passes2View.setText(String.valueOf(rand));
        rand = new Random().nextInt(55)+45;
        ProgressBar dribblesView = findViewById(R.id.dribblesView);
        TextView dribbles2View = findViewById(R.id.dribbles2View);
        dribblesView.setProgress(rand);
        dribbles2View.setText(String.valueOf(rand));
        rand = new Random().nextInt(55)+45;
        ProgressBar duelsView = findViewById(R.id.duelsView);
        TextView duels2View = findViewById(R.id.duels2View);
        duelsView.setProgress(rand);
        duels2View.setText(String.valueOf(rand));
        rand = new Random().nextInt(55)+45;
        ProgressBar velocityView = findViewById(R.id.velocityView);
        TextView velocity2View = findViewById(R.id.velocity2View);
        velocityView.setProgress(rand);
        velocity2View.setText(String.valueOf(rand));
        rand = new Random().nextInt(55)+45;
        ProgressBar defenseView = findViewById(R.id.defenseView);
        TextView defense2View = findViewById(R.id.defense2View);
        defenseView.setProgress(rand);
        defense2View.setText(String.valueOf(rand));
        rand = new Random().nextInt(55)+45;
        ProgressBar physicView = findViewById(R.id.physicView);
        TextView physic2View = findViewById(R.id.physic2View);
        physicView.setProgress(rand);
        physic2View.setText(String.valueOf(rand));
        rand = new Random().nextInt(55)+45;
        ProgressBar headerView = findViewById(R.id.headerView);
        TextView header2View = findViewById(R.id.header2View);
        headerView.setProgress(rand);
        header2View.setText(String.valueOf(rand));
        rand = new Random().nextInt(55)+45;
        ProgressBar penaltyView = findViewById(R.id.penaltyView);
        TextView penalty2View = findViewById(R.id.penalty2View);
        penaltyView.setProgress(rand);
        penalty2View.setText(String.valueOf(rand));
    }

    public void loadJSONFromAsset() {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading data...");
        progressDialog.show();

        try {
            String json = "";
            URL url = new URL("https://api-football-v1.p.rapidapi.com/v2/players/player/"+player_id);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.addRequestProperty("X-RapidAPI-Key", "41dc7495ecmsh510ff4e7a9e00cap1f4e44jsnfe923af4782a");
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String temp;
            while ((temp = br.readLine()) != null)
                json = json + temp + "\n";

            try {
                JSONObject JO = new JSONObject(json).getJSONObject("api");
                JSONArray array = JO.getJSONArray("players");
                for (int i = 0; i < array.length(); i++) {
                    JSONObject o = array.getJSONObject(i);
                    String id = o.getString("player_id");
                    if (player_id.equals(id)){
                        String name = o.getString("player_name");
                        TextView nameView = findViewById(R.id.nameView);
                        nameView.setText(name);
                        ImageView logoView = findViewById(R.id.logoView);
                        Picasso.with(this).load(imageUrl).into(logoView);
                        TextView teamView = findViewById(R.id.teamView);
                        teamView.setText(name_team);
                        teamView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(PlayersDetails.this, TeamsDetails.class);
                                intent.putExtra("country", country);
                                intent.putExtra("flag", flag);
                                intent.putExtra("imageUrl", imageUrl);
                                intent.putExtra("name", name_team);
                                startActivity(intent);
                            }
                        });
                        String number = o.getString("number");
                        TextView numberView = findViewById(R.id.numberView);
                        numberView.setText(number);
                        String age = o.getString("age");
                        TextView ageView = findViewById(R.id.ageView);
                        ageView.setText(age);
                        String position = o.getString("position");
                        TextView positionView = findViewById(R.id.positionView);
                        positionView.setText(position);
                        String injured = o.getString("injured");
                        TextView injuredView = findViewById(R.id.injuredView);
                        injuredView.setText(injured);
                        String rating = o.getString("rating");
                        ProgressBar ratingView = findViewById(R.id.ratingView);
                        ratingView.setProgress((int)Math.floor(Double.parseDouble(rating)));
                        String goals = o.getJSONObject("goals").getString("total");
                        TextView goalsView = findViewById(R.id.goalsView);
                        goalsView.setText(goals);
                        String cardy = o.getJSONObject("cards").getString("yellow");
                        TextView cardyView = findViewById(R.id.cardyView);
                        cardyView.setText(cardy);
                        String cardr = o.getJSONObject("cards").getString("red");
                        TextView cardrView = findViewById(R.id.cardrView);
                        cardrView.setText(cardr);
                        int rand = new Random().nextInt(55)+45;
                        ProgressBar passesView = findViewById(R.id.passesView);
                        TextView passes2View = findViewById(R.id.passes2View);
                        passesView.setProgress(rand);
                        passes2View.setText(String.valueOf(rand));
                        rand = new Random().nextInt(55)+45;
                        ProgressBar dribblesView = findViewById(R.id.dribblesView);
                        TextView dribbles2View = findViewById(R.id.dribbles2View);
                        dribblesView.setProgress(rand);
                        dribbles2View.setText(String.valueOf(rand));
                        rand = new Random().nextInt(55)+45;
                        ProgressBar duelsView = findViewById(R.id.duelsView);
                        TextView duels2View = findViewById(R.id.duels2View);
                        duelsView.setProgress(rand);
                        duels2View.setText(String.valueOf(rand));
                        rand = new Random().nextInt(55)+45;
                        ProgressBar velocityView = findViewById(R.id.velocityView);
                        TextView velocity2View = findViewById(R.id.velocity2View);
                        velocityView.setProgress(rand);
                        velocity2View.setText(String.valueOf(rand));
                        rand = new Random().nextInt(55)+45;
                        ProgressBar defenseView = findViewById(R.id.defenseView);
                        TextView defense2View = findViewById(R.id.defense2View);
                        defenseView.setProgress(rand);
                        defense2View.setText(String.valueOf(rand));
                        rand = new Random().nextInt(55)+45;
                        ProgressBar physicView = findViewById(R.id.physicView);
                        TextView physic2View = findViewById(R.id.physic2View);
                        physicView.setProgress(rand);
                        physic2View.setText(String.valueOf(rand));
                        rand = new Random().nextInt(55)+45;
                        ProgressBar headerView = findViewById(R.id.headerView);
                        TextView header2View = findViewById(R.id.header2View);
                        headerView.setProgress(rand);
                        header2View.setText(String.valueOf(rand));
                        rand = new Random().nextInt(55)+45;
                        ProgressBar penaltyView = findViewById(R.id.penaltyView);
                        TextView penalty2View = findViewById(R.id.penalty2View);
                        penaltyView.setProgress(rand);
                        penalty2View.setText(String.valueOf(rand));
                        break;
                    }

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        progressDialog.dismiss();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        int id = menuItem.getItemId();

        if (id == R.id.leagues) {
            Intent intent = new Intent(PlayersDetails.this, Leagues.class);
            startActivity(intent);
            finish();
        }else if (id == R.id.livescores) {
            Intent intent = new Intent(PlayersDetails.this, LivescoresDetails.class);
            startActivity(intent);
            finish();
        }else if (id == R.id.tops) {
            Intent intent = new Intent(PlayersDetails.this, Tops.class);
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
