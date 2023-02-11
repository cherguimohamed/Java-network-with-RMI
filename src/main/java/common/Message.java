package common;

import java.io.Serializable;

public class Message implements Serializable {
    private String _msg;
    private String _pseudo;

    public Message(String pseudo, String msg) {
        _msg = msg;
        _pseudo = pseudo;
    }

    public String getMessage() {
        return _msg;
    }

    public String getPseudo() {
        return _pseudo;
    }
}


/**
 *Pourquoi la classe common.Message doit implementer l'interface java.io.serializable
 *La communication entre les clients et le serveur se fait implicitement par des sockets,
 * les messages donc s'envoie octet par octet, l'interface java.io.serializable assure le comportement souhaité pour la classe Message.java
 */

