package service;

import service.appDAO.TicketDAO;
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import service.appDAO.UserDAO;

import java.sql.SQLException;


@Configuration
@ComponentScan
public class Config {

    @Bean
    public PGSimpleDataSource dataSource() throws SQLException {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setUrl("jdbc:postgresql://localhost:5432/my_ticket_service_db");
        dataSource.setUser("postgres");
        dataSource.setPassword("02112021");
        return dataSource;
    }

    @Bean
    public UserDAO userDAO() throws SQLException {
        return new UserDAO(dataSource());
    }

    @Bean
    public TicketDAO ticketDAO() throws SQLException {
        return new TicketDAO(dataSource());
    }
}
