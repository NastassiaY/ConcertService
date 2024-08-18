package users;


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;


public class User implements service.PrintClassInfo {
    static int userCount = 1;
    private Integer id = 0;
    String userName;
    UserRole userRole;
    LocalDate creationDate;
    public static ArrayList<User> userList = new ArrayList<User>();

    public User() {
        userList.add(this);
    }
    public User(String userName) {
        this.setID();
        this.userName = userName;
        this.creationDate = LocalDate.now();
        userList.add(this);
    }
    public User(String userName, UserRole userRole) {
        this.setID();
        this.userName = userName;
        this.userRole = userRole;
        this.creationDate = LocalDate.now();
        userList.add(this);
    }

    public void printRole() {
        System.out.printf("User %s with ID %d is %s%n", this.getUserName(), this.getID(), this.userRole.getTitle());
    }

    public void setUserName(String name) {
        this.userName = name;
    }
    public String getUserName() {
        return userName;
    }

    public void setCreationDate(LocalDate date) {
        this.creationDate = date;
    }
    public LocalDate getCreationDate() {
        return this.creationDate;
    }

    public void setID() {
        if (this.id == 0) {
            this.id = userCount++;
        } else {
            System.out.println("User ID is already set");
            return;
        }
    }
    public void setID(int id) {
        this.id = id;
    }

    public Integer getID() {
        return id;
    }

    public static User getByID(int id) {
        for(User user : userList) {
            if(user.getID() == id) {
                return user;
            } else {
                System.out.println("No matches found");
            }
        }
        return null;
    }

    public String getClassInfo() {
        Class<? extends User> c = this.getClass();
        String modifiers = Modifier.toString(c.getModifiers());
        Field[] fields = c.getFields();
        StringBuilder fieldsList = new StringBuilder();
        for(Field field : fields) {
            fieldsList.append(field.toGenericString());
        }
        Method[] methods = c.getMethods();
        StringBuilder methodsList = new StringBuilder();
        for(Method method : methods) {
            methodsList.append(method.toGenericString());
        }

        return String.format("Class name: %s; Class modifiers: %s; Class fields: %s; " +
                "Class methods: %s", c.getName(), modifiers, fieldsList, methodsList);
    }

    @Override
    public void printClassInfo() {
        System.out.println(this.getClassInfo());
    }

    public enum UserRole {
        ADMIN("Admin"),
        CLIENT("Client"),
        UNDEFINED("Undefined");

        private final String title;

        UserRole(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }
    }
}
