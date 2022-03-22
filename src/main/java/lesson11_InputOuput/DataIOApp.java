package lesson11_InputOuput;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

// класс для удобства работы с потоками ввода вывода. Уже буферизованный и уже умеет работать с простыми типами данных.
public class DataIOApp {

    public static void main(String[] args) {

        // требуется передать поток входной.
        try (DataOutputStream outputStream  = new DataOutputStream(new FileOutputStream("demo.txt"))) {
            outputStream.writeUTF("We love Java");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }



}
