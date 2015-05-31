package javachat;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MessageSend extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Get data from POST method.
        String username = (String) req.getSession().getAttribute("user");
        String message = req.getParameter("message");
        if (message == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        if (username == null) {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        DataBase db = DataBase.getInstance();
        try {
            if (db.saveMessage(username, message)) {
                CachedData.getInstance().updateUserActivity(req);

                resp.setContentType("text/html;charset=utf-8");
                resp.setStatus(HttpServletResponse.SC_OK);
                PrintWriter pw = resp.getWriter();
                pw.print("success");
            } else {
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
