package Service;

import java.time.LocalDateTime;
import java.time.Month;

import Ticket.Ticket;

public class TicketService implements IDManager {
    private static int ticketServiceCount = 1;
    private int ticketServiceID = 0;

    public static void main(String[] args) {

        try {
            Ticket ticketFull = new Ticket("Wembley", 333, LocalDateTime.of(2024, Month.OCTOBER, 15, 19, 30, 00), false, Ticket.StadiumSector.A, 5.0F);

        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }

        try {
            Ticket ticketLimited = new Ticket("Wembley", 333, LocalDateTime.of(2024, Month.OCTOBER, 15, 19, 30, 00));
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }

        Ticket ticketEmpty = new Ticket();

    }

    @Override
    public void setID() {
        if(this.ticketServiceID == 0) {
            this.ticketServiceID = ticketServiceCount++;
        } else {
            System.out.println("Ticket ID is already set");
            return;
        }
    }

    @Override
    public int getID() {
        return this.ticketServiceID;
    }
}



