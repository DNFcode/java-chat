package javachat;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Класс для более удобной передачи сообщений.
 */
public class Message {
    private final String username;
    private final String stringDate;
    private final Long longDate;
    private final String message;

    public Message(String username, Date date, String message) {
        DateFormat df = new SimpleDateFormat("HH:mm:ss");
        this.username = username;
        this.stringDate = df.format(date);
        this.longDate = date.getTime();
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public Date getDate() {
        return new Date(longDate);
    }

    public String getMessage() {
        return message;
    }
}
