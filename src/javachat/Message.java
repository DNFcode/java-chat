package javachat;

import java.sql.Date;

/**
 * Класс для более удобной передачи сообщений.
 */
public class Message {
    private final String username;
    private final Date date;
    private final String message;
    private final Long id;

    public Message(String username, Date date, String message, Long id) {
        this.username = username;
        this.date = date;
        this.message = message;
        this.id = id;
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

    public Long getID(){
        return id;
    }
}
