package server;

import java.rmi.*;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {

    public static void main(String args[]) {

        try {
            LocateRegistry.createRegistry(1099);
        } catch (Exception e) {
        }
        try {
            ChatServer Chat = new ChatServer();
            HourServer hour = new HourServer();
            hour.setHour();
            Naming.rebind("hourServer", hour);
            Naming.rebind("chatServer", Chat);
            System.out.println("The server is ready ;) ");
        } catch (RemoteException | MalformedURLException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
