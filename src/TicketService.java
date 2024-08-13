import model.Ticket;
import users.User;

public class TicketService implements service.PrintClassInfo {
    public static void main(String[] args) {
        User user1 = new User("Soliderus", User.UserRole.ADMIN);
        User user2 = new User("Nastenish", User.UserRole.CLIENT);
        User user3 = new User("Varenik", User.UserRole.CLIENT);
        User user4 = new User("Du", User.UserRole.ADMIN);

        for(User u : User.userList) {
            u.printRole();
        }

        Ticket ticket1 = new Ticket();
        Ticket ticket2 = new Ticket();
        ticket1.shareTicket(375293795535L);
        ticket2.shareTicket(37060544327L, "nas.das@gmail.com");
    }
}



