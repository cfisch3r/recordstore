package de.itemis.recordstore;

public class RecordRepository {

    private int newAlbumID;
    private Record record;

    public RecordRepository(int newAlbumID) {
        this.newAlbumID = newAlbumID;
    }

    public Record fetchStoredRecord() {
        return record;
    }

    int save(Record record) {
        this.record = record;
        return newAlbumID;
    }
}