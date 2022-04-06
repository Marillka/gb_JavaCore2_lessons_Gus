package lesson13_MultithreadingPart2.Vitalik;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CountDownLatchExample {
    public static void main(String[] args) {
        CountDownLatch cdl = new CountDownLatch(6);// 6 - счетчик
        System.out.println("Начинаем");
        for (int i = 0; i < 6; i++) {
            int index = i;
            new Thread(() -> {
                try {
                    Thread.sleep((int) (500 * Math.random()));
                    System.out.println("Машина " + index + " заехала на парковку");
                    cdl.countDown();// уменьшаем счетчик на единицу
//                    System.out.println(cdl.getCount());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        try {
            System.out.println("Ждем пока заедут все машины ");
            cdl.await();// На этой строке будем стоять пока счетик не станет равен 6
//            cdl.await(1000, TimeUnit.MILLISECONDS);// Сколько ждать поток
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Все машины проехали, открываем парковку");
    }
}

/*
Начинаем
await начал выполняться
Поток 1 закончила работу
Поток 4 закончила работу
Поток 5 закончила работу
Поток 0 закончила работу
Поток 2 закончила работу
Поток 3 закончила работу
Здесь мы закончили
 */

/*
Начинаем
await начал выполняться
Поток 5 закончила работу
5
Поток 0 закончила работу
4
Поток 2 закончила работу
3
Поток 1 закончила работу
2
Поток 3 закончила работу
1
Поток 4 закончила работу
0
Здесь мы закончили
 */

//    public static void main(String[] args) {
//        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
//        int newArrayLength = arr.length / 2;
//        int[] result = new int[newArrayLength];
//        CountDownLatch cdl = new CountDownLatch(newArrayLength);
//        System.out.println("Начинаем");
//        for (int i = 0; i < newArrayLength; i++) {
//            int index = i;
//            new Thread(() -> {
//                switch (index) {
//                    case 0:
//                        result[0] = arr[0] + arr[1];
//                        break;
//                    case 1:
//                        result[1] = arr[2] + arr[3];
//                        break;
//                    case 2:
//                        result[2] = arr[4] + arr[5];
//                        break;
//                    case 3:
//                        result[3] = arr[6] + arr[7];
//                        break;
//                    case 4:
//                        result[4] = arr[8] + arr[9];
//                        break;
//                    case 5:
//                        result[5] = arr[9];
//                        break;
//                }
//
//                cdl.countDown();
//            }).start();
//        }
//
//        try {
//            System.out.println("await начал выполняться");
//            cdl.await();// На этой строке будем стоять пока счетик не станет равен 6
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println("Здесь мы закончили");
//
//        System.out.println(Arrays.stream(result).sum());
//        System.out.println(Arrays.toString(result));
//    }
//}11``

/*
Начинаем
await начал выполняться
Здесь мы закончили
55
[3, 7, 11, 15, 19]
 */
