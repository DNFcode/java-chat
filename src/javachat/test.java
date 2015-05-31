package javachat;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.*;

/**
 * А в один прекрасный день мы это удалим.
 */
public class test extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("json;charset=utf-8");

        /*BufferedReader bf = req.getReader();
        String[] array = new String[100];
        String line;
        int i = 0;
        while((line = bf.readLine()) != null){
            array[i] = line;
            i++;
        }*/
        Date timeNow = new Date(Calendar.getInstance().getTimeInMillis());
        Date timeNow2 = new Date(Calendar.getInstance().getTimeInMillis() - 2000000);
        final Long time3 = timeNow.getTime() - timeNow2.getTime();
        Gson gson = new Gson();

        Date time = Date.valueOf("2015-10-22");

        System.out.println("<H1>Hello, world! или Привет мир</H1>");

        String username = "1234";
        String password = "1234";
        Date CurrentDate = new Date(Calendar.getInstance().getTimeInMillis());
        //Long filterDate = Current_Date.getTime();
        //Проверка
        DataBase db = DataBase.getInstance();
        try {
            db.saveUser(username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        try {
//            db.saveMessage(username, "aefwegewgewgwewfew");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        try {
//            db.saveMessage(username, "1234gwewfew");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        try {
            db.saveUser("Kate", "Kate");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            System.out.println("Password: " + db.getUserMD5Password(username));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Users: ");
        String user[] = db.getAllUsers();
        //тут вылетело ну так он не может получить юзеров, которых нет в БД
        //и самой таблицы тоже нет
        //она не создаётся хотя как-то же он получил пароль null странности
        if(user.length > 0){
            for(int i=0; i<user.length; i++){
                System.out.println(user[i]);
            }
        }else{
            System.out.println("Users not found!");
        }
        Message[] messages = new Message[0];
        try {
            messages = db.getMessages(CurrentDate);
        } catch (SQLException e) {
            System.out.println("Exception when try to get messages: " + e.getMessage());
        }
        for(int i=0; i<messages.length; i++){
            System.out.println(messages[i].getMessage());
        }
        try {
            db.removeUser("Kate");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Users: ");
        user = db.getAllUsers();
        for(int i=0; i<user.length; i++){
            System.out.println(user[i]);
        }
        db.disconnect();
    }
}