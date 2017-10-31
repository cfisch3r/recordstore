package de.itemis.recordstore;

public class RecordRepositoryMock implements RecordRepository {

    private Long newAlbumID;
    private Record record;

    public RecordRepositoryMock(Long newAlbumID) {
        this.newAlbumID = newAlbumID;
    }

    @Override
    public Long save(Record record) {
        this.record = record;
        return newAlbumID;
    }

    public Record fetchStoredRecord() {
        return record;
    }

}