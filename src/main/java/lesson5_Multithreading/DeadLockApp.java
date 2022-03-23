package lesson5_Multithreading;
public class DeadLockApp {

    public static void main(String[] args) {
        // Взаимная блокировка. То есть когда первый поток заблокировал один объект и хочет заблокировать второй. А второй поток поступил наоборот, он заблокировал второй и хочет заблокировать первый. Крест на крест стоят и ждут друг друга кто первый отпустит. Приложение в этот момент зависло.

        Object lock1 = new Object();
        Object lock2 = new Object();

        new Thread(() -> {
            synchronized (lock1) {
                System.out.println("Thread 1 locked lock1");
                synchronized (lock2) {
                    System.out.println("Thread 2 locked lock2");
                }
            }
        }).start();

        new Thread(() -> {
            synchronized (lock2) {
                System.out.println("Thread 2 locked lock1");
                synchronized (lock1) {
                    System.out.println("Thread 1 locked lock2");
                }
            }
        }).start();



    }
}
