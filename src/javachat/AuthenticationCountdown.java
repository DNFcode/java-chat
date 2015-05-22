package javachat;

import java.sql.SQLException;

/**
 * This class will delete user from data base and cache if
 * user will not login after registration.
 */
public class AuthenticationCountdown implements Runnable {

    private final Long delay;
    private final String username;

    public AuthenticationCountdown(Long delay, String username){
        this.delay = delay;
        this.username = username;
    }

    @Override
    public void run() {
        try {
            this.wait(delay);
            CachedData cd = CachedData.getInstance();
            User user = cd.getUser(username);
            if (!user.isRegistered()) {
                DataBase db = DataBase.getInstance();
                cd.removeUser(user);
                db.removeUser(username);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
