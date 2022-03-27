package lesson12_MultithreadingPart1;

/*
Взаимная блокировка - когда один поток запустился, берет лок1 и ожидает получить второй.
Другой поток в это время берет лок 2 и ожидает получения первого.
 */
public class DeadlockApp {

    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();

    public static void main(String[] args) {

        new ThreadOne().start();
        new ThreadTwo().start();
    }

    // первый поток
    private static class ThreadOne extends Thread {
        @Override
        public void run() {
            // синхронихизируеся на первом объекте и ждем секунду
            synchronized (lock1) {
                System.out.println(Thread.currentThread().getName() + " locked lock1");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // выводим текст
                System.out.println(Thread.currentThread().getName() + " waiting for lock2");

                synchronized (lock2) {
                    System.out.println(Thread.currentThread().getName() + " locked lock2");
                }
            }
        }
    }

    // второй поток
    private static class ThreadTwo extends Thread {
        @Override
        public void run() {
            // синхронихизируеся на первом объекте и ждем секунду
            synchronized (lock2) {
                System.out.println(Thread.currentThread().getName() + " locked lock2");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // выводим текст
                System.out.println(Thread.currentThread().getName() + " waiting for lock1");

                synchronized (lock1) {
                    System.out.println(Thread.currentThread().getName() + " locked lock1");
                }
            }
        }
    }

    //Thread-0 locked lock1
    //Thread-1 locked lock2
    //Thread-1 waiting for lock1
    //Thread-0 waiting for lock2


    /*
    Способы борьбы с deadlock-ом
    1) Стараемся брать локи в одном и том же порядке. То есть берем локи от меньшего к большему. Должны получать lock1, потом получать lock2.
    Остальные на следующем уроке.
     */

}
