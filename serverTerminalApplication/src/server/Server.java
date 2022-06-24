package server;

import entity.Employee;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class Server {
    public static final int PORT_one = 1991;
    // public static final int PORT_two = 1992;

    public static void main(String[] args) throws IOException {
        ServerSocket server_one = new ServerSocket(PORT_one);
        //---------------------START HIBERNATE------------------
        ///create session///
        SessionFactoryImpl.createHibernateSession();
        SessionFactoryImpl.getSessionFactory();
        ///end session///
        System.out.println("\n\nRead object");
      //  List<Employee> employees = query.list();
        List<Employee> employees = (List<Employee>)SessionFactoryImpl.getSessionFactory().openSession().createQuery("FROM Employee").list();
        System.out.println(employees);
    //    List<Object[]> rows = query.list();

      /*  for(Object[] row : rows) {
            Employee employee = new Employee();
            employee.setPersonId(Integer.valueOf(row[0].toString()));
            employee.setPrice(row[1].toString());
            employee.setfName(row[2].toString());
            employee.setlName(row[3].toString());
            System.out.println(employee.toString());
        }*/
        //---------------------END HIBERNATE--------------------
        // ServerSocket server_two = new ServerSocket(PORT_two);
        System.out.println("Server one is READY!");
        try {
            System.out.println(server_one);//creat server with port 1993
            while (true) {
                Socket socket = server_one.accept();//created server get socket connected
                System.out.println("Connect a client with server access successfully ");
                System.out.println(socket); //connect server with socket
                try {
                    ServerConnectClient scc = new ServerConnectClient(socket); //creat Object ServerConnectClient
                    scc.readfromClientSwitchCase();
                    //scc.read_Client_toCalculation();
                    //scc.sendtoClientwithCalculation();
                } catch (IOException e) {
                    System.out.println("///Socket - close///");
                    System.out.println("Client " + socket + "is out");
                    socket.close();
                }
            }
        } finally {
            server_one.close();
        }
    }
}
