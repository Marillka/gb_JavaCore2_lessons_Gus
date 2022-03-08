package lesson10_DataBase;

import java.sql.*;
import java.util.Random;

public class JdbcApp {

    private static Connection connection;
    private static Statement statement;// интерфейс выражений. Отправка запросов в БД.
    private static final Random random = new Random();

    public static void main(String[] args) {

        try {
            connect();
            createTable();

            long start = System.currentTimeMillis();
            insertStudents();
            System.out.println("insert statement " + (System.currentTimeMillis() - start + " ms"));

            start = System.currentTimeMillis();
            insertStudentBatch();
            System.out.println("insert batch " + (System.currentTimeMillis() - start + " ms"));

            insertOneStudent("' drop database; '", "35S");
            readData();
            dropTable();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            disconnect();
        }
    }

    private static void connect() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:javadb.db");
//        connection.setAutoCommit(false);// После каждого действия должны будем писать connection.commit();
        //connection().rollback();
        statement = connection.createStatement();
    }

    private static void disconnect() {

        try {
            if (statement != null) {
                statement.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try {
            if (connection != null) {
                connection.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        //close something else
    }

    private static void createTable() throws SQLException {
        statement.executeUpdate("create table if not exists students (\n" +
                "    id integer primary key autoincrement not null,\n" +
                "    name text not null,\n" +
                "    group_name text,\n" +
                "    score integer\n" +
                ");");
    }

    private static void insertStudents() throws SQLException {
        for (int i = 0; i < 100; i++) {
            statement.executeUpdate("insert into students(name, group_name, score)" +
                    " values ('Bob" + i + "', '22', '3');");
        }
    }

    private static void readData() {
        // ResultSet - интерфейс, для чтения из БД. Множество результатов. Отчет с 1, а не с 0.
        try (ResultSet rs = statement.executeQuery("select * from students")) {
            while (rs.next()) {
                System.out.println(
                        rs.getInt(1)
                                + " "
                                + rs.getString("name")
                                + " "
                                + rs.getString(3)
                                + " "
                                + rs.getInt("score"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void dropTable() throws SQLException {
        statement.executeUpdate("drop table students");
    }


// batch - пакетная вставка. Вставка сразу нескольких записей. Работает быстрее.
    private static void insertStudentBatch() {
        try (PreparedStatement ps = connection.prepareStatement(
                "insert into students (name, group_name, score)" +
                " values (?, ?, ?)")) {
            for (int i = 0; i < 100; i++) {
                ps.setString(1,"Jack " + i);
                ps.setString(2, "group " + (10 - i));
                ps.setInt(3, random.nextInt(6));// рандомное чилос от 0 до 5.
                ps.addBatch();// добавили все эти сети в батч (типо пакет)
            }
            ps.executeBatch(); // выполнили батч
        } catch (Exception ex) {
            ex.printStackTrace();
        } ;
    }



    // name = ' drop database; '
    private static void insertOneStudent(String name, String group) throws SQLException {
        // PreparedStatement - выражение, которое можно вызывать с различным набором параметров. НЕОБХОДИМО ЗАКРЫВАТЬ
        try (PreparedStatement ps = connection.prepareStatement(
                "insert into students (name, group_name, score)" +
                        "values (?, ?, 3)")) {
            ps.setString(1, name);
            ps.setString(2, group);
            ps.execute();// выполнить
        } catch (Exception ex) {
            ex.printStackTrace();
        }

//        statement.executeUpdate(
//                "insert into students(name, group_name, score)" +
//                " values ('name', '22', '3');");
    }

    // Транзакция в SQL - набор действий, которые будут выполнены все или не будет выполнено ни одной


}
