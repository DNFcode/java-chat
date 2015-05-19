package javachat;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Это синглтон!
 */
public class CachedData {

    private static CachedData instance = null;
    private ArrayList<User> users;

    public static CachedData getInstance() {
        if(instance == null)
            instance = new CachedData();
        return instance;
    }

    private CachedData() {
        users = new ArrayList<User>();
        DataBase db = DataBase.getInstance();
        String[] allUsers = db.getAllUsers();
        for(String user:allUsers)
            users.add(new User(user, null, true));
    }

    public User[] getUsers() {
        return users.toArray(new User[users.size()]);
    }

    public void updateUsersStatus() {
        /*for (int i = 0; i < users.size(); i++) {
            users.get(i).updateLastActivityTime(); //TODO: может быть get(i) передает не ссылку
        }*/
        //TODO: понятия не имею работает ли это. Если данные не по ссылке передаются то все плохо.
        for(User user: users)
            user.updateLastActivityTime();
    }

    public void addUser(User user){
        users.add(user);
    }

    public User getUser(String username){
        for (User user:users)
            if (user.getUsername().equals(username))
                return user;
        return null;
    }

    public void updateUserActivity(HttpServletRequest req){
        String username = req.getRemoteUser();
        if (username != null){
            CachedData cd = CachedData.getInstance();
            cd.getUser(username).updateLastActivityTime();
        }
    }

    public void removeUser(User user){
        users.remove(user);
    }
}
