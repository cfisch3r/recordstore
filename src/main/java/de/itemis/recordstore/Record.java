package de.itemis.recordstore;

public class Record {
    private String title;
    private String artist;

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
}
