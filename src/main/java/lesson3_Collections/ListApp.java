package lesson3_Collections;

import java.util.*;

public class ListApp {

    public static void main(String[] args) {
        int[] array = new int[10];
        // массив с элементами меньше 5
//        List<Integer> list = new ArrayList<>();// ссылка на интерфейс = реализация интерфейса - полиморфизм.
        List<Integer> list = new LinkedList<>();



        list.add(10);// в конец добавит 10
        list.add(10);// в конец добавит 10

        list.add(0, 10);// в нулевой элемент добавить 10

        list.remove(0);// удалит нулевой элемент
        list.get(1);// получить первый элемент

        list.size();// количество элементов в листе
        list.contains( 10);// содержит ли список элемент 10?




        Object[] objects = list.toArray();// возвращает Objectы


         for (int x: array) {
             if(x < 5) {
                 list.add(x);
             }
         }

        Collections.sort(list);// отсортирует ваш список

    }
}
