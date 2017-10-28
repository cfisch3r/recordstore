package de.itemis.recordstore;

public class RecordStore {
    private final RecordRepository recordRepository;

    public RecordStore(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    public Integer add(Record record) {

        return recordRepository.save(record);
    }

}
