package de.itemis.recordstore;

import java.util.ArrayList;
import java.util.List;

/**
 * Record Request DTO.
 */
public class Record {
    private String title;
    private String artist;
    private List<Song> songs = new ArrayList<>();

    public Record(String title, String artist) {
        this.title = title;
        this.artist = artist;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void addSong(Song song) {
        songs.add(song);
    }
}
