package lesson11_InputOuput;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class WriterApp {

    //reader и writer - работают с массивавами символов. А стримы работают с массивами байт

    public static void main(String[] args) {
        try (Writer writer = new BufferedWriter(new FileWriter("demo.txt"))) {
            writer.append("he he he");
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    // Домашка. Последниее 100 строк чата. Сделать так, чтобы приложение не упало от недостатка оперативки. Подсказка append().
}
