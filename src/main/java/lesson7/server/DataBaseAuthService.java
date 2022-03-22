package lesson7.server;

import java.sql.*;
import java.util.*;

public class DataBaseAuthService implements AuthService {

    private class Entry {
        private String login;
        private String password;
        private String nick;

        public Entry(String login, String password, String nick) {
            this.login = login;
            this.password = password;
            this.nick = nick;
        }

        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getNick() {
            return nick;
        }

        public void setNick(String nick) {
            this.nick = nick;
        }
    }

    private List<Entry> entries;

    private static Connection connection;
    private static Statement statement;


    @Override
    public void start() throws SQLException {

        connection = DriverManager.getConnection("jdbc:sqlite:users.db");
        statement = connection.createStatement();
        // создали бд если нет
        createTable();
        addUser("log1", "pass1", "valera1");
        addUser("log2", "pass2", "valera2");
        addUser("log3", "pass3", "valera3");

        entries = new ArrayList<>();

        for (int i = 1; i <= 3; i++) {
            entries.add(new Entry(getLogin(i), getPassword(i), getNick(i)));
            entries.add(new Entry(getLogin(i), getPassword(i), getNick(i)));
            entries.add(new Entry(getLogin(i), getPassword(i), getNick(i)));
        }

    }


    private static void createTable() throws SQLException {
        statement.executeUpdate("create table if not exists users (\n" +
                "    id integer primary key autoincrement not null,\n" +
                "    login text not null,\n" +
                "    password text not null,\n" +
                "    nick text not null\n" +
                ");");
    }

    @Override
    public void stop() {

        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void addUser(String login, String password, String nick) {
        try (PreparedStatement ps = connection.prepareStatement(
                "insert into users (login, password, nick)"
                        + " values (?, ?, ?)")) {
            ps.setString(1, login);
            ps.setString(2, password);
            ps.setString(3, nick);
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void changeNick(String login, String password, String newNickname) {
        // создали мапу
        Map<String, String> userMap = new HashMap<>();
        // засунули в мапу логин и пароль
        for (int i = 0; i < entries.size(); i++) {
            userMap.put(entries.get(i).getLogin(), entries.get(i).getPassword());
        }
        // если вводимые данные совпадают
        String valuePass = userMap.get(login);
        try {
            if (valuePass.equals(password)) {
                statement.executeUpdate("update users set nick = '" + newNickname + "' where login = '" + login + "'");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


//    1
//    UPDATE comments
//2
//    SET email = 'zyx@email.com'
//3
//    WHERE name = 'Shivam Mamgain';


    public String getLogin(int count) {
        try (ResultSet rs = statement.executeQuery("select login from users where id = " + count)) {
            while (rs.next()) {
                String login = rs.getString(1);
                return login;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public String getPassword(int count) {
        try (ResultSet rs = statement.executeQuery("select password from users where id = " + count)) {
            while (rs.next()) {
                String password = rs.getString(1);
                return password;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public String getNick(int count) {
        try (ResultSet rs = statement.executeQuery("select nick from users where id = " + count)) {
            while (rs.next()) {
                String nick = rs.getString(1);
                return nick;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Optional<String> getNickByLoginAndPass(String login, String pass) {
        // Сначал из всех записей что есть отфильтровали те, что подходят по логину и паролю. Если что то осталось то из entry достали nick. а findFirst() - если что то есть возвращает этот объект, если нет не возарвщает.
        return entries.stream()
                .filter(entry -> entry.login.equals(login) && entry.password.equals(pass))
                .map(entry -> entry.nick)
                .findFirst();
    }
}
