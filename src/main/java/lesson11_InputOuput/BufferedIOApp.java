package lesson11_InputOuput;

import java.io.*;

// абстракция буфера
public class BufferedIOApp {

    public static void main(String[] args) {

        // Для того чтобы буферизовать поток, ему нужен сам поток, который он собирется буферизовать
        try (
                FileOutputStream fous = new FileOutputStream("demo.txt");
                BufferedOutputStream out = new BufferedOutputStream(fous)// обертка для буферизации. Можно сразу сказать, что массив буфера будет 1_000_000
        ) {
            long start = System.currentTimeMillis();
            for (int i = 0; i < 1_000_000; i++) {
                out.write(65);// A на Ascii
            }
            System.out.println("t1 = " + (System.currentTimeMillis() - start));// замеряем время

        } catch (IOException exception) {
            exception.printStackTrace();
        }


        // Короткая запись
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(new File("demo.txt")))){

            //...
        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }

}
