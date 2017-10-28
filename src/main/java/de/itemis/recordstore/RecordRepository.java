package de.itemis.recordstore;

public class RecordRepository {

    private int newAlbumID;

    public RecordRepository(int newAlbumID) {
        this.newAlbumID = newAlbumID;
    }

    int save(Record record) {
        return newAlbumID;
    }
}