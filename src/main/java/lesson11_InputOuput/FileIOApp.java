package lesson11_InputOuput;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileIOApp {

    public static void main(String[] args) {

        // FileOutputStream - если передаем String - это путь к файлу.
        // передаем объект(абстракцию типа File) - это тоже самое, только указатель через файл.
        File file = new File("demo.txt");

//        try(FileOutputStream out = new FileOutputStream(file)) {
        try (FileOutputStream out = new FileOutputStream("demo.txt")) {
            out.write("Java 3 we are the best".getBytes());// getBytes() - превращает строку в массив байт
        } catch (IOException exception) {
            exception.printStackTrace();
        }


        try (FileInputStream inputStream = new FileInputStream("demo.txt")) {
            while (inputStream.available() > 0) {
//                System.out.print(inputStream.read());
                System.out.print((char) inputStream.read());
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }




    }
}
