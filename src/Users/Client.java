package Users;

import Ticket.Ticket;

import java.util.ArrayList;

public class Client extends User {
    private ArrayList<Ticket> userTickets;

    public Client(String userName) {
        super(userName);
        this.userRole = UserRole.CLIENT;
        userTickets = new ArrayList<Ticket>();
    }

    public Ticket getTicket() {
        Ticket ticket = new Ticket();
        this.userTickets.add(ticket);
        System.out.printf("Ticket with ID %d was created for User %s with ID %d", ticket.getID(), this.getUserName(), this.getID());
        return ticket;
    }
}
