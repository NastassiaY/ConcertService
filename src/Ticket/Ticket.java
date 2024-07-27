package Ticket;

import Service.IDManager;

import java.time.Instant;
import java.time.LocalDateTime;

public class Ticket implements Service.IDManager {
    private static int ticketCount = 1;
    private int ticketID = 0;
    private String concertHallName;
    private int eventCode;
    private LocalDateTime concertStartTime;
    private boolean isPromo;
    private StadiumSector stadiumSector;
    private float backpackWeightMAX;
    private final LocalDateTime ticketCreationTime;


    public Ticket() {
        this.setID();
        ticketCreationTime = LocalDateTime.from(Instant.now());
    }

    public Ticket(String concertHallName, int eventCode, LocalDateTime concertStartTime) {
        this.setID();
        ticketCreationTime = LocalDateTime.from(Instant.now());

        if (concertHallName.length() > 10) {
            throw new IllegalArgumentException("ConcertHallName shouldn't be longer than 10 chars");
        }
        this.concertHallName = concertHallName;

        if(((int) (Math.log10(eventCode) + 1)) != 3 ) {
            throw new IllegalArgumentException("EventCode should contain 3 digits");
        }
        this.eventCode = eventCode;

        this.concertStartTime = concertStartTime;
    }

    public Ticket(String concertHallName, int eventCode,
                  LocalDateTime concertStartTime, boolean isPromo, StadiumSector stadiumSector, float backpackWeightMAX) {

        this(concertHallName, eventCode, concertStartTime);

        this.isPromo = isPromo;
        this.stadiumSector = stadiumSector;

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

    public void setConcertHallName(String concertHallName) {
        if (concertHallName.length() > 10) {
            throw new IllegalArgumentException("ConcertHallName shouldn't be longer than 10 chars");
        }
        this.concertHallName = concertHallName;
    }

    public String getConcertHallName() {return this.concertHallName;}

    public void setEventCode(int eventCode) {
        if(((int) (Math.log10(eventCode) + 1)) != 3 ) {
            throw new IllegalArgumentException("EventCode should contain 3 digits");
        }
        this.eventCode = eventCode;
    }

    public int getEventCode() {return this.eventCode;}

    public void setConcertStartTime(LocalDateTime concertStartTime) {
        this.concertStartTime = concertStartTime;
    }

    public LocalDateTime getConcertStartTime() {return this.concertStartTime;}

    public void setPromo(boolean promo) {
        isPromo = promo;
    }

    public boolean isPromo() {return isPromo;}

    public void setStadiumSector(StadiumSector stadiumSector) {
        this.stadiumSector = stadiumSector;
    }

    public StadiumSector getStadiumSector() {return this.stadiumSector;}

    public void setBackpackWeightMAX(float backpackWeightMAX) {
        if (backpackWeightMAX <= 0) {
            throw new IllegalArgumentException("BagWeightMAX should be above 0");
        }
        this.backpackWeightMAX = backpackWeightMAX;
    }

    public float getBackpackWeightMAX() {return this.backpackWeightMAX;}

    public LocalDateTime getTicketCreationTime() {return ticketCreationTime;}

        public enum StadiumSector {
        A('A'),
        B('B'),
        C('C');

        private char title;

        StadiumSector(char title) {
            this.title = title;
        }

        public char getTitle() {
            return title;
        }
    }

}

