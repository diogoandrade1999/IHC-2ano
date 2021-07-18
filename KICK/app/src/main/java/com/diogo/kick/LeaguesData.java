package com.diogo.kick;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

public class LeaguesData extends AsyncTask<Void, Void, Void> {

    private Context context;
    private List<ItemsLeagues> listData;
    private List<ItemsLeagues> listData2;

    public LeaguesData(Context context) {
        this.context = context;

    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            String json = "";
            URL url = new URL("https://api-football-v1.p.rapidapi.com/v2/leagues");
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
                listData2 = new ArrayList<>();
                JSONArray array = JO.getJSONArray("leagues");
                for (int i = 0; i < array.length(); i++) {
                    JSONObject o = array.getJSONObject(i);
                    if (o.getString("season").equals("2018")) {
                        String id = o.getString("league_id");
                        String logo = o.getString("logo");
                        if (logo.equals("null"))
                            logo = "https://wydethemes.com/flora3/wp-content/themes/flora/images/blog/placeholder.jpg";
                        String name = o.getString("name");
                        String country = o.getString("country");
                        String flag = "https://www.countryflags.io/" + o.getString("country_code") + "/flat/64.png";
                        if (country.equals("World"))
                            flag = "https://cdn.pixabay.com/photo/2014/04/03/11/57/globe-312668_960_720.png";
                        ItemsLeagues listItem = new ItemsLeagues(id, logo, name, country, flag);
                        listData.add(listItem);
                        if ((name.equals("Primeira Liga") && country.equals("Portugal") )||
                                (name.equals("Primera Division") && country.equals("Spain") )||
                                        (name.equals("Serie A") && country.equals("Italy") )){
                            listData2.add(listItem);
                        }
                    }
                }
                Collections.sort(listData, new Comparator<ItemsLeagues>() {
                    @Override
                    public int compare(ItemsLeagues u1, ItemsLeagues u2) {
                        return u1.getCountry().compareTo(u2.getCountry());
                    }
                });
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

        Leagues.adapter = new LeaguesAdapter(listData, context);
        Leagues.recyclerView.setAdapter(Leagues.adapter);
        Leagues.adapterTop = new LeaguesAdapter(listData2, context);
        Leagues.recyclerViewTop.setAdapter(Leagues.adapterTop);
        Leagues.progressDialog.dismiss();
    }
}