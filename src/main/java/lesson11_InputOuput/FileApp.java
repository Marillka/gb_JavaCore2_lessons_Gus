package lesson11_InputOuput;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class FileApp {
    public static void main(String[] args) throws IOException {

        //1

        // здесь файл не создается. Создается абстракция, для доступа к этому файлу.
//        File file = new File("demo.txt");

        // mkdir() - создаст только одну директорию (создаст последнюю папку)
        // mkdirs() - создаст цепочку директорий (создаст все, чего не хватает)
        File parentDir = new File("dir");
        // если не существуют, то создаст цепочку директорий
        if (!parentDir.exists()) {
            parentDir.mkdirs();
        }

        // создание новой директрории и файла в этой директории
        File file = new File(parentDir, "demo.txt");

        // проверяет существует файл или нет.  true false. по умолчанию будет искать в src
        System.out.println("File exists - " + file.exists());

        // если такого файла нет - создать. IOException
        if (!file.exists()) {
            file.createNewFile();
        }

        // является ли этот файл непосредственно файлом или является директорией
        System.out.println("IsFile " + file.isFile() + " isDirectory - " + file.isDirectory());


        // 2
        // вернет директорию где лежит файл
        System.out.println(file.getParent());

        // getAbsolutePath() - вернет абслютный путь
        System.out.println(file.getAbsolutePath());

        // getAbsoluteFile() - вернет абстрацию куда передаст абсолютный путь.
        System.out.println(file.getAbsoluteFile());

        // listFiles() дает массив директорий
        // listFiles() дает массив файлов
        parentDir.listFiles();

        // вывести список файлов в папке. Если директория - идем дальше, если файл - выводим.
        for (File f : parentDir.listFiles()) {
            if (f.isDirectory()) {
                continue;
            }
            System.out.println(f.getName());
        }

        // вернет длинну в байтах
        file.length();

        // в домашке нужно будет для каждого клиента создать файл, откроем connection file и будем туда писать историю сообщений. Пришло сообщение от сервера, мы его вывели и записали в файл. Если файл с историей есть - будем дописывать. Если файла нет - созадим его и будем писать в него.


    }
}
