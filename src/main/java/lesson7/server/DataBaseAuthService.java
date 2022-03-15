//package lesson7.server;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//public class DataBaseAuthService implements AuthService {
//
//    private static Connection connection;
//    private static Statement statement;
//
//
//    private List<Entry> entries;
//
////    public DataBaseAuthService() {
////        entries = new ArrayList<>();
////
////        for (int i = 0; i < dbLength; i++) {
////
////        }
////        entries.add(new Entry())
////    }
//
//
//
//    public void createTable() throws SQLException {
//        statement.executeUpdate("create table if not exists users ("
//                + "id integer primary key autoincrement not null,"
//                + "login text not null,"
//                + "password text not null,"
//                + "nick text not null unique");
//    }
//
//    @Override
//    public void start() throws SQLException {
//        connection = DriverManager.getConnection("jdbc:sqlite:users.db");
//        statement = connection.createStatement();
//        createTable();
//        addUser("log1", "pass1", "valera1");
//        addUser("log2", "pass2", "valera2");
//        addUser("log3", "pass3", "valera3");
//
//    }
//
//    @Override
//    public void stop() {
//
//        try {
//            if (statement != null) {
//                statement.close();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            if (connection != null) {
//                connection.close();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    public void addUser(String login, String password, String nick) {
//        try (PreparedStatement ps = connection.prepareStatement(
//                "insert into users (login, password, nick)"
//                        + " values (?, ?, ?)")) {
//            ps.setString(1, login);
//            ps.setString(2, password);
//            ps.setString(3, nick);
//            ps.execute();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
////    @Override
////    public Optional<String> getNickByLoginAndPass(String login, String pass) {
////        try (ResultSet rs = statement.executeQuery("select * from users")) {
////            while (rs.next()) {
////
////            }
////        } catch (SQLException e) {
////            e.printStackTrace();
////        }
////    }
//
//    public void getLogin() {
//
//    }
//
//    public void getPassword() {
//
//    }
//
//    public void getNick() {
//
//    }
//
////    public String dbLength() {
////        try (ResultSet rs = statement.executeQuery("select * from users where id = max")) {
////            while (rs.next()) {
////                int length = rs.getInt(1);
////            }
////        } catch (Exception ex) {
////            ex.printStackTrace();
////        }
////    }
//
//
//
//    private class Entry {
//        private String login;
//        private String password;
//        private String nick;
//
//        public Entry(String login, String password, String nick) {
//            this.login = login;
//            this.password = password;
//            this.nick = nick;
//        }
//    }
//
//}
