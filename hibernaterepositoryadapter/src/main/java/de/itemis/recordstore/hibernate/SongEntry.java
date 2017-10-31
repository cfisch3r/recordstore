package de.itemis.recordstore.hibernate;

import de.itemis.recordstore.Song;

import javax.persistence.*;

@Entity(name="song")
public class SongEntry {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Basic
    public Long id;

    @Basic
    public String title;

    @Basic Integer duration;

    public SongEntry(Song song) {
        this.title = song.getTitle();
        this.duration = song.getDuration();
    }
}
