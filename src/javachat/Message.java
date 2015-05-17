package javachat;

import java.sql.Date;

/**
 * Created by dnf on 14.05.15.
 */
public class Message {
    private final String username;
    private final Date date;
    private final String message;

    public Message(String username, Date date, String message) {
        this.username = username;
        this.date = date;
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public Date getDate() {
        return date;
    }

    public String getMessage() {
        return message;
    }
}
