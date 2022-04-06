package lesson13_MultithreadingPart2.Vitalik;

import java.util.concurrent.Semaphore;

public class SemaphoreExample {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(2);
//        Semaphore semaphore2 = new Semaphore(2);
        for (int i = 0; i < 5; i++) {
            int index = i;
            new Thread(() -> {
                try {
                    System.out.println("Поток " + index + " встал перед семафором");
                    semaphore.acquire();// пропускаем один из потоков (случайным образом).
//                    semaphore.isFair();// честность. Допустим потоки встали друг за другом 0 1 2 3 4, и допустим один пермит. То оно в порядке очереди от 0 1 2 3 4 будет пускать.

//                    // если не смогли забрать первый светофор (его пермит), то забираем второй.
//                    if (!semaphore.tryAcquire()) {
//                        semaphore2.acquire();
//                    }

                    System.out.println("Поток " + index + " получил доступ к семафору");
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("Поток " + index + " отдал ресурс семафора");
                    semaphore.release();// отпускаем поток, следующий может войти.
//                    semaphore2.release();
                }
            }).start();
        }
    }
}

/*
Поток 0 встал перед семафором
Поток 1 встал перед семафором
Поток 2 встал перед семафором
Поток 3 встал перед семафором
Поток 4 встал перед семафором

Поток 0 получил доступ к семафору
Поток 1 получил доступ к семафору

Поток 1 отдал ресурс семафора
Поток 0 отдал ресурс семафора

Поток 3 получил доступ к семафору
Поток 2 получил доступ к семафору

Поток 2 отдал ресурс семафора
Поток 3 отдал ресурс семафора

Поток 4 получил доступ к семафору

Поток 4 отдал ресурс семафора

 */
