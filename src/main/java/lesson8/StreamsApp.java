package lesson8;


import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// Stream Api - абстракция для просмотра коллекций и структуры данных(Синтаксический сахар).
public class StreamsApp {

    public static void main(String[] args) {

        Random random = new Random();

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(random.nextInt(100));// вернет от 0 до 99
        }
        System.out.println(list);


//        {//1
//            Stream<Integer> stream = list.stream();// получили Stream интов
////        for(int i : list) {
////            System.out.println();
////        }
//            stream.forEach(a -> System.out.println(a));// a - название переменной внутри Stream.
//        }

        {//2
            long count = list.stream()
//                .skip(50)// пропустили первые 50 чисел
                    .limit(50)// проверяем первые 50, далее стрим закрывается
                    .peek(i -> System.out.println(i))// позволяет заглянуть внутрь стрима.
                    /*
                    оставим в потоке только четные элементы.
                     */
                    .filter(integer -> integer % 2 == 0)
                    .count();
            System.out.println(count);

                /*
                При поиске максимума, должны указать ему в каком порядке сравниваем. Компаратор naturalOrder опирается на метод compareTo() - сравнение
                 */
////                .max(Comparator.naturalOrder()).get();
//                .min(Comparator.naturalOrder()).get();

//                .count();// счетчик

//                .forEach(a -> System.out.println(a));
        }

        {//3
            list.stream()
                    .limit(50)
                    .peek(i -> System.out.println(i))
                    .filter(integer -> integer % 2 == 1);
            // не сработает, потому что нет терминального оператора
        }


        {//4
            List<Integer> list2 = list.stream()
                    .limit(50)
                    .filter(integer -> integer % 2 == 1)
                    .map(integer -> integer * 1000)// замена элементов
                    .collect(Collectors.toList());// метод принимает в себя коллектор - то во что мы собираем наш объект. Собирает в новый лист
            System.out.println(list2);
        }

        {//5
            String str = list.stream()
                    .limit(50)
                    .filter(integer -> integer % 2 == 1)
                    .sorted()//по умолчанию сортирует в указанном порядке, либо принимает компаратор.
                    .distinct()//оставляет только уникальные элементы
                    .map(integer -> integer * 1000)// замена элементов
                    .map(integer -> String.valueOf(integer))
                    .collect(Collectors.joining("<->"));// соединяет строчку джоинером между каждым числом.
            System.out.println(str);
        }


        {//6
            Stream<String> stringStream = Stream.of("aaaa", "bbbb", "cccc", "aaa", "aaaa", "bbb");

            List<String> collect = stringStream
                    .filter(str -> str.length() == 4)// оставить строки длинной 4.
                    .map(str -> str.toUpperCase())
                    .collect(Collectors.toList());
            System.out.println(collect);
        }

        {//7
            Stream<String> stringStream = Stream.of("aaaa", "bbbb", "cccc", "aaa", "aaaa", "bbb");

            Set<String> collect2 = stringStream
                    .filter(str -> str.length() == 4)// оставить строки длинной 4.
                    .map(str -> str.toUpperCase())
                    .collect(Collectors.toSet());
            System.out.println(collect2);
        }

        {//8
            Stream<String> stringStream = Stream.of("aaaa", "bbbb", "cccc", "aaa", "aaaa", "bbb");

            boolean isAll4 = stringStream
//                    .filter(str -> str.length() == 4)
                    .map(str -> str.toUpperCase())
//                            .allMatch(str -> str.length() == 4);// все длинной четыре?
                    .noneMatch(str -> str.length() == 5);// нету ни одного элемента длинны пять?
            System.out.println(isAll4);
        }

        {//9
            Stream<String> stringStream = Stream.of("aaaa", "bbbb", "cccc", "aaa", "aaaa", "bbb");

            Map<String, Integer> map = stringStream.
                    collect(Collectors.toMap(str -> str, str -> 1, (v1, v2) -> v1 + 1));
            System.out.println(map);
        }
//{aaa=1, bbb=1, aaaa=2, cccc=1, bbbb=1}
    }


}
