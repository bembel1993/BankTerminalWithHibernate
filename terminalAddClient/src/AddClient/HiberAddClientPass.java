package AddClient;

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

public class HiberAddClientPass {
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
    //---------------------------------------------------------------
    private void saveUser() throws IOException {
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
    public HiberAddClientPass() throws IOException {
        // ???????????????? ????????????
        session = createHibernateSession();
        if (session != null) {
            System.out.println("session is created");
            // ???????????????????? ?????????????? ?? ??????????????
            saveUser();
            // ???????????????? ????????????
            session.close();
        } else {
            System.out.println("session is not created");
        }
    }
    //---------------------------------------------------------------
    public static void main(String[] args) throws IOException {
        new HiberAddClientPass();
        System.exit(0);
    }
}
