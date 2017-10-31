package de.itemis.recordstore.hibernate;

import de.itemis.recordstore.Record;
import de.itemis.recordstore.RecordRepository;
import org.hibernate.Session;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class RepositoryAdapter implements RecordRepository{

    @Override
    public Integer save(Record record) {
        StandardServiceRegistryBuilder builder =
                new StandardServiceRegistryBuilder();
        StandardServiceRegistry registry = builder.build();

        MetadataSources sources = new MetadataSources( registry );
        Configuration configuration = new Configuration();
        configuration.setProperty("connection.driver_class",
                "com.mysql.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url",
                "jdbc:mysql://127.0.0.1/recordstore");
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLInnoDBDialect");
        configuration.setProperty("hibernate.connection.username", "test");
        configuration.setProperty("hibernate.connection.password", "test");
        configuration.addAnnotatedClass(RecordEntry.class);
        Session session = configuration.buildSessionFactory().openSession();
        session.save(new RecordEntry(record));
        session.close();
        return null;
    }
}
