package com.systems.spanning.platform.match;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Marcus on 2018-02-08.
 */

public class Match implements Parcelable {
    private String name;
    private int thumbnail;

    public Match(String name, int thumbnail) {
        this.name = name;
        this.thumbnail = thumbnail;
    }

    public Match (Parcel parcel) {
        this.name = parcel.readString();
        this.thumbnail = parcel.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }
}
