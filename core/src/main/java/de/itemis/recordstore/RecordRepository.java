package de.itemis.recordstore;

/**
 * Repository for Records.
 */
public interface RecordRepository {
    Long save(Record record);
}
