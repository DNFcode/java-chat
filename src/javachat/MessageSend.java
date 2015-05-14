package javachat;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MessageSend extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Get data from POST method.
        String username = req.getRemoteUser();
        String message = req.getParameter("message");
        if(message == null){
            //TODO: wrong request error
            return;
        }
        if(username == null && message != null){
            //TODO: you must log in first.
            return;
        }
        DataBase db = DataBase.getInstance();
        if(db.saveMessage(username, message)){
            //TODO: success response
            return;
        }else{
            //TODO: error response
            return;
        }
    }
}
