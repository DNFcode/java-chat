package javachat;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
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

        uuser[] array = {new uuser("lol", true), new uuser("WTF", false)};
        String a = gson.toJson(array);
        resp.setStatus(HttpServletResponse.SC_OK);
        PrintWriter pw = resp.getWriter();
        pw.print(a);
        System.out.println("<H1>Hello, world! или Привет мир</H1>");

    }
}

class uuser {
    private String name;
    private Boolean isOnline;

    uuser(String name, Boolean isOnline) {
        this.name = name;
        this.isOnline = isOnline;
    }
}