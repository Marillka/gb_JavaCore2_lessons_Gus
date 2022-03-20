package lesson3_Collections;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MapApp {

    //equals
    //hashCode

    // Равные объекты должны иметь одинаковый hashCode.
    // Если hashCode одинаковые, то это не значит что объекты равны. В Математике это называется колизия.
    // ключ уникальный

    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();

        map.put("key", 100);
        map.put("key", 200);// перезапишет

        map.get("key");// вернет 100 или null если нет такого объекта
        map.containsKey("key");// true или false. const
        map.remove("asda");// удалит по ключу
        map.size();// сколько элементов
        map.getOrDefault("key123",10);// дай мне значение по ключю, если такого нет верни 10.

        map.isEmpty();// пустая?

        map.values();// Collection.вернет все значения списком (List)
        map.keySet();// вернет все значения сетом

        // Если заполнятся все бакеты(load factor - 0,75), то она увеличиться в двое. и 16 на которую мы делили превратиться в 32 и произойдет хеширование, то есть из одних бакетов уедут в другие.


        Set<Integer> set = new HashSet<>();// внутри Мапа, у которой ключи это значение сета. Значение это какой то Object.


    }


}
