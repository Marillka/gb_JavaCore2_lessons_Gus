package lesson13_MultithreadingPart2;


import java.util.Random;
import java.util.concurrent.CyclicBarrier;

// циклический барьер
public class CyclicApp4 {

    private static Random random = new Random();

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5);// принимает количество parties. Синхронизируем на 5


        /*
        Подождали пока все приготовились - всех отпустили. Они едут едут едут и в тот момент когда все доехали - можем написать что гонка закончилась.
        await() - ожидает что в определенной точке соберется нужное колчиство потоков. Как только веса становится достаточно, дно проваливается и все потоки проходят дальше.
        Пример Квиз на корпоративе. Пока маршрутка не набьется - не поедем.
         */
        for (int i = 0; i < 5; i++) {
            int w = i + 1;// чтобы индексации с нуля не было
            new Thread(() -> {
                try {
                    System.out.println(w + " готовиться");
                    Thread.sleep(random.nextInt(5) * 1000);
                    System.out.println(w + " готов");
                    // когда все потоки готовы - хотим их дождаться.
                    cyclicBarrier.await();// ожиданеие. В каждом потоке говорим, здесь в этой точке мы ожидаем, что получим какое то разрешение

                    System.out.println(w + " поехал");
                    Thread.sleep(random.nextInt(5) * 1000);
                    System.out.println(w + " доехал");
                    // все едут разное время и мы хотим синхронизироваться
                    cyclicBarrier.await();// опять ждем

                    System.out.println("Гонка закончилась");
                } catch (Exception e) {
                    // ignore
                }
            }).start();
        }
    }


    /*
1 готовиться
4 готовиться
2 готовиться
3 готовиться
5 готовиться

4 готов
3 готов
2 готов
1 готов
5 готов

4 поехал
2 поехал
3 поехал
5 поехал
1 поехал

5 доехал
3 доехал
4 доехал
1 доехал
2 доехал

Гонка закончилась
Гонка закончилась
Гонка закончилась
Гонка закончилась
Гонка закончилась
     */

}
