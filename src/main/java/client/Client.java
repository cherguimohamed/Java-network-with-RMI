package client;

import com.example.demo.HelloApplication;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import server.InterfaceChatServer;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Client extends Application{

    @Override
    public void start(Stage stage) throws IOException, RemoteException{
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("clientInterface.fxml"));
            InterfaceChatClient Chat = null;
            Scene scene = new Scene(fxmlLoader.load(), 320, 240);
            stage.setTitle("Chat(c) Client1");
            stage.setScene(scene);
            stage.show();
            ChatClient chat = fxmlLoader.getController();
            try {
                Naming.rebind("Client1", chat);
                InterfaceChatServer chatServeur = (InterfaceChatServer) Naming.lookup("rmi://127.0.0.1/chatServer");
                System.out.println("Connexion to the server ;) ");
                chatServeur.connect("firstClient", "Client1");
                chat.setPseudo("Client1");
                chat.sethour();

            } catch (MalformedURLException | RemoteException e) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, e);
            }
        }catch (Exception e){}
    }




    public static void main(String args[]) {
        try {
            launch();
        } catch (Exception e){}
    }
}

