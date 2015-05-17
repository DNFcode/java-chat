package javachat;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MessageSend extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Get data from POST method.
        String username = req.getRemoteUser();
        String message = req.getParameter("message");
        if (message == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        if (username == null && message != null) {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        DataBase db = DataBase.getInstance();
        if (db.saveMessage(username, message)) {
            //TODO: success response
        } else {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
