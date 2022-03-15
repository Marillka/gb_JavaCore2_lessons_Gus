package lesson11_InputOuput;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class FileApp {

    public static void main(String[] args) throws IOException {


        // mkdir() - создаст только одну директорию
        // mkdirs() - создаст цепочку директорий
        File parentDir = new File("dir");
        if (!parentDir.exists()) {
            parentDir.mkdirs();
        }

        // здесь файл не создается. Создается абстракция, для доступа к этому файлу
//        File file = new File("demo.txt");

        // создание новой директрории и файла в этой директории
        File file = new File(parentDir,"demo.txt");

        // проверяет существует файл или нет.  true false
        System.out.println("File exists " + file.exists());

        // если такого файла нет - создать
        if (!file.exists()) {
            file.createNewFile();
        }

        // является ли этот файл непосредственно файлом или является директорией
        System.out.println("IsFile " + file.isFile() + " " + " isDirectory " + file.isDirectory());


        // вернет директорию
        System.out.println(file.getParent());


        // getAbsolutePath() - вернет строчку
        System.out.println(file.getAbsolutePath());

        // getAbsoluteFile() - вернет абстрацию файла, то есть абсолютный путь
        System.out.println(file.getAbsoluteFile());


        // дает массив файлов
        parentDir.listFiles();


        // вывести список файлов в папке. Если директория - идем дальше, если файли - выводим.
        for (File f : parentDir.listFiles()) {
            if (f.isDirectory()) {
                continue;
            }
            System.out.println(f.getName());
        }

        // вернет длинну в байтах
        file.length();

//        00 55 53



    }
}
