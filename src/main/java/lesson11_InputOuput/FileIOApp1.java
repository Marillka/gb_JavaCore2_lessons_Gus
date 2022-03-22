package lesson11_InputOuput;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class FileIOApp1 {


    public static void main(String[] args) {

        File file = new File("demo1.txt");

        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }


        try (FileOutputStream out = new FileOutputStream("demo1.txt")) {
            long start = System.currentTimeMillis();
            for (int i = 0; i < 100_000; i++) {
                out.write(65);// A на Ascii
            }
            System.out.println("t1 = " + (System.currentTimeMillis() - start));// замеряем время

            // Буферизация - как только буфер заполняется до конца бы его пишем (метод flush()) и буефер отчищаем.
            byte[] arr = new byte[100_000];
            Arrays.fill(arr, (byte) 65);// заполняет массив байтами 65
            start = System.currentTimeMillis();
            out.write(arr);
            System.out.println("t2 = " + (System.currentTimeMillis() - start));

            //t1 = 294
            //t2 = 0

            //t1 = 2698
            //t2 = 49


        } catch (IOException exception) {
            exception.printStackTrace();
        }

//        try (FileInputStream inputStream = new FileInputStream("demo1.txt")) {
//            while (inputStream.available() > 0) {
//                System.out.print((char) inputStream.read());
//            }
//        } catch (
//                IOException exception) {
//            exception.printStackTrace();
//        }
//
//    }

    }
}

