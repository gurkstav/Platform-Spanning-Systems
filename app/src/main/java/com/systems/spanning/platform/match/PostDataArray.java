package com.systems.spanning.platform.match;

import android.os.AsyncTask;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import org.json.JSONArray;


public class PostDataArray extends AsyncTask<Void, Void, JSONArray> {
    private HttpURLConnection urlConnection;
    private String url;
    private PostDataArrayInterface callbackPost;
    private JSONArray json;
    private JSONObject postData;

    public PostDataArray(String url, HashMap<String, String> postData, PostDataArrayInterface callbackPost) {
        this.url = url;
        this.callbackPost = callbackPost;
        if (postData != null) {
            this.postData = new JSONObject(postData);
        }
    }

    @Override
    protected JSONArray doInBackground(Void... params) {
        StringBuilder result = new StringBuilder();
        try {
            URL url = new URL(this.url);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            urlConnection.setReadTimeout(5000 /* milliseconds */ );
            urlConnection.setConnectTimeout(5000 /* milliseconds */ );
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestMethod("POST");

            if (this.postData != null) {
                OutputStreamWriter writer = new OutputStreamWriter(urlConnection.getOutputStream());
                writer.write(postData.toString());
                writer.flush();
            }

            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));


            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }

            json = new JSONArray(result.toString());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }
        return json;
    }

    @Override
    protected void onPostExecute(JSONArray json) {
        super.onPostExecute(json);
        this.callbackPost.fetchDataCallback(json);
    }
}
