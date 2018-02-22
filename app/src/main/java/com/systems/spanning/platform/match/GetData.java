package com.systems.spanning.platform.match;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Marcus on 2018-02-16.
 */

public class GetData extends AsyncTask<Void, Void, JSONArray>{
    private HttpURLConnection urlConnection;
    private String url;
    private GetDataInterface callbackInterface;
    JSONArray jsonResponse = null;
    private String json;

    public GetData(String url, GetDataInterface callbackInterface) {
        this.url = url;
        this.callbackInterface = callbackInterface;
    }

    @Override
    protected JSONArray doInBackground(Void... params) {
        StringBuilder result = new StringBuilder();
        try {
            URL url = new URL(this.url);
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            json = result.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }

        try {
            jsonResponse = new JSONArray(json);
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }


        return jsonResponse;
    }

    @Override
    protected void onPostExecute(JSONArray jsonResponse) {
        super.onPostExecute(jsonResponse);
        this.callbackInterface.fetchDataCallback(jsonResponse);
    }
}
