package de.itemis.recordstore.hibernate;

import de.itemis.recordstore.Record;
import de.itemis.recordstore.Song;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "record")
public class RecordData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic
    public Long id;

    @Basic
    public String title;

    @Basic
    public String artist;

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "RECORD_ID", nullable = false)
    public Set<SongData> songs = new HashSet<>();

    public RecordData(Record record) {
        this.title = record.getTitle();
        this.artist = record.getArtist();
        for (Song song : record.getSongs()) {
            songs.add(new SongData(song));
        }
    }
}
