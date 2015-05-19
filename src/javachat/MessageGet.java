package javachat;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Отправка списка сообщений клиенту в формате json.
 */

public class MessageGet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Если пользователь авторизирован, то обновить последнее время активности.
        CachedData.getInstance().updateUserActivity(req);

        try {
            Date date = Date.valueOf(req.getParameter("date"));
            //TODO: don't forget to make valid AJAX request date format yyyy-[m]m-[d]d
            DataBase db = DataBase.getInstance();
            Message[] messages = db.getMessages(date);

            if (messages != null) {
                Gson gson = new Gson();
                resp.setContentType("json;charset=utf-8");
                PrintWriter pw = resp.getWriter();
                String result = gson.toJson(messages);
                pw.print(result);
            } else {
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } catch (IllegalArgumentException ex) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}