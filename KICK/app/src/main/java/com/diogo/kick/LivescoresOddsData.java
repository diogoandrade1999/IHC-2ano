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

public class LivescoresOddsData extends AsyncTask<Void, Void, Void> {

    private Context context;
    private String id;
    private String oddHome;
    private String oddDraw;
    private String oddAway;
    private String name1;
    private String name2;

    public LivescoresOddsData(Context context, String id, String name1, String name2) {
        this.context = context;
        this.id = id;
        this.name1 = name1;
        this.name2 = name2;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            String json = "";
            URL url = new URL("https://api-football-v1.p.rapidapi.com/v2/odds/fixture/"+id);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.addRequestProperty("X-RapidAPI-Key", "41dc7495ecmsh510ff4e7a9e00cap1f4e44jsnfe923af4782a");
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String temp;
            while ((temp = br.readLine()) != null)
                json = json + temp + "\n";
            try {
                JSONObject JO = new JSONObject(json).getJSONObject("api");
                JSONArray jarray = JO.getJSONArray("odds");
                JSONObject ob = jarray.getJSONObject(0);
                JSONArray jarray2 = ob.getJSONArray("bookmakers");
                JSONObject ob2 = jarray2.getJSONObject(3);
                JSONObject ob3 = ob2.getJSONArray("bets").getJSONObject(0);
                JSONArray array = ob3.getJSONArray("values");
                oddHome = array.getJSONObject(0).getString("odd");
                oddDraw = array.getJSONObject(1).getString("odd");
                oddAway = array.getJSONObject(2).getString("odd");
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

        LivescoresDetails.oddsTeam1name.setText(name1);
        LivescoresDetails.oddsTeam2name.setText(name2);
        LivescoresDetails.oddsTeam1.setText(oddHome);
        LivescoresDetails.oddsTeam2.setText(oddAway);
        LivescoresDetails.oddstie.setText(oddDraw);
        LivescoresDetails.progressDialog.dismiss();
    }
}