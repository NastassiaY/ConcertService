package main;

import model.Ticket;
import service.appDAO.TicketDAO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.Config;
import users.User;
import service.appDAO.UserDAO;

import java.util.List;

public class TicketService {
    public static void main(String[] args) {
        User user1 = new User("Soliderus", User.UserRole.ADMIN);
        User user2 = new User("Nastenish", User.UserRole.CLIENT);
        User user3 = new User("Varenik", User.UserRole.CLIENT);
        User user4 = new User("Du", User.UserRole.ADMIN);

        Ticket ticket1 = new Ticket(Ticket.TicketType.DAY, user1);
        Ticket ticket2 = new Ticket(Ticket.TicketType.WEEK, user2);
        Ticket ticket3 = new Ticket(Ticket.TicketType.MONTH, user1);
        Ticket ticket4 = new Ticket(Ticket.TicketType.YEAR, user3);

        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        UserDAO userDAO = context.getBean(UserDAO.class);
        TicketDAO ticketDAO = context.getBean(TicketDAO.class);

        userDAO.save(user1);
        userDAO.save(user2);
        userDAO.save(user3);

        User user5 = userDAO.getByID(user1.getID());
        userDAO.delete(user3);

        ticketDAO.save(ticket1);
        ticketDAO.save(ticket2);
        ticketDAO.save(ticket3);

        Ticket ticket5 = ticketDAO.getByID(ticket3.getID());
        List<Ticket> tickets = ticketDAO.getByUserID(user1.getID());
        ticketDAO.update(user2.getID(), Ticket.TicketType.WEEK);


    }
}



