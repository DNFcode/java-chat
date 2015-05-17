package javachat;

/**
 * Created by dnf on 12.05.15.
 */

import java.sql.Date;

/**
 * В этом классе применяется singleton, так как база у нас всего одна,
 * то и объектов не может быть много.
 */
public class DataBase {
    static public DataBase getInstance() {
        return new DataBase();
    }

    /**
     * private конструктор для невозможного создания новых объектов БД.
     */
    private DataBase() {

    }

    /**
     * username - имя пользователя
     * MD5password - пароль закодированный в виде хеш-суммы MD5
     * Добавляет пользователя с указанным паролем в БД.
     * Если пользователя удалось добавить то возврашает true, иначе false.
     */
    public boolean saveUser(String username, String MD5password) {
        //IMAGINATION. Просто представь что оно работает.
        System.out.println("User: " + username + " | added to DB");
        return true;
    }

    /**
     * username - имя пользователя
     * Возвращает пароль закодированный в виде хеш-суммы MD5.
     * Если не удалось получить пароль, то возвращает null.
     */
    public String getUserMD5Password(String username) {
        //IMAGINATION. Просто представь что оно работает.
        return "1234";
    }

    /**
     * username - имя пользователя
     * message - сообщение
     * Сохраняет сообщение с указанным пользователем и текущей датой(!) в БД.
     * Если сохранить удалось, то возвращает true, иначе false.
     */
    public boolean saveMessage(String username, String message) {
        //IMAGINATION. Просто представь что оно работает.
        System.out.println("Message: " + message + " | saved");
        return true;
    }

    /**
     * filterDate - дата фильтрации сообщений.
     * Возвращает список всех сообщений, которые были сохранены не позднее укзанной даты.
     * При ошибке возвращает null.
     */
    public Message[] getMessages(Date filterDate) {
        //IMAGINATION. Просто представь что оно работает.
        return new Message[1];
    }

    /**
     * Возвращает список всех пользователей.
     * При ошибке возвращает null.
     */
    public String[] getAllUsers() {
        //IMAGINATION. Просто представь что оно работает.
        return new String[1];
    }
}
