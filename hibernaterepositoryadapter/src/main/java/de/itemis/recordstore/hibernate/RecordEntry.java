package de.itemis.recordstore.hibernate;

import de.itemis.recordstore.Record;

import javax.persistence.*;

@Entity(name = "record")
public class RecordEntry {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Basic
    public Long id;

    @Basic
    public String title;

    @Basic
    public String artist;

    public RecordEntry(Record record) {
        this.title = record.getTitle();
        this.artist = record.getArtist();
    }
}
