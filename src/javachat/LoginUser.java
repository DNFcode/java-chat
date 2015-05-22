package javachat;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

/**
 * Регистрация и авторизация пользователей в системе.
 */
public class LoginUser extends HttpServlet {

    static private final Long delay = (long) 5*60*1000;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Get data from POST method.
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if (username == null || password == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        try {
            String passwordMD5 = stringToMD5(password);
            DataBase db = DataBase.getInstance();
            String dbMD5password = db.getUserMD5Password(username);
            if (dbMD5password != null) {
                if (dbMD5password.equals(passwordMD5)) {
                    //Авторизация пользователя
                    CachedData cd = CachedData.getInstance();
                    cd.getUser(username).setRegistered();
                    req.login(username, password);
                    resp.sendRedirect("/index.jsp");
                } else {
                    resp.sendError(HttpServletResponse.SC_FORBIDDEN);
                }
            } else {
                if (db.saveUser(username, passwordMD5)) {
                    //Добаление пользователя в кеш
                    CachedData cd = CachedData.getInstance();
                    cd.addUser(new User(username, null, false));
                    //Регистрация пользователя (если через 5 минут он не войдет в систему, то акк будет удален)
                    resp.setStatus(HttpServletResponse.SC_OK);
                    resp.setContentType("text/html;charset=utf-8");
                    PrintWriter pr = resp.getWriter();
                    pr.print("Need to log in to activate account."); //TODO: или чего другое отослать. Я не знаю :(

                    //Начало отсчета до удаления
                    AuthenticationCountdown ac = new AuthenticationCountdown(delay, username);
                    ac.run();
                } else {
                    resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                }
            }
        } catch (NoSuchAlgorithmException ex) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private String stringToMD5(String string) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(string.getBytes());
        byte[] digest = md.digest();
        StringBuffer sb = new StringBuffer();
        for (byte b : digest) {
            sb.append(String.format("%02x", b & 0xff));
        }
        return sb.toString();
    }
}