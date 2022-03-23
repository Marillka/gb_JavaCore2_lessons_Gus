package lesson5_Multithreading;

import java.util.concurrent.atomic.AtomicInteger;

public class RaceConditionApp {

    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();

        Object lock = new Object();


         Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
//                synchronized (lock) {// или так. Синхронизируемся не на счетчике а на объекте. Пока я занял его, ты его не возьвешь и не сделаешь со счетчиком.
                    counter.increment();
//                }

            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
//                synchronized (lock) {
                    counter.increment();
//                }
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();


        System.out.println("Counter = " + counter.getValue());
    }

//    public static class Counter {
//        private int value;
//
//        public int getValue() {
//            return value;
//        }
//
//        public  void increment() {// пока поток работает, объект заблокирован. Точка синхронизации это всегда объект.
////            synchronized (this) { // или так
////                value++;
////            }
//            value++;
//        }
//    }


    // В java есть типы, которые сами выполняют синхронизаци. Они атомарны. Атомарность - выполняется все действие целиком (разом).
    public static class Counter {
        private AtomicInteger value;

        public Counter() {
            this.value = new AtomicInteger(0);
        }

        public int getValue() {
            return value.intValue();
        }

        public  void increment() {
            value.incrementAndGet();
        }
    }

}
