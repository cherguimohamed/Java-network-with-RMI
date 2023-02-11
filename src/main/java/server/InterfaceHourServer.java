package server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfaceHourServer extends Remote {
    public String getHour() throws RemoteException;
    public void setHour() throws RemoteException;
}
