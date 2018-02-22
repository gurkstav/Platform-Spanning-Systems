package com.systems.spanning.platform.match;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

/**
 * Created by Marcus on 2018-02-22.
 */

public class PostData extends AsyncTask<Void, Void, String> {
    private HttpURLConnection urlConnection;
    private String url;
    private PostDataInterface callbackPost;
    private String json;
    private JSONObject postData;

    public PostData(String url, HashMap<String, String> postData, PostDataInterface callbackPost) {
        this.url = url;
        this.callbackPost = callbackPost;
        if (postData != null) {
            this.postData = new JSONObject(postData);
        }
    }

    @Override
    protected String doInBackground(Void... params) {
        StringBuilder result = new StringBuilder();
        try {
            URL url = new URL(this.url);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestMethod("POST");

            if (this.postData != null) {
                OutputStreamWriter writer = new OutputStreamWriter(urlConnection.getOutputStream());
                writer.write(postData.toString());
                writer.flush();
            }

            int statusCode = urlConnection.getResponseCode();

            if (statusCode ==  200) {
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }
                json = result.toString();
            }
            else{
                //not successful
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }
        return json;
    }

    @Override
    protected void onPostExecute(String jsonResponse) {
        super.onPostExecute(jsonResponse);
        this.callbackPost.fetchDataCallback(jsonResponse);
    }
}