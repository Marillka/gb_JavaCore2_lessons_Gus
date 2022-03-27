package lesson12_MultithreadingPart1;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class CounterApp1 {

    private static class Counter {
        private int value;

        public Counter() {
            this.value = 0;
        }

        public Counter(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public synchronized void increment() {
            /*
            чтобы это операция выполнилась 3 нужно операций процессора (3 такта).
            1) Прочитать значение.
            2) Увеличить значение.
            3) Записать значение.
            В любой момент процессорное время может закончиться.
            Мы прочитали значение и процессорное время закончилось и другой поток идет и тоже читает значение.
            Мы оба прочитали значение допустим 10.
            Дальше мы оба увеличиваем значение на единицу. Оба получаем 11. И оба пишем 11 в ячейку.
            ИТОГО 1 ЕДИНИЦА ПОТЕРЯНА.

            Что делаеть AtomicInteger - это обертка над примитивом, которая позволяет выполнять действие с переменной атомарно (атомарность - неделимость, то есть выполняются все 3 действия разом, похоже на транзакцию, но не совсем то. Транзакция может откатиться).
             */
            this.value++;
        }
    }

    public static void main(String[] args) throws InterruptedException {

        final Counter counter = new Counter();

        /*
        Аналогичный counter представлен в пакете  java.util.concurrent
        AtomicInteger - класс, лежит в пакете java.util.concurrent.atomic. Все операции выполняются разом, прочитал, увеличил, записал.
        Обладаем болшей пропускной способностью, за счет того что у него меньше блокировок, по сравнению с synchronized.
         */
        AtomicInteger atomicInteger = new AtomicInteger(0);

        /*
        AtomicReference - должен быть параметризованыый. Потому что это параметризованная атомарная ссылка.
        Когда мы работаем с int-ом. Мы такие прочитали число, прочитали число, поменяли, поменяли и записали.
        Со ссылкой может быть тоже самое, если у нас есть строка и мы ссылаемся на эту строчку. Мы оба ее прочитали, оба ее поменяли и оба положили новую ссылку. Победит тот, кто положил ссылку последний. А второй объект просто потерятся.
        Для того чтобы не было таких проблем с изменением ссылок, когда мы один объект присваиваем другому используется класс AtomicReference, который все эти действия тоже делает отомарно.
         */
        AtomicReference<String> atomicReference = new AtomicReference<>();

        Runnable runnable = () -> {
            for (int i = 0; i < 10000; i++) {
                atomicInteger.incrementAndGet();// по сути означает ++value из класса counter
                counter.increment();
            }
        };

        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println(counter.getValue());
        System.out.println(atomicInteger.get());
    }
}
