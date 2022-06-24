package AddPriceSalesreps;

import entity.Employee;
import entity.Password;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HiberAddPrice
{
    private Session session = null;
    //---------------------------------------------------------------
    private Session createHibernateSession()
    {
       // ServiceRegistry  srvc = null;
        SessionFactory sf  = null;
        try {
            Configuration conf = new Configuration();
            conf.configure("resources/hibernate.cfg.xml");
       //     srvc = new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();
         //         sf = conf.buildSessionFactory(srvc);
            sf = conf.buildSessionFactory();
            session = sf.openSession();
            System.out.println("Create session");
        } catch(HibernateException exception){
            System.out.println("Problem creating session factory");
            exception.printStackTrace();
        }
        return session;
    }
    //---------------Add Salesreps for Employer
    private void saveUser() throws IOException {
        if (session == null)
            return;

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
        employee.setPrice(price);
        employee.setfName(name);
        employee.setlName(lastname);

        Transaction trans = session.beginTransaction();
        session.save(employee);

        trans.commit();

        System.out.println("user of card: " + employee.toString());

    }
    //-----------Add User for Bank, where is PASSWORD
    private void addPasswUser() throws IOException {
    if (session == null)
        return;

    String name;
    String lastname;
    String passw;

    BufferedReader enterName = new BufferedReader(new InputStreamReader(System.in));
    System.out.println("Enter first name of User Card: ");
    name = enterName.readLine();

    BufferedReader enterLastName = new BufferedReader(new InputStreamReader(System.in));
    System.out.println("Enter last name of User Card: ");
    lastname = enterLastName.readLine();

    BufferedReader enterPass = new BufferedReader(new InputStreamReader(System.in));
    System.out.println("Enter password of User Card: ");
    passw = enterPass.readLine();

    //  Password password = getPasswordForEmp();
    Password password = new Password(name, lastname, passw);
    password.setName(name);
    password.setLastname(lastname);
    password.setPassword(passw);

    Transaction trans = session.beginTransaction();
    session.save(password);

    trans.commit();

    System.out.println("user of card with password: " + password.toString());
    }
    //---------------------------------------------------------------
    public HiberAddPrice() throws IOException {
        // Создание сессии
        session = createHibernateSession();
        if (session != null) {
            System.out.println("session is created");
            // Добавление записей в таблицу
            saveUser();
            addPasswUser();
            // Закрытие сессии
            session.close();
        } else {
            System.out.println("session is not created");
        }
    }
    //---------------------------------------------------------------
    public static void main(String[] args) throws IOException {
        new HiberAddPrice();
        System.exit(0);
    }
}