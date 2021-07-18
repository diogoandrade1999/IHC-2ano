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
import java.util.Collections;
import java.util.List;

public class LeaguesDetailsResultsData extends AsyncTask<Void, Void, Void> {

    private Context context;
    private List<ItemsLeaguesDetailsResults> listData1;
    private List<ItemsLeaguesDetailsResults> listData2;

    public LeaguesDetailsResultsData(Context context) {
        this.context = context;

    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            String json = "";
            URL url = new URL("https://api-football-v1.p.rapidapi.com/v2/fixtures/league/"+LeaguesDetails.id_league);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.addRequestProperty("X-RapidAPI-Key", "41dc7495ecmsh510ff4e7a9e00cap1f4e44jsnfe923af4782a");
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String temp;
            while ((temp = br.readLine()) != null)
                json = json + temp + "\n";

            try {
                JSONObject JO = new JSONObject(json).getJSONObject("api");
                listData1 = new ArrayList<>();
                listData2 = new ArrayList<>();
                JSONArray array = JO.getJSONArray("fixtures");
                for (int i = 0; i < array.length(); i++) {
                    JSONObject o = array.getJSONObject(i);
                    String id1 = o.getJSONObject("homeTeam").getString("team_id");
                    String id2 = o.getJSONObject("awayTeam").getString("team_id");
                    String logo1 = o.getJSONObject("homeTeam").getString("logo");
                    String logo2 = o.getJSONObject("awayTeam").getString("logo");
                    String name1 = o.getJSONObject("homeTeam").getString("team_name");
                    String name2 = o.getJSONObject("awayTeam").getString("team_name");
                    String round = o.getString("round");
                    String score = o.getJSONObject("score").getString("fulltime");
                    String status = o.getString("statusShort");
                    String date = o.getString("event_date");
                    ItemsLeaguesDetailsResults listItem = new ItemsLeaguesDetailsResults(id1, id2, logo1, logo2, name1, name2, round, score, status, date);
                    if (status.equals("FT") || status.equals("AET") || status.equals("PEN"))
                        listData1.add(listItem);
                    else
                        listData2.add(listItem);
                }
                Collections.reverse(listData1);
                Collections.reverse(listData2);
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

        LeaguesDetails.adapter2 = new LeaguesDetailsResultsAdapter(listData1, context, LeaguesDetails.country, LeaguesDetails.flag);
        LeaguesDetails.recyclerView2.setAdapter(LeaguesDetails.adapter2);
        LeaguesDetails.adapter3 = new LeaguesDetailsResultsAdapter(listData2, context, LeaguesDetails.country, LeaguesDetails.flag);
        LeaguesDetails.recyclerView3.setAdapter(LeaguesDetails.adapter3);
        LeaguesDetails.progressDialog.dismiss();
    }
}