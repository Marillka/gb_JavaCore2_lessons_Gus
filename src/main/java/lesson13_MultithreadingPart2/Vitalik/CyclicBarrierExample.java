package lesson13_MultithreadingPart2.Vitalik;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierExample {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5);
        for (int i = 0; i < 5; i++) {
            int index = i;
            new Thread(() -> {
                try {
                    System.out.println("Машина " + index + " готовится");
                    Thread.sleep(100 + (int)(3000 * Math.random()));
                    System.out.println("Машина " + index + " готов");
                    cyclicBarrier.await();// ждем всех
                    System.out.println("Машина " + index + " запустилась");
                } catch (InterruptedException | BrokenBarrierException exception) {
                    exception.printStackTrace();
                }
            }).start();

            System.out.println("Машина создана. Index = " + index);
        }
    }
}

/*
Поток создан. Index = 0
Поток 0 готовится
Поток создан. Index = 1
Поток создан. Index = 2
Поток 1 готовится
Поток создан. Index = 3
Поток 2 готовится
Поток создан. Index = 4
Поток 3 готовится
Поток 4 готовится
Поток 3 готов
Поток 2 готов
Поток 1 готов
Поток 0 готов
Поток 4 готов
Поток 3 запустился
Поток 2 запустился
Поток 4 запустился
Поток 0 запустился
Поток 1 запустился
 */

/*
Машина создана. Index = 0
Машина 0 готовится
Машина создана. Index = 1
Машина создана. Index = 2
Машина 1 готовится
Машина создана. Index = 3
Машина 2 готовится
Машина создана. Index = 4
Машина 3 готовится
Машина 4 готовится
Машина 3 готов
Машина 4 готов
Машина 0 готов
Машина 1 готов
Машина 2 готов
Машина 2 запустилась
Машина 3 запустилась
Машина 0 запустилась
Машина 1 запустилась
Машина 4 запустилась
 */
