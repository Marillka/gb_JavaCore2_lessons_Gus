package lesson5_Multithreading;

public class WaitNotifyApp {

    Object lock = new Object();

    private String currentLetter = "A";

    public static void main(String[] args) {

        WaitNotifyApp app = new WaitNotifyApp();

        // ABABABABABABABABABABABAB
        Thread t1 = new Thread(() -> {
            app.printA();
        });

        Thread t2 = new Thread(() -> {
            app.printB();
        });

        t1.start();
        t2.start();
    }

    public void printB() {
        synchronized (lock) {
            try {
                for (int i = 0; i < 10; i++) {
                    while (!currentLetter.equals("B")) {
                        lock.wait();//wait от Object. Спи, мы тебя разбудим. Пока от нас "A" не требуется - мы спим. Иначе мы пишем А
                    }
                    System.out.print("B");
                    currentLetter = "A";
                    lock.notifyAll();// будет wait. Все кто заблокирован на этом объекте - забирайте - готово.
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void printA() {
        synchronized (lock) {// синхронизируемся на объекте.
            try {
                for (int i = 0; i < 10; i++) {
                    while (!currentLetter.equals("A")) {
                        lock.wait();//wait от Object. Спи, мы тебя разбудим. Пока от нас "A" не требуется - мы спим. Иначе мы пишем А. Отпускает lock (обхъект) и дает ему работать с другими потоками, но запоминает где он остановился.
                    }
                    System.out.print("A");
                    currentLetter = "B";
                    lock.notifyAll();// notify() - будет один случайный поток. all будет все потоки, которые ожидают на этом локи.
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
