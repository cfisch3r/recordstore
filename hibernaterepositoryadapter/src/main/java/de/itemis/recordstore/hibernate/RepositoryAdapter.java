package de.itemis.recordstore.hibernate;

import de.itemis.recordstore.Record;
import de.itemis.recordstore.RecordRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class RepositoryAdapter implements RecordRepository {

    private SessionFactory sessionFactory;

    public RepositoryAdapter(String connectionString, String userName, String password) {
        sessionFactory = createSessionFactory(connectionString, userName, password);
    }

    @Override
    public Long save(Record record) {
        Session session = sessionFactory.openSession();
        RecordData entry = new RecordData(record);
        session.save(entry);
        session.close();
        return entry.id;
    }

    private SessionFactory createSessionFactory(String connectionString, String userName, String password) {
        Configuration configuration = new Configuration();
        configuration.setProperty("connection.driver_class",
                "com.mysql.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url",
                connectionString);
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLInnoDBDialect");
        configuration.setProperty("hibernate.connection.username", userName);
        configuration.setProperty("hibernate.connection.password", password);
        configuration.addAnnotatedClass(SongData.class);
        configuration.addAnnotatedClass(RecordData.class);
        return configuration.buildSessionFactory();
    }
}
