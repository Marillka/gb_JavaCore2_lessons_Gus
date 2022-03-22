package lesson11_InputOuput;

import java.io.*;

public class ObjectIOApp {

    public static void main(String[] args) {

        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("demo.txt"))) {
            User user = new User("John", 55);

            // для того чтобы объект смог превратиться в массив байт, мы должны научить его этому или как мимимум сказать что он это умеет. Для этого надо имплементировать Serializable.
            // Сериализация - превращеине объекта в набор байт и наоборот.
            outputStream.writeObject(user);// можем передать объект и он будет записан в виде массива байт.
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
        }


        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("demo.txt"))) {
            User user = (User) in.readObject();// кастование!!!!

            //get bytes

            // toString
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    private static class User implements Serializable {
        private String name;
        private int age;

        public User(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }
    }

}
