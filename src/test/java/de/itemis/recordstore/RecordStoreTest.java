package de.itemis.recordstore;

import org.junit.Assert;
import org.junit.Test;

public class RecordStoreTest {

    public static final Integer NEW_ALBUM_ID = 4711;

    @Test
    public void when_record_is_stored_ID_from_repository_is_returned() {
        RecordStore recordStore = new RecordStore(new RecordRepository(NEW_ALBUM_ID));
        Record record = new Record();
        Integer albumId = recordStore.add(record);
        Assert.assertEquals(NEW_ALBUM_ID,albumId);
    }
}
