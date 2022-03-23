package lesson5_Multithreading;

public class Thread2App {
    public static void main(String[] args) throws InterruptedException {

        Thread.sleep(1000);

        new Thread(() -> {
            try {
                Thread.sleep(3000);// 3 секунды
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
        }).start();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName());
        }).start();


        Thread thread = new Thread(() ->  {
            try {
                Thread.sleep(1000);//
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Daemon " + Thread.currentThread().getName());
        });
        thread.setDaemon(true);// поток демон - фоновый процесс, который Java дожидаться не будет. То есть если у Java закончились все потоки не демоны, то демон просто прибивается. Нужен пока крутится приложение. Например сборщик мусора.
        thread.start();


    }
}
