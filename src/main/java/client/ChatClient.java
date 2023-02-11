package client;

import common.Message;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import server.InterfaceChatServer;
import server.InterfaceHourServer;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChatClient extends UnicastRemoteObject implements InterfaceChatClient {

    public String pseudo;

    @FXML
    public TextArea outputMessageTextArea;
    @FXML
    public TextArea inputMessageTextArea;
    @FXML
    public TextArea clientNameArea;
    @FXML
    public Label clientHourLabel;
    @FXML
    public Label serverHourLabel;


    InterfaceChatServer chatServer;
    InterfaceHourServer hourServer;

   public ChatClient() throws RemoteException {
    }
    public void setPseudo(String pseudo) throws RemoteException{
        this.pseudo = pseudo;
        clientNameArea.setText(pseudo);
    }
    public void sethour() throws RemoteException {
        Date date = new Date();
        try {
            hourServer = (InterfaceHourServer) Naming.lookup("rmi://127.0.0.1/hourServer");
        }catch (NotBoundException | MalformedURLException | RemoteException e) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, e);
        }
        serverHourLabel.setText(""+ serverHourLabel.getText()+""+ hourServer.getHour().substring(10,19));
        clientHourLabel.setText(""+ clientHourLabel.getText()+""+date.toString().substring(10,19));
    }

    @FXML
    public void displayMessage(Message m) throws RemoteException{
        outputMessageTextArea.setText(""+outputMessageTextArea.getText()+m.getPseudo()+" :    "+m.getMessage());
    }


    public void diffuseMessage() throws RemoteException {

        try {
            chatServer = (InterfaceChatServer) Naming.lookup("rmi://127.0.0.1/chatServer");
        }catch (NotBoundException | MalformedURLException | RemoteException e) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, e);
        }

        inputMessageTextArea.setOnKeyPressed(keyEvent ->
        {
            if (keyEvent == null) {
            }
            if (keyEvent.getCode() == KeyCode.ENTER) {
                try {
                    Message mm = new Message(pseudo, inputMessageTextArea.getText());
                    chatServer.broadcastMessage(mm);
                } catch (Exception e) {
                }
                inputMessageTextArea.setText("");
            }
        });
    }
}
