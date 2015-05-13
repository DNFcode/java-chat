package javachat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class test extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");

        BufferedReader bf = req.getReader();
        String[] array = new String[100];
        String line;
        int i = 0;
        while((line = bf.readLine()) != null){
            array[i] = line;
            i++;
        }
        System.out.println("<H1>Hello, world! или Привет мир</H1>");

    }
}