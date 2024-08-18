package model;

import users.User;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;


public class Ticket implements service.PrintClassInfo {
    static int userCount = 1;

    private Integer id = 0;
    private String venueName;
    private int eventCode;
    private LocalDateTime time;
    private boolean isPromo;
    private SeatSector seatSector;
    private float backpackWeightMAX;
    private LocalDate ticketCreationDate;
    private TicketType ticketType;
    private User user;

    public static ArrayList<Ticket> tickets = new ArrayList<Ticket>();

    public void setID() {
        if(this.id == 0) {
            this.id = userCount++;
        } else {
            System.out.println("User ID is already set");
            return;
        }
    }
    public void setID(int id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public Ticket() {
        tickets.add(this);
    }

    public Ticket(TicketType tt, User user) {
        tickets.add(this);
        ticketCreationDate = LocalDate.now();
        this.setID();
        this.ticketType = tt;
        this.user = user;
    }

    public Ticket(String venueName, int eventCode, LocalDateTime time) {
        ticketCreationDate = LocalDate.now();
        this.setID();

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

        if (backpackWeightMAX <= 0) {
            throw new IllegalArgumentException("BagWeightMAX should be above 0");
        }
        this.backpackWeightMAX = backpackWeightMAX;

    }


    public int getID() {
        return this.id;
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

    public void setTicketCreationDate(LocalDate date) {
        this.ticketCreationDate = date;
    }

    public LocalDate getTicketCreationDate() {return ticketCreationDate;}

    public void setTicketType(TicketType tt) {
        this.ticketType = tt;
    }

    public TicketType getTicketType() {
        return this.ticketType;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return this.user;
    }

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

    public enum TicketType {
        DAY,
        WEEK,
        MONTH,
        YEAR
    }

}

