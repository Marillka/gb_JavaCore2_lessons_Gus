package lesson12_MultithreadingPart1;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class CounterApp2 {

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

            this.value++;
        }
    }

    private static class DoubleCounter {

        private Object lock1 = new Object();
        private Object lock2 = new Object();

        private int first;
        private int second;

        public DoubleCounter() {
            this.first = 0;
            this.second = 0;
        }

        @Override
        public String toString() {
            return "DoubleCounter{" +
                    "first=" + first +
                    ", second=" + second +
                    '}';
        }

        //        public synchronized void incrementFirst() {
        public void incrementFirst() {
            // вот этот блок называется КРИТИЧЕСКАЯ СЕКЦИЯ - секция, которая выполняется одним потоком и является блокирующей для всех остальных.
            synchronized (lock1) {
                this.first++;
            }
        }

        //        public synchronized void incrementSecond() {
        public synchronized void incrementSecond() {
            synchronized (lock2) {
                this.second++;
            }
        }

    }

    public static void main(String[] args) throws InterruptedException {

        final Counter counter = new Counter();

        AtomicInteger atomicInteger = new AtomicInteger(0);

        AtomicReference<String> atomicReference = new AtomicReference<>();

        DoubleCounter doubleCounter = new DoubleCounter();

        Runnable runnable = () -> {
            for (int i = 0; i < 10000; i++) {
                atomicInteger.incrementAndGet();
                counter.increment();


                /*
                Первая проблема, метод не синхронизированный
                DoubleCounter{first=19927, second=9971}
                DoubleCounter{first=19969, second=9969}

                 Ага, засинхронизировали
                 DoubleCounter{first=20000, second=10000}
                 Но как никрути, между двумя синхронайзами есть разрыв.
                 Во вторых срадает пропусканая способность, когда мы шелкаем первым счетчиком, мы не можем щелкать первым и вторым. Когда мы вызваем синхронайз на таком вот методе - блокируется объект (точка синхронизации объект).
                 Для повышения пропускно способности можно использовать объекты для блокировка. И испльзовать не синхронизированный метод, а написать synchronized внутри метода.
                 За счет блокировки на объектах синхронизация выполняется не на одном объекте, а на двух разных. Повышается пропусканая способность.
                 */
                doubleCounter.incrementFirst();
                ;
                if (i % 2 == 0) {
                    doubleCounter.incrementSecond();
                }

                /*
                Если мы хотим одновременно (атомарно) увеличить значение и первого и второго (изменились синхронно, либо оба изменились, либо ни один не изменился).
                В этом случае нужна снхронизация на объекте. Потому что мы меняем объект и мы должны быть уверены что никто к нему доступа не имеет, пока мы свои изменения не закончим. Если такой необходимости нет, то делаем синхронизацию на объекте, как в прошлом примере.
                 */
                synchronized (doubleCounter) {
                    doubleCounter.incrementFirst();
                    doubleCounter.incrementSecond();
                }
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
        System.out.println(doubleCounter.toString());
    }
}
