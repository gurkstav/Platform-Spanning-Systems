package com.systems.spanning.platform.match;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class fetchCardParams {

    public static ArrayList<Match> fetchParams(JSONArray result) {
        ArrayList<Match> matchList = new ArrayList<>();
        if (result.length() == 0) {
            Match a = new Match(null, null, null, null, null, null, 0);
            matchList.add(a);
        }
        else {
            for (int i = 0; i < result.length(); i++) {
                try {
                    JSONObject activity = result.getJSONObject(i);
                    String type = activity.getString("type");
                    String date = activity.getString("date");
                    String location = activity.getString("location");
                    String max_part = activity.getString("max_participants");
                    String min_part = activity.getString("min_participants");
                    String users_email = activity.getString("users_email");

                    Match a = new Match(type, date, location, min_part, max_part, users_email, 1);
                    matchList.add(a);
                } catch (JSONException e) {
                }
            }
        }
        return matchList;
    }
}
