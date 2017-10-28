package de.itemis.recordstore;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RecordStoreTest {

    public static final Integer NEW_ALBUM_ID = 4711;
    private RecordStore recordStore;
    private RecordRepository recordRepository;

    @Before
    public void setUp() throws Exception {
        recordRepository = new RecordRepository(NEW_ALBUM_ID);
        recordStore = new RecordStore(recordRepository);
    }

    @Test
    public void when_record_is_stored_ID_from_repository_is_returned() {
        Record record = createRecord();
        Integer albumId = recordStore.add(record);
        Assert.assertEquals(NEW_ALBUM_ID,albumId);
    }


    @Test
    public void new_record_is_stored_in_Repository() {
        Record record = createRecord();
        recordStore.add(record);
        assertEquals(record, recordRepository.fetchStoredRecord());
    }

    private Record createRecord() {
        return new Record("Book of Death","Iron Maiden");
    }

    private void assertEquals(Record record, Record storedRecord) {
        Assert.assertEquals(storedRecord.getTitle(), record.getTitle());
        Assert.assertEquals(storedRecord.getArtist(), record.getArtist());
    }
}
