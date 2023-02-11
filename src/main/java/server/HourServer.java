package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;

public class HourServer extends UnicastRemoteObject implements InterfaceHourServer {

    public String hour;

    public HourServer() throws RemoteException {
        super();
    }

    public void setHour(){
        Date date = new Date();
        hour = date.toString();
    }

    public String getHour() {
        return hour;
    }
}
