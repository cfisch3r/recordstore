package de.itemis.recordstore.hibernate;


import com.mysql.cj.jdbc.MysqlDataSource;
import de.itemis.recordstore.Record;
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
    }

    @After
    public void tearDown() throws Exception {
        dropSchema();

    }

    @Test
    public void schema_is_imported() throws SQLException {
        List<Map<String, Object>> entries = getAllEntries("record");
        Assert.assertEquals(0,entries.size());
    }

    @Test
    public void record_is_saved() throws SQLException {
        RepositoryAdapter repositoryAdapter = new RepositoryAdapter("jdbc:mysql://127.0.0.1/recordstore", "test", "test");
        repositoryAdapter.save(new Record("V","Led Zeppelin"));
        List<Map<String, Object>> entries = getAllEntries("record");
        Assert.assertEquals("V", getFieldValue(entries.get(0), "TITLE"));
        Assert.assertEquals("Led Zeppelin", getFieldValue(entries.get(0), "ARTIST"));
    }

    @Test
    public void new_id_is_returned() throws SQLException {
        RepositoryAdapter repositoryAdapter = new RepositoryAdapter("jdbc:mysql://127.0.0.1/recordstore", "test", "test");
        Long id = repositoryAdapter.save(new Record("V", "Led Zeppelin"));
        List<Map<String, Object>> entries = getAllEntries("record");
        Assert.assertEquals(id.longValue(), getIntegerFieldValue(entries.get(0), "ID").longValue());
    }

    private String getFieldValue(Map<String, Object> entry, String field) {
        return (String) entry.get(field);
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
        runner.execute("CREATE DATABASE recordstore");
    }

    private void importSchema() {
        Flyway flyway = new Flyway();
        flyway.setDataSource(getMysqlDataSource(RECORDSTORE_SCHEMA));
        flyway.migrate();
    }

    private void dropSchema() throws SQLException {
        MysqlDataSource ds = getMysqlDataSource(MYSQL_SCHEMA);
        QueryRunner runner = new QueryRunner(ds);
        runner.execute("DROP DATABASE recordstore");
    }

    private MysqlDataSource getMysqlDataSource(String schema) {
        MysqlDataSource ds = new MysqlDataSource();
        ds.setUrl(String.format("jdbc:mysql://127.0.0.1:3306/%s?" +
                "user=%s&password=%s", schema,"test","test"));
        return ds;
    }
}