package javachat;

/**
 * Created by dnf on 12.05.15.
 */

import java.sql.*;
import java.util.ArrayList;
import java.util.Locale;

/**
 * В этом классе применяется singleton, так как база у нас всего одна,
 * то и объектов не может быть много.
 */
public class DataBase {

    static private DataBase instance = null;
    private Connection conn;

    static public DataBase getInstance() {
        System.out.println("Connection run");
        Locale.setDefault(Locale.ENGLISH);
        if(instance == null)
            instance = new DataBase();
        return instance;
    }

    /**
     * private конструктор для невозможного создания новых объектов БД.
     */
    //TODO: При инициализации нужно создавать таблицы если их нет :3
    private DataBase() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@de.ifmo.ru:3521:xe";
            Locale.setDefault(Locale.ENGLISH);
            //НЕЛЬЗЯ КОММИТИТЬ С ПАРОЛЕМ И ЮЗЕРОМ
            conn = DriverManager.getConnection(url, "u0006", "pip902");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Проверяет есть ли пользователь с таким логином в БД
     * username - имя пользователя
     */
    public boolean userExists(String username)    {//Work
        try {
            PreparedStatement stmt = null;
            ResultSet rs = null;
            String query = "SELECT login FROM Users WHERE login = ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            rs = stmt.executeQuery();
            if(rs.next()){
                return true;
            }
            stmt.close();
            rs.close();
            return false;
        }
        catch (Exception e){
            System.out.println("Database exception.");
            return false;
        }
    }

    /**
     * username - имя пользователя
     * MD5password - пароль закодированный в виде хеш-суммы MD5
     * Добавляет пользователя с указанным паролем в БД.
     * Если пользователя удалось добавить то возврашает true, иначе false.
     */
    public boolean saveUser(String username, String MD5password) throws SQLException {//Work
        if(userExists(username)){
            System.out.println("Пользователь с таким именем уже существует!");
            return false;
        } else{
            PreparedStatement stmt = conn.prepareStatement(
                    "insert into users values(?,?)");
            stmt.setString(1, username);
            stmt.setString(2, MD5password);
            stmt.executeUpdate();
            stmt.close();
        }
        return true;
    }

    /**
     * username - имя пользователя
     * Возвращает пароль закодированный в виде хеш-суммы MD5.
     * Если не удалось получить пароль, то возвращает null.
     */
    public String getUserMD5Password(String username) throws SQLException {//Work
        PreparedStatement stmt = null;
        String MD5Password = null;
        String query = "SELECT password FROM Users WHERE login = ?";
        if(userExists(username)) {
            try {
                stmt = conn.prepareStatement(query);
                stmt.setString(1, username);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    MD5Password = rs.getString(1);
                    System.out.println(MD5Password);
                }
                System.out.println("password of user = " + username + " = " + MD5Password);
            } catch (SQLException e) {
                System.out.println("Database exception.");
                return null;
            } finally {
                if (stmt != null) {
                    stmt.close();
                }
            }
            return MD5Password;
        } else{
            System.out.println("Такого пользователя не существует!");
            return null;
        }
    }

    /**
     * username - имя пользователя
     * message - сообщение
     * Сохраняет сообщение с указанным пользователем и текущей датой(!) в БД.
     * Если сохранить удалось, то возвращает true, иначе false.
     */
    public boolean saveMessage(String username, String message) throws SQLException {//Work
        java.util.Date Current_Date = new java.util.Date();
        System.out.println("Date of save: " + Current_Date);
        long date = Current_Date.getTime();
        if(userExists(username)){
            PreparedStatement stmt = conn.prepareStatement(
                    "insert into Message values(?,?,?)");
            stmt.setString(1, username);
            stmt.setString(2, message);
            stmt.setLong(3, date);
            stmt.executeUpdate();
            stmt.close();
        } else{
            System.out.println("Такого пользователя не существует!");
        }
        return true;
    }

    /**
     * filterDate - дата фильтрации сообщений. Передаётся сразу в long.
     * Возвращает список всех сообщений, которые были сохранены не позднее указанной даты.
     * При ошибке возвращает null.
     */

    //TODO: Возвращать нужно Message[]
    //TODO: И да, на вход будет подаваться не long, а Date. Ну его же всегда можно перевести в long ;)
    public String[] getMessages(long filterDate) throws SQLException { //Work
        ArrayList<String> messages = new ArrayList<String>();
        System.out.println("DATE: " + filterDate);
        PreparedStatement stmt = null;
        String query = "SELECT message FROM Message WHERE date_mes < ?";
        stmt = conn.prepareStatement(query);
        stmt.setLong(1, filterDate);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            messages.add(rs.getString("message"));
        }
        rs.close();
        stmt.close();
        int n = messages.size();
        String user[] = (String[])messages.toArray(new String[n]);
        return user;
    }

    public Message[] getMessages(Long ID){
        //TODO: Перегрузка метода повыше, только здесь нужно вернуть все сообщения, ID у которых
        //TODO: меньше чем указанный.
    }

    /**
     * Возвращает список всех пользователей.
     * При ошибке возвращает null.
     */
    public String[] getAllUsers() { //Work
        ArrayList<String> login = new ArrayList<String>();
        PreparedStatement stmt = null;
        String query = "SELECT login FROM Users";
        try {
            stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                login.add(rs.getString("login"));
            }
            rs.close();
            stmt.close();
        }
        catch (Exception e){
            System.out.println("Database exception.");
            return null;
        }
        int n = login.size();
        String user[] = (String[])login.toArray(new String[n]);
        return user;
    }

    /**
     * Удаляет информацию о пользователе из БД по его имени.
     */
    public void removeUser(String username) throws SQLException {
            try {
                PreparedStatement stmt = conn.prepareStatement(
                        "DELETE FROM Users WHERE login = ?");
                stmt.setString(1, username);
                stmt.executeUpdate();
                stmt.close();
                System.out.println("User: " + username + " deleted");

                PreparedStatement stmt2 = conn.prepareStatement(
                        "DELETE FROM Message WHERE login = ?");
                stmt2.setString(1, username);
                stmt2.executeUpdate();
                stmt2.close();
                System.out.println("Message deleted");
            } catch (Exception e) {
                System.out.println("Database exception.");
            }
    }

    /**
     * Метод для разрыва соединения с БД
     */
    public void disconnect() {
        try {
            conn.close();
            System.out.println("Disconnect");
        } catch (SQLException ex) {
            System.out.println("Database exception.");
        }
    }
}