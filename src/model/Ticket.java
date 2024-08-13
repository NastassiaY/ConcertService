package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

public class Ticket implements service.IDManager, service.PrintClassInfo {
    private static int ticketCount = 1;
    private int ticketID;
    private String venueName;
    private int eventCode;
    private LocalDateTime time;
    private boolean isPromo;
    private SeatSector seatSector;
    private float backpackWeightMAX;
    private final LocalDateTime ticketCreationTime;
    public static ArrayList<Ticket> tickets = new ArrayList<Ticket>();

    public Ticket() {
        this.setID();
        ticketCreationTime = LocalDateTime.now();
        tickets.add(this);
    }

    public Ticket(String venueName, int eventCode, LocalDateTime time) {
        this.setID();
        ticketCreationTime = LocalDateTime.now();

        if (venueName.length() > 10) {
            throw new IllegalArgumentException("ConcertHallName shouldn't be longer than 10 chars");
        }
        this.venueName = venueName;

        if(((int) (Math.log10(eventCode) + 1)) != 3 ) {
            throw new IllegalArgumentException("EventCode should contain 3 digits");
        }
        this.eventCode = eventCode;

        this.time = time;
        tickets.add(this);
    }

    public Ticket(String venueName, int eventCode,
                  LocalDateTime time, boolean isPromo, SeatSector sector, float backpackWeightMAX) {

        this(venueName, eventCode, time);

        this.isPromo = isPromo;
        this.seatSector = seatSector;

        if (backpackWeightMAX <= 0) {
            throw new IllegalArgumentException("BagWeightMAX should be above 0");
        }
        this.backpackWeightMAX = backpackWeightMAX;

    }

    @Override
    public void setID() {
        if(this.ticketID == 0) {
            this.ticketID = ticketCount++;
        } else {
            System.out.println("Ticket ID is already set");
            return;
        }
    }

    @Override
    public int getID() {
        return this.ticketID;
    }

    public String getVenueName() {return this.venueName;}

    public int getEventCode() {return this.eventCode;}

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public LocalDateTime getTime() {return this.time;}

    public boolean isPromo() {return isPromo;}

    public void setSeatSector(SeatSector seatSector) {
        this.seatSector = seatSector;
    }

    public SeatSector getSeatSector() {return this.seatSector;}

    public float getBackpackWeightMAX() {return this.backpackWeightMAX;}

    public LocalDateTime getTicketCreationTime() {return ticketCreationTime;}

    public void shareTicket(long phoneNumber) {
        String ticketDetail = this.toString();
        String printMessage = String.format("Ticket details were shared by phone: %d", phoneNumber);
        System.out.println(printMessage);
    }

    public void shareTicket(long phoneNumber, String eMail) {
        String ticketDetail = this.toString();
        String printMessage = String.format("Ticket details were shared by phone %d and by eMail: %s", phoneNumber, eMail);
        System.out.println(printMessage);
    }

    @Override
    public String toString() {

        return String.format("ID: %d, VenueName: %s, EventCode: %d",
                this.getID(), this.getVenueName(), this.getEventCode());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return ticketID == ticket.ticketID && getEventCode() == ticket.getEventCode() && Objects.equals(getTicketCreationTime(), ticket.getTicketCreationTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticketID, getEventCode(), getTicketCreationTime());
    }

    public enum SeatSector {
        A('A'),
        B('B'),
        C('C');

        private char title;

        SeatSector(char title) {
            this.title = title;
        }

        public char getTitle() {
            return title;
        }
    }

}

