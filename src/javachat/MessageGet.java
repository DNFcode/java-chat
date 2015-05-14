package javachat;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MessageGet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Date date = Date.valueOf(req.getParameter("date"));
            //TODO: don't forget to make valid AJAX request date format yyyy-[m]m-[d]d
            DataBase db = DataBase.getInstance();
            Message[] messages = db.getMessages(date);
            if (messages != null) {
                //TODO: send all messages
            }else {
                //TODO: error response
            }
        }catch (IllegalArgumentException ex){
            //TODO: wrong request error
        }


    }
}