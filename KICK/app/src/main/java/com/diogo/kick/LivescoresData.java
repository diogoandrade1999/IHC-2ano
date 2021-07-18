package com.diogo.kick;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

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

public class LivescoresData extends AsyncTask<Void, Void, Void> {

    private Context context;
    private List<ItemsLivescores> listData;
    private String country;
    private String flag;

    public LivescoresData(Context context, String country, String flag) {
        this.context = context;
        this.country = country;
        this.flag = flag;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            String json = "";
            URL url = new URL("https://api-football-v1.p.rapidapi.com/v2/fixtures/live");
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
                JSONArray array = JO.getJSONArray("fixtures");
                for (int i = 0; i < array.length(); i++) {
                    JSONObject o = array.getJSONObject(i);
                    String idf = o.getString("fixture_id");
                    String name1 = o.getJSONObject("homeTeam").getString("team_name");
                    String name2 = o.getJSONObject("awayTeam").getString("team_name");
                    String id1 = o.getJSONObject("homeTeam").getString("team_id");
                    String id2 = o.getJSONObject("awayTeam").getString("team_id");
                    String logo1 = o.getJSONObject("homeTeam").getString("logo");
                    String logo2 = o.getJSONObject("awayTeam").getString("logo");
                    String round = o.getString("round");
                    String scoreHome = o.getString("goalsHomeTeam");
                    String scoreAway = o.getString("goalsHomeTeam");
                    String status = o.getString("statusShort");
                    String date = o.getString("event_date");
                    ItemsLivescores listItem = new ItemsLivescores(idf, id1, id2, logo1, logo2, name1, name2, round, scoreHome, scoreAway, status, date);
                    listData.add(listItem);
                }
                Collections.reverse(listData);
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

        LivescoresDetails.adapter = new LivescoresAdapter(listData, context, country, flag);
        LivescoresDetails.recyclerView.setAdapter(LivescoresDetails.adapter);
        LivescoresDetails.progressDialog.dismiss();
    }
}