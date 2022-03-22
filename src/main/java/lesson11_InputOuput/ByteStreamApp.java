package lesson11_InputOuput;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;

public class ByteStreamApp {


    // Не имеет никаго отношений к StreamAPI. Чисто байт. Богатый русский язык.

    // InputStream - чтение
    // read() - возвращает int (беззнаковый). Если возвращает -1 то поток закончился. И согласно Юникоду трансформируется в символ.
    // read(массив байт) - считывает несколько чисел в массив.
    // read (чтение со смещением)
    // available() возвращает больше нуля если есть что почитать, и ноль если нет.
    //...


    // OutputStream - запись
    // имплементирует Closeable - если есть поток, то он должен быть обязательно закрыт. Flushable - метод flush()(сбрасывает состояние "накопителя"), все буферизованыые операции имеют этот метод, обязательно вызывать перед close(). Потому что на момент закрытия, что то могло лежать в памяти, и мы сначала делаем flush() и потом закрываем. В этом случае мы уверены что все данные из буферизованных методов запишутся.
    // write() - пишет
    // write(массив байтов) - пишет

    public static void main(String[] args) {

        // INPUT
        byte[] arr = {65, 66, 67};// A B C на аски
        ByteArrayInputStream in = new ByteArrayInputStream(arr);// передаем массив для чтения в поток (байтовых данных).

        // если в потоке значений больше 0, то ....
        int x = 0;
        while (in.available() > 0) {
            x = in.read();// читаем поток и засовываем в переменную
            System.out.println(x);
        }

        // OUTPUT
        ByteArrayOutputStream out = new ByteArrayOutputStream();// поток ввода. Лежит в куче как динамический массив.
        out.write(65);// пишем
        out.write(66);
        System.out.println(Arrays.toString(out.toByteArray()));// ввыводим

    }

}
