package javachat;

import java.sql.Date;
import java.util.Calendar;

/**
 * Класс описывающий пользователя системы.
 * В первую очередь нужен для отслеживания онлайн/оффлайн пользователей.
 */

public class User {

    private final String username;
    private Long lastActivityTime;
    private Boolean registered;

    public User(String username, Date lastActivityTime, Boolean registered) {
        this.username = username;
        this.lastActivityTime = lastActivityTime == null ? null : lastActivityTime.getTime();
        this.registered = registered;
    }

    public String getUsername() {
        return username;
    }

    public boolean isOnline() {
        if(lastActivityTime == null)
            return false;

        Long timeNow = Calendar.getInstance().getTimeInMillis();
        Long difference = timeNow - lastActivityTime;
        return difference <= 10000 ? true : false;
    }

    public boolean isRegistered(){
        return registered;
    }

    public void updateLastActivityTime() {
        lastActivityTime = Calendar.getInstance().getTimeInMillis();
    }

    public void setRegistered(){
        registered = true;
    }
}
