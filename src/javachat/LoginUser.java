package javachat;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginUser extends HttpServlet {
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
                    //TODO: login
                } else {
                    //TODO: wrong password response
                }
            } else {
                if (db.saveUser(username, passwordMD5)) {
                    //TODO: Account created successfully.
                    //TODO: Please login, or your account will be deleted after 5 minutes.
                } else {
                    resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                }
            }
        } catch (NoSuchAlgorithmException ex) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
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