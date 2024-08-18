package service;

import org.postgresql.ds.PGSimpleDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DBConnection {
    private static final PGSimpleDataSource ds = new PGSimpleDataSource();

    public static Connection connect() throws SQLException {
        PGSimpleDataSource ds = new PGSimpleDataSource();
        ds.setUrl("jdbc:postgresql://localhost:5432/my_ticket_service_db");
        ds.setUser("postgres");
        ds.setPassword("02112021");
        return ds.getConnection();
    }

}
