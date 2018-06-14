package baldin.seb;

import java.util.Date;

public class Message {
    private Date data;
    private String body;
    private User sender;

    public Message(String body, User sender) {
        data = new Date();
        this.body = body;
        this.sender = sender;
    }

    public Message(Date date, String body, User sender) {
        this.data = date;
        this.body = body;
        this.sender = sender;
    }

    public Date getData() {
        return data;
    }

    public String getBody() {
        return body;
    }

    public User getSender() {
        return sender;
    }

    @Override
    public String toString() {
        return sender.getUsername() + ": " + body;
    }
}
