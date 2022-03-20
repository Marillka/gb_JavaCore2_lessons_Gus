package lesson3_Collections;

import java.util.*;

public class TreeApp {

    // Comparable - интерфейс (дословно сравнимый), то есть один объект сравним с другим. Свойство объекта, объект может сам себя сравнивать с другими.
    // Comparator - абстрактый класс. Сравнитель, может сравнивать между собой объекты.
    // Мапа, внутри древовидная структура. Чтобы объекты повестить в дерево, они должны быть сравнимы. Нужно научиться их сравнивать.

    public static void main(String[] args) {

        Map<String, Integer> map = new TreeMap<>();

        TreeSet<User> set = new TreeSet<>();
        set.add(new User(10));
        set.add(new User(6));
        set.add(new User(19));

        System.out.println(set);

        // сортировка стрингов под капотом по умолчанию. Лексигографическая сортировка
        TreeSet<String> strings = new TreeSet<>();
        strings.add("b");
        strings.add("bb");
        strings.add("bbb");
        strings.add("a");
        strings.add("aa");
        strings.add("aaa");

        System.out.println(strings);



        // А если нужно отсортировать в кастомном порядке. На помощь приходит компаратор.
        // Для этого раелизуем анонимный класс Comparator. Где реализуем метод compare.
        TreeSet<String> strings1 = new TreeSet<>(new Comparator<String>() {
            // на вход дают две строки. Мы должны сказать какая из них больше.
            @Override
            public int compare(String o1, String o2) {
                int res =  o1.length() - o2.length();
                if (res != 0) {
                    return res;
                }
                return o1.compareTo(o2);
            }
        });
        strings1.add("b");
        strings1.add("bb");
        strings1.add("bbb");
        strings1.add("a");
        strings1.add("aa");
        strings1.add("aaa");

        System.out.println(strings1);

        // Такжк можно компаратор засунуть в лист
//        Collections.sort(list, new Comparator<Object>() {
//        });


    }
}
