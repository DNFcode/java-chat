package javachat;

import java.sql.Date;
import java.util.Calendar;

/**
 * Created by dnf on 15.05.15.
 */
public class User {

    private final String username;
    private Long lastActivityTime;

    public User(String username, Date lastActivityTime) {
        this.username = username;
        this.lastActivityTime = lastActivityTime.getTime();
    }

    public String getUsername() {
        return username;
    }

    public boolean isOnline() {
        Long timeNow = Calendar.getInstance().getTimeInMillis();
        Long difference = timeNow - lastActivityTime;
        return difference <= 10000 ? true : false;
    }

    public void updateLastActivityTime() {
        lastActivityTime = Calendar.getInstance().getTimeInMillis();
    }
}
