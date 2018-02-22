package com.systems.spanning.platform.match;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Marcus on 2018-02-08.
 */

public class Match implements Parcelable {
    private String type;
    private String date;
    private String location;
    private String min_participants;
    private String max_participants;
    private String email;
    private int thumbnail;


    public Match(String Type, String Date, String Location, String Min_participants, String Max_participants, String Email, int Thumbnail) {
        this.type = Type;
        this.date = Date;
        this.location = Location;
        this.max_participants = Max_participants;
        this.min_participants = Min_participants;
        this.email = Email;
        this.thumbnail = Thumbnail;
    }

    public Match (Parcel parcel) {
        this.type = parcel.readString();
        this.date = parcel.readString();
        this.location = parcel.readString();
        this.max_participants = parcel.readString();
        this.min_participants = parcel.readString();
        this.email = parcel.readString();
        this.thumbnail = parcel.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(type);
        dest.writeString(date);
        dest.writeString(location);
        dest.writeString(max_participants);
        dest.writeString(min_participants);
        dest.writeString(email);
        dest.writeInt(thumbnail);
    }

    public static Creator<Match> CREATOR = new Creator<Match>() {

        @Override
        public Match createFromParcel(Parcel source) {
            return new Match(source);
        }

        @Override
        public Match[] newArray(int size) {
            return new Match[size];
        }

    };

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMax_participants() {

        return max_participants;
    }

    public void setMax_participants(String max_participants) {
        this.max_participants = max_participants;
    }

    public String getMin_participants() {

        return min_participants;
    }

    public void setMin_participants(String min_participants) {
        this.min_participants = min_participants;
    }

    public String getLocation() {

        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {

        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }
}
