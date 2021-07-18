package com.diogo.kick;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
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
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class PlayersData extends AsyncTask<Void, Void, Void> {

    private Context context;

    public PlayersData(Context context) {
        this.context = context;

    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            String json = "";
            URL url = new URL("https://api-football-v1.p.rapidapi.com/v2/players/player/"+PlayersDetails.player_id);
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
                Log.e("----", JO.toString());
                for (int i = 0; i < array.length(); i++) {
                    JSONObject o = array.getJSONObject(i);
                    String id = o.getString("player_id");
                    if (PlayersDetails.player_id.equals(id)){
                        String name = o.getString("player_name");
                        PlayersDetails.nameView.setText(name);
                        String number = o.getString("number");
                        PlayersDetails.numberView.setText(number);
                        String age = o.getString("age");
                        PlayersDetails.ageView.setText(age);
                        String position = o.getString("position");
                        PlayersDetails.positionView.setText(position);
                        String injured = o.getString("injured");
                        PlayersDetails.injuredView.setText(injured);
                        String rating = o.getString("rating");
                        if (rating.equals("null"))
                            rating = "0";
                        PlayersDetails.ratingView.setProgress((int)Math.floor(Double.parseDouble(rating)));
                        String goals = o.getJSONObject("goals").getString("total");
                        PlayersDetails.goalsView.setText(goals);
                        String cardy = o.getJSONObject("cards").getString("yellow");
                        PlayersDetails.cardyView.setText(cardy);
                        String cardr = o.getJSONObject("cards").getString("red");
                        PlayersDetails.cardrView.setText(cardr);
                    }

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        PlayersDetails.progressDialog.dismiss();
    }
}