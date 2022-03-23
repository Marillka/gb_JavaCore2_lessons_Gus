package lesson5_Multithreading;

public class TaskApp {


    public static void main(String[] args) {
        // Поток описывается классом Thread
        Thread thread1 = new Thread(new MyRunnable());
        Thread thread2 = new Thread(new MyRunnable());
//        thread1.run();// выполним его в этом потоке. Здесь и сейчас.
        thread1.start();
        thread2.start();

        System.out.println("Я все запустил и уже закончил. Жду пока закончат остальные");// main -> t1 и -> t2

        Thread thread3 = new MyThread();
        thread3.start();


        // 3) Анонимный класс (Anonymous)
        Thread thread4 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 10; i <= 15; i++) {
                    System.out.println("Thread out: " + i);
                }
            }
            });
        thread4.start();

        // 4) lambda
        Thread thread5 = new Thread(() -> {
            for (int i = 20; i <= 25; i++) {
                System.out.println("Thread out: " + i);
            }
        });
        thread5.start();


    }


    // Способы запуска потоков
    // 1) Реализация интерфейса Runnable
    public static class MyRunnable implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                System.out.println("Thread out: " + i);
            }
        }
    }

    // 2) Наследование от класса Thread. Должны переопределить метод run().
    public static class MyThread extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println("Thread out: " + i);
            }
        }
    }



}
