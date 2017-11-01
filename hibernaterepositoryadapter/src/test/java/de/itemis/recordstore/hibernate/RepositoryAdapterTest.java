package de.itemis.recordstore.hibernate;


import com.mysql.cj.jdbc.MysqlDataSource;
import de.itemis.recordstore.Record;
import de.itemis.recordstore.Song;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.flywaydb.core.Flyway;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class RepositoryAdapterTest {
    public static final String RECORDSTORE_SCHEMA = "recordstore";
    public static final String MYSQL_SCHEMA = "mysql";
    public static final String DB_BASE_CONNECTION_STRING = "jdbc:mysql://127.0.0.1";
    public static final String DB_USER = "test";
    public static final String DB_PW = "test";
    public static final String RECORD_TABLE = "record";
    public static final String SONG_TABLE = "song";

    private RepositoryAdapter repositoryAdapter;

//    @ClassRule
//    public static DockerRule mysqlContainer = DockerRule.builder()
//            .imageName("mysql/mysql-server:latest")
//            .expose("3306","3306")
//            .env("MYSQL_ROOT_PASSWORD","admin")
//            .waitFor(WaitFor.logMessage("Starting MySQL"))
//            .build();

    @Before
    public void setUp() throws Exception {
        createSchema();
        importSchema();
        repositoryAdapter = new RepositoryAdapter(String.format("%s/%s", DB_BASE_CONNECTION_STRING, RECORDSTORE_SCHEMA),
                DB_USER, DB_PW);
    }

    @After
    public void tearDown() throws Exception {
        dropSchema();
    }

    @Test
    public void schema_is_empty() throws SQLException {
        assertHasNoEntries(RECORD_TABLE);
        assertHasNoEntries(SONG_TABLE);
    }

    @Test
    public void record_is_saved() throws SQLException {
        repositoryAdapter.save(new Record("V", "Led Zeppelin"));
        List<Map<String, Object>> entries = getAllEntries(RECORD_TABLE);
        Assert.assertEquals("{ID=1, TITLE=V, ARTIST=Led Zeppelin}", entries.get(0).toString());
    }

    @Test
    public void song_is_saved() throws SQLException {
        Record record = new Record("V", "Led Zeppelin");
        record.addSong(new Song("Black Dog", 10));
        repositoryAdapter.save(record);
        List<Map<String, Object>> entries = getAllEntries(SONG_TABLE);
        Assert.assertEquals("{ID=1, TITLE=Black Dog, DURATION=10, RECORD_ID=1}", entries.get(0).toString());
    }

    @Test
    public void new_id_is_returned() throws SQLException {
        Long id = repositoryAdapter.save(new Record("V", "Led Zeppelin"));
        List<Map<String, Object>> entries = getAllEntries(RECORD_TABLE);
        Assert.assertEquals(id.longValue(), getIntegerFieldValue(entries.get(0), "ID").longValue());
    }


    private Integer getIntegerFieldValue(Map<String, Object> entry, String field) {
        return (Integer) entry.get(field);
    }

    private List<Map<String, Object>> getAllEntries(String table) throws SQLException {
        QueryRunner runner = new QueryRunner(getMysqlDataSource(RECORDSTORE_SCHEMA));
        return runner.query(String.format("select * from %s", table), new MapListHandler());
    }

    private void createSchema() throws SQLException {
        MysqlDataSource ds = getMysqlDataSource(MYSQL_SCHEMA);
        QueryRunner runner = new QueryRunner(ds);
        runner.execute(String.format("CREATE DATABASE %s", RECORDSTORE_SCHEMA));
    }

    private void importSchema() {
        Flyway flyway = new Flyway();
        flyway.setDataSource(getMysqlDataSource(RECORDSTORE_SCHEMA));
        flyway.migrate();
    }

    private void dropSchema() throws SQLException {
        MysqlDataSource ds = getMysqlDataSource(MYSQL_SCHEMA);
        QueryRunner runner = new QueryRunner(ds);
        runner.execute(String.format("DROP DATABASE %s", RECORDSTORE_SCHEMA));
    }

    private MysqlDataSource getMysqlDataSource(String schema) {
        MysqlDataSource ds = new MysqlDataSource();
        ds.setUrl(String.format("%s/%s?user=%s&password=%s", DB_BASE_CONNECTION_STRING, schema, DB_USER, DB_PW));
        return ds;
    }

    private void assertHasNoEntries(String tableName) throws SQLException {
        List<Map<String, Object>> entries = getAllEntries(tableName);
        Assert.assertEquals(0, entries.size());
    }
}
