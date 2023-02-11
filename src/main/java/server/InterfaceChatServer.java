package server;

import common.Message;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfaceChatServer extends Remote {
    public void connect(String pseudo, String url) throws RemoteException;

    public void disconnect(String pseudo) throws RemoteException;

    public void broadcastMessage(Message msg) throws RemoteException;

    }
