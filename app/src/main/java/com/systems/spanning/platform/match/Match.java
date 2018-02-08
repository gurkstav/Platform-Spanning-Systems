package com.systems.spanning.platform.match;

/**
 * Created by Marcus on 2018-02-08.
 */

public class Match {
    private String name;
    private int thumbnail;

    public Match(String name, int thumbnail) {
        this.name = name;
        this.thumbnail = thumbnail;
    }

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
