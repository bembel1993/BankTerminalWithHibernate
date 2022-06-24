package AddPriceSalesreps;

import ExampleHibernate.User;
import entity.Employee;
import entity.Password;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class SessionFactoryImpl {

    private SessionFactoryImpl() {}
    private static SessionFactory sessionFactory;

    private static Session session;
    public static Session createHibernateSession()
    {
        SessionFactory   sf  = null;
        try {
            Configuration conf = new Configuration();
            conf.configure("resources/hibernate.cfg.xml");

            sf = conf.buildSessionFactory();
            session = sf.openSession();
            System.out.println("Create session");
        } catch(HibernateException exception){
            System.out.println("Problem creating session factory");
            exception.printStackTrace();
        }
        return session;
    }
    //--------------------------------------------------

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(Employee.class);
                configuration.addAnnotatedClass(Password.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

            } catch (Exception e) {
                System.out.println("Exception!" + e);
            }
        }
        return sessionFactory;
    }
}
