package ExampleHibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateExample
{
    private  Session  session = null;
    //---------------------------------------------------------------
    private Session createHibernateSession()
    {
        SessionFactory   sf  = null;
     //   ServiceRegistry  srvc = null;
        try {
            Configuration conf = new Configuration();
            conf.configure("resources/hibernate2.cfg.xml");

     //       srvc = new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();
     //       sf = conf.buildSessionFactory(srvc);
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
    private void saveUser()
    {
        if (session == null)
            return;

        User user1 = new User();
        user1.setName ("Ivan");
        user1.setLogin("ivan");

        User user2 = new User();
        user2.setName ("Serg");
        user2.setLogin("serg"  );

        Transaction trans = session.beginTransaction();
        session.save(user1);
        session.save(user2);

        trans.commit();

        System.out.println("user1 : " + user1.toString());
        System.out.println("user2 : " + user2.toString());
    }
    //---------------------------------------------------------------
    public HibernateExample()
    {
        // Создание сессии
        session = createHibernateSession();
        if (session != null) {
            System.out.println("session is created");
            // Добавление записей в таблицу
            saveUser();
            // Закрытие сессии
            session.close();
        } else {
            System.out.println("session is not created");
        }
    }
    //---------------------------------------------------------------
    public static void main(String[] args)
    {
        new HibernateExample();
        System.exit(0);
    }
}
