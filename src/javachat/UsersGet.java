package javachat;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.*;

import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * По запросу клиента отпарвляет список пользователей в зависимости от выбранного запроса.
 * Запрос может быть на онлайн пользователей или на всех пользователей.
 */

public class UsersGet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String mode = req.getParameter("mode");

        //Если пользователь авторизирован, то обновить последнее время активности.
        CachedData.getInstance().updateUserActivity(req);

        if (mode.equals("online") || mode.equals("offline")) {

            CachedData cd = CachedData.getInstance();
            cd.updateUsersStatus();
            User[] users = cd.getUsers();

            Gson gson = new Gson();
            resp.setContentType("json;charset=utf-8");
            PrintWriter pw = resp.getWriter();

            ArrayList<JSONUser> resultUsers = new ArrayList<JSONUser>();

            if (mode.equals("online")) {
                for (User user : users)
                    if (user.isOnline())
                        resultUsers.add(new JSONUser(user.getUsername(), true));
            } else {
                for (User user : users)
                    resultUsers.add(new JSONUser(user.getUsername(), user.isOnline()));
            }

            String result = gson.toJson(resultUsers);
            pw.print(result);
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}


/**
 * This class is just for gson serialise
 */
class JSONUser {

    private String name;
    private Boolean isOnline;

    JSONUser(String name, Boolean isOnline) {
        this.name = name;
        this.isOnline = isOnline;
    }
}