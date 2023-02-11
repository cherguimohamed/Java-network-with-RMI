package server;

import client.InterfaceChatClient;
import common.Message;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class ChatServer extends UnicastRemoteObject implements InterfaceChatServer {

    Map<String, InterfaceChatClient> map = new HashMap<>();

    public ChatServer() throws RemoteException {
        super();
    }


    @Override
    public void connect(String pseudo, String url) {
        InterfaceChatClient urlC = null;
        try {
            urlC = (InterfaceChatClient) Naming.lookup("rmi://127.0.0.1/" + url);
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        map.put(pseudo, urlC);
    }

    public void disconnect(String pseudo) {
        map.remove(pseudo);
    }

    public void broadcastMessage(Message msg) throws RemoteException {

        //We can display here the message for the server output

        for (HashMap.Entry<String, InterfaceChatClient> mapentry : map.entrySet()) {
            try {
                mapentry.getValue().displayMessage(msg);
            } catch (Exception e) {}
        }
    }
}
