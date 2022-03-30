package lesson13_MultithreadingPart2;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class CountDownApp3 {

    private static Random random = new Random();

    public static void main(String[] args) throws InterruptedException {

        /*
        Защелка. Например детей собираются выводить из школы. Есть 30 потоков, и каждый поток это отдельный первоклассник. И прежде чем их всех вывести на улицу, учитель как центральный поток (поток, который все это синхронизует) ожидает 30 щелчков, грубо говоря, когда каждый из учеников скажет - я готов. И когда все будут готовы, он выполнит какое то действие.
        То есть в какой то точке происходит синхронизация (блокирующий вызов) и мы ожидаем что сколько там потоков будет готово. Они выполнятся и тогда мы сможем перейти дальше.
         */


        /*
        Когда действие совершит мы говорим countDown() - то есть одно действие скидывает.
         */
        CountDownLatch countDownLatch = new CountDownLatch(5);// передаем количество потоков, которые нужно дождаться.
        for (int i = 0; i < 5; i++) {
            int w = i + 1;
            new Thread(() -> {
                try {
                    // задача
                    Thread.sleep(random.nextInt(5) * 1000);// поток будет спать от 0 до 4 секунд.
                    countDownLatch.countDown();// сбрасывает защелку
                    System.out.println("Поток " + w + " готов");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        // Ждем всех
        countDownLatch.await();// блокируюющий, в этот момент код заблокируется. Если ее закоментировать работа завершится сразу, а потом только потоки будут готовы, потому что мы их не ждем.
        System.out.println("Работа завершена");

        //Поток 2 готов
        //Поток 4 готов
        //Поток 5 готов
        //Поток 3 готов
        //Поток 1 готов
        //Работа завершена

        // Если передать в ожидание 1 поток, то можно реализовать синхронизация вместо каких нибудь wait notify.
    }
}
