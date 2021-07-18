package com.diogo.kick;

import android.content.Context;
import android.os.AsyncTask;

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
import java.util.List;

public class LeaguesDetailsData extends AsyncTask<Void, Void, Void> {

    private Context context;
    private List<ItemsLeaguesDetails> listData;

    public LeaguesDetailsData(Context context) {
        this.context = context;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            String json = "";
            URL url = new URL("https://api-football-v1.p.rapidapi.com/v2/leagueTable/"+LeaguesDetails.id_league);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.addRequestProperty("X-RapidAPI-Key", "41dc7495ecmsh510ff4e7a9e00cap1f4e44jsnfe923af4782a");
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String temp;
            while ((temp = br.readLine()) != null)
                json = json + temp + "\n";

            try {
                JSONObject JO = new JSONObject(json).getJSONObject("api");
                listData = new ArrayList<>();
                JSONArray jarray = JO.getJSONArray("standings");
                JSONArray array = (JSONArray) jarray.get(0);
                for (int i = 0; i < array.length(); i++) {
                    JSONObject o = array.getJSONObject(i);
                    String id = o.getString("team_id");
                    String logo = o.getString("logo");
                    String name = o.getString("teamName");
                    String rank = o.getString("rank");
                    String goalsFor = o.getJSONObject("all").getString("goalsFor");
                    String goalsAgainst = o.getJSONObject("all").getString("goalsAgainst");
                    String points = o.getString("points");
                    String matchsPlayed = o.getJSONObject("all").getString("matchsPlayed");
                    ItemsLeaguesDetails listItem = new ItemsLeaguesDetails(id, logo, name, rank, goalsFor, goalsAgainst, points, matchsPlayed);
                    listData.add(listItem);
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

        LeaguesDetails.adapter = new LeaguesDetailsAdapter(listData, context, LeaguesDetails.country, LeaguesDetails.flag);
        LeaguesDetails.recyclerView.setAdapter(LeaguesDetails.adapter);
        LeaguesDetails.progressDialog.dismiss();
    }
}