package AddPriceSalesreps.storageJDBC;

import entity.Employee;
import entity.Password;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.internal.SessionFactoryImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import static AddPriceSalesreps.SessionFactoryImpl.createHibernateSession;

public class AddPriceSalesreps {

    public static void main(String[] argv) throws SQLException {

        try {
            Session session = null;
            SessionFactory sf = null;
            
            try {
                Configuration conf = new Configuration();
                conf.configure("resources/hibernate.cfg.xml");
                sf = conf.buildSessionFactory();
                session = sf.openSession();
                System.out.println("Create session" + session);
            } catch (HibernateException exception) {
                System.out.println("Problem creating session factory");
                exception.printStackTrace();
            }
            if (session == null) {
                String name;
                String lastname;
                String price;

                BufferedReader enterPass = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("Enter price of User Card: ");
                price = enterPass.readLine();

                BufferedReader enterUserName = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("Enter First Name User Card: ");
                name = enterUserName.readLine();

                BufferedReader enterLastName = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("Enter Last Name User Card: ");
                lastname = enterLastName.readLine();

                //  Password password = getPasswordForEmp();
                Employee employee = new Employee(price, name, lastname);
                //  addEmployee(price, name, lastname);
            }
            //  String query = "SELECT * FROM authors ORDER BY lastName , firstName ";
         /*   String sql = "INSERT INTO countsals(price, fname, lname) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = JDBC.connection.prepareStatement(sql);
            preparedStatement.setString(1, price);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, lastname);

            int rows = preparedStatement.executeUpdate();*/

            //   System.out.printf("Added %d object\n", rows);
        } catch (HibernateException | IOException exception) {
            System.out.println("Not creating session factory");
            exception.printStackTrace();
        }
    }
    /*    System.out.println("Alphabatized all rows by price, first and last name:");

        ResultSet rs1 = stmt.executeQuery("SELECT * FROM countsals");


        while (rs1.next()) {
            String price1 = rs1.getString("price");
            int price = Integer.parseInt(price1);
            String firstName = rs1.getString("fname");
            String lastName = rs1.getString("lname");
            System.out.println("\t" + price + "\t|\t" + firstName + "\t|\t" + lastName);
        }*/
//finally block used to close resources
    //    JDBC.close();
    // }

    /*  public static Password getPasswordForEmp() {
          Scanner in = new Scanner(System.in);
          Password password = null;
          System.out.print("Enter firstname of Employee: ");
          String fname = in.nextLine();
          System.out.print("Enter lastname of Employee: ");
          String lname = in.nextLine();
          System.out.print("Enter password of Employee: ");
          String passw = in.nextLine();
          //  if(Validator.correctUser(login, password)) {
          //  if(passw.equals("8888")) {
          password = new Password(fname, lname, passw);
          // }
          // else {
          //     System.out.println("This password not your");
          // }
          //}
          return password;
      }*/
    //HIBERNATE
    /* Method to CREATE an employee in the database */
    public static Integer addEmployee(String price, String fname, String lname) {
        SessionFactory factory = null;
        Session session = factory.openSession();
        Transaction tx = null;
        Integer employeeID = null;

        try {
            tx = session.beginTransaction();
            Employee employee = new Employee(price, fname, lname);
            employeeID = (Integer) session.save(employee);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return employeeID;
    }

}
