package lesson5_Multithreading;

public class JoinApp {

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(i);
            }
        });

        //
        thread.setPriority(10);// от 1 до 10. НЕ РЕКОМЕНДУЕТСЯ К ИСПОЛЬЗОВАНИЮ. Современным ОС плевать на ваш приоритет, сама поставит приоритет.
        //

        thread.start();

        thread.join();// ожидает пока поток завершит свою работу
//        thread.join(3000);// если не завершит свою работу за 3 секунды, то выбросит исключение InterruptedException

        System.out.println("END");


    }
}
