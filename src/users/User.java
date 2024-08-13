package users;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

public class User implements service.IDManager, service.PrintClassInfo {
    static int userCount = 1;
    int userID = 0;
    final String userName;
    UserRole userRole;
    public static ArrayList<User> userList = new ArrayList<User>();

    public User(String userName) {
        this.userName = userName;
        this.setID();
        userList.add(this);
    }

    public User(String userName, UserRole userRole) {
        this.userName = userName;
        this.userRole = userRole;
        this.setID();
        userList.add(this);
    }

    public void printRole() {
        System.out.printf("User %s with ID %d is %s%n", this.getUserName(), this.getID(), this.userRole.getTitle());
    }

    public String getUserName() {
        return userName;
    }

    @Override
    public void setID() {
        if(this.userID == 0) {
            this.userID = userCount++;
        } else {
            System.out.println("User ID is already set");
            return;
        }
    }

    @Override
    public int getID() {
        return userID;
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
