package Users;

public abstract class User implements Service.IDManager {
    private static int userCount = 1;
    private int userID = 0;

    @Override
    public void setID() {
        if(this.userID == 0) {
            this.userID = userCount++;
        } else {
            System.out.println("Ticket ID is already set");
            return;
        }
    }

    @Override
    public int getID() {
        return this.userID;
    }

    public abstract void printTicket();
}
