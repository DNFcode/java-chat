package javachat;

import java.util.ArrayList;

/**
 * Created by dnf on 12.05.15.
 */
public class CachedData {

    private ArrayList<User> users;

    static CachedData getInstance() {
        return new CachedData();
    }

    private CachedData() {

    }

    public User[] getUsers() {
        return users.toArray(new User[users.size()]);
    }

    public void updateUsersStatus() {
        for (int i = 0; i < users.size(); i++) {
            users.get(i).updateLastActivityTime(); //TODO: может быть get(i) передает не ссылку
        }
    }
}
