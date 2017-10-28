package de.itemis.recordstore;

public class RecordRepositoryMock implements RecordRepository {

    private int newAlbumID;
    private Record record;

    public RecordRepositoryMock(int newAlbumID) {
        this.newAlbumID = newAlbumID;
    }

    @Override
    public Integer save(Record record) {
        this.record = record;
        return newAlbumID;
    }

    public Record fetchStoredRecord() {
        return record;
    }

}