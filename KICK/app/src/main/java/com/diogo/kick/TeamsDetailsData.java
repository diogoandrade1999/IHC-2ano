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

public class TeamsDetailsData extends AsyncTask<Void, Void, Void> {

    private Context context;
    private List<ItemsTeamsDetails> listData;

    public TeamsDetailsData(Context context) {
        this.context = context;

    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            String json = "";
            URL url = new URL("https://api-football-v1.p.rapidapi.com/v2/players/team/"+TeamsDetails.id_team);
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
                JSONArray array = JO.getJSONArray("players");
                for (int i = 0; i < array.length(); i++) {
                    JSONObject o = array.getJSONObject(i);
                    String id = o.getString("player_id");
                    String name = o.getString("player_name");
                    String position = o.getString("position");
                    String number = o.getString("number");
                    ItemsTeamsDetails listItem = new ItemsTeamsDetails(id, name, position, number);
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

        TeamsDetails.adapter = new TeamsDetailsAdapter(listData, context, TeamsDetails.country, TeamsDetails.flag, TeamsDetails.name_team, TeamsDetails.imageUrl);
        TeamsDetails.recyclerView.setAdapter(TeamsDetails.adapter);
        TeamsDetails.progressDialog.dismiss();
    }
}