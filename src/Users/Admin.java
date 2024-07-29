package Users;

import Ticket.Ticket;

public class Admin extends User {


    public Admin(String userName) {
        super(userName);
        this.userRole = UserRole.ADMIN;
    }
    public boolean checkTicket(Ticket ticket) {
        for(Ticket t : Ticket.tickets){
            if(t.equals(ticket)) {
                System.out.printf("The ticket with ID %d exists", ticket.getID());
                return true;
            } else System.out.println("The ticket doesn't exists");
        }
        return false;
    }
}
