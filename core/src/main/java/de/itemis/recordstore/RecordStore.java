package de.itemis.recordstore;

/**
 * Record Store Application.
 */
public class RecordStore {
    private final RecordRepository recordRepository;

    public RecordStore(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    public Long add(Record record) {

        return recordRepository.save(record);
    }

}
