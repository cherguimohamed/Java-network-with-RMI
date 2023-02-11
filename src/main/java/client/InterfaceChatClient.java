package client;

import common.Message;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfaceChatClient extends Remote {

    public void diffuseMessage() throws RemoteException;
    public void displayMessage(Message m) throws RemoteException;
    public void setPseudo(String pseudo) throws RemoteException;
    public void sethour() throws RemoteException ;

    }
