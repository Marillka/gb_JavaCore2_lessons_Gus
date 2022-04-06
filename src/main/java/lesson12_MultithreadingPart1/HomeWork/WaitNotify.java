package lesson12_MultithreadingPart1.HomeWork;


/*
1. Создать три потока, каждый из которых выводит определенную букву (A, B и C) 5 раз (порядок – ABСABСABС). Используйте wait/notify/notifyAll.
 */
public class WaitNotify {
    private final Object lock = new Object();
    private volatile char currentLetter = 'A';

    public static void main(String[] args) {
        WaitNotify waitNotify = new WaitNotify();

        Thread t1 = new Thread(() -> {
            waitNotify.printA();
        });
        Thread t2 = new Thread(() -> {
            waitNotify.printB();
        });
        Thread t3 = new Thread(() -> {
            waitNotify.printC();
        });

        t1.start();
        t2.start();
        t3.start();

    }

    private void printA() {
        synchronized (lock) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (currentLetter == 'B' || currentLetter == 'C') {
                        lock.wait();
                    }
                    System.out.print("A");
                    currentLetter = 'B';
                    lock.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void printB() {
        synchronized (lock) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (currentLetter == 'A' || currentLetter == 'C') {
                        lock.wait();
                    }
                    System.out.print("B");
                    currentLetter = 'C';
                    lock.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void printC() {
        synchronized (lock) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (currentLetter == 'A' || currentLetter == 'B') {
                        lock.wait();
                    }
                    System.out.print("C");
                    currentLetter = 'A';
                    lock.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}