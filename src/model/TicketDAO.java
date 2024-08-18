package model;


import service.DBConnection;
import users.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketDAO extends DBConnection {


    public void save(Ticket ticket) {
        String sql = "INSERT INTO Ticket(id, creation_date, ticket_type, user_id) VALUES (?, ?, ?::ticket_type, ?)";
        try (Connection connection = connect();
             PreparedStatement statement = connection.prepareStatement(sql);) {

            statement.setInt(1, ticket.getID());
            statement.setDate(2, Date.valueOf(ticket.getTicketCreationDate()));
            statement.setString(3, ticket.getTicketType().toString());
            statement.setInt(4, ticket.getUser().getID());

            int count = statement.executeUpdate();

            if (count > 0) {
                System.out.println("The User was successfully inserted");
            } else {
                System.out.println("The insertion failed");
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public Ticket getByID(int id) {
        String sql = "SELECT * FROM Ticket WHERE id = ?";
        try (Connection connection = connect();
             PreparedStatement statement = connection.prepareStatement(sql);) {

            statement.setInt(1, id);
            ResultSet results = statement.executeQuery();

            if (!results.first()) {
                return null;
            }

            Ticket ticket = new Ticket();
            ticket.setID(results.getInt(1));
            ticket.setTicketCreationDate(results.getDate(2).toLocalDate());
            ticket.setUser(User.getByID(results.getInt(3)));

            return ticket;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<Ticket> getByUserID(int id) {

        List<Ticket> ticketList = new ArrayList<>();
        String sql = "SELECT ticket_id FROM(Ticket JOIN User ON Ticket.user_id = User.id) WHERE User.id = ?";
        try (Connection connection = connect();
             PreparedStatement statement = connection.prepareStatement(sql);) {

            statement.setInt(4, id);
            ResultSet results = statement.executeQuery();

            if (!results.first()) {
                return null;
            }

            while(results.next()) {
                ticketList.add(this.getByID(results.getInt(1)));
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    return ticketList;
    }

    public void update(int id, Ticket.TicketType ticketType) {
        String sql = "Update Ticket set ticket_type=?::ticket_type WHERE id = ?";
        try (Connection connection = connect();
             PreparedStatement statement = connection.prepareStatement(sql);) {

            statement.setString(3, ticketType.toString());
            statement.setInt(1, id);

            int count = statement.executeUpdate();

            if (count > 0) {
                System.out.println("The ticket type was successfully updated");
            } else {
                System.out.println("The update failed");
            }

        } catch (SQLException ex) {
        throw new RuntimeException(ex);
        }
    }
}
