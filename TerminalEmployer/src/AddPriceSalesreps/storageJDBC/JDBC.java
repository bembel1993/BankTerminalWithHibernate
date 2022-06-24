package AddPriceSalesreps.storageJDBC;

import entity.Employee;
import entity.Password;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.internal.SessionFactoryImpl;

import java.sql.*;
import java.util.List;
import java.util.Scanner;

public class JDBC {

    public static Connection connection = null;

    public static void connect() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("MySQL JDBC Driver Registered!");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your MySQL JDBC Driver?");
            e.printStackTrace();
            throw new SQLException();
        }
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/forterminal?useUnicode=true&serverTimezone=UTC",
                "root", "root");
        if(connection == null) {
            throw new SQLException();
        } else {
            System.out.println("Successfully connected");
        }
    }

    public static void close() {
        try {
            if(connection != null) {
                connection.close();
                System.out.println("Closing connection");
            }
        } catch (SQLException e) {
            System.out.println("Failed to close connection!");
        }
    }
///////////////////////////////////////////////////////

  /*  private boolean checkUniqueLogin(String passw) {
        boolean isUnique = true;
        for (Employee emp : getEmployee()) {
            if (emp.getPassword().getName().equals(passw)) {
                isUnique = false;
            }
        }
        return isUnique;
    }

    public void showPeople() {
        List<Employee> employees = getEmployee();
        if (employees.size() != 0) {
            System.out.format("%10s%20s%20s%10s%20s%30s%20s", "ID |", "Price |", "First Name |", "Last Name |");
            for (Employee emp: employees) {
                System.out.println(" ");
                System.out.format("%10s%20s%20s%10s%20s%30s%20s", emp.getPersonId() + " |", emp.getPrice() + " |",
                        emp.getfName() + " |", emp.getlName() + " |");

            }
            System.out.println(" ");
        }
        else {
            System.out.println("Нет пользователей!");
        }
    }

    private List<Employee> getEmployee() {
        List<Employee> employees = showPeople();
        return employees;
    }

    public boolean addPerson(Employee employee) {
        boolean isAdded = false;
        try {
            Session session = SessionFactoryImpl.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            session.save(employee);
            tx.commit();
            session.close();
            isAdded = true;
        }
        catch (NoClassDefFoundError e) {
            System.out.println("Exception: " + e);
        }
        return isAdded;
    }*/

}
