package lesson13_MultithreadingPart2.HomeWork;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class Car implements Runnable {
    private String name;
    private int speed;
    private Race race;
    private static int CARS_COUNT;

    private int count = 0;

    public void incrementCount(int count) {
        this.count += count;
    }


    static {
        CARS_COUNT = 0;
    }

    public Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }


    final int THREADS_COUNT = 4;
    CyclicBarrier cyclicBarrier = new CyclicBarrier(THREADS_COUNT);
     static CountDownLatch cdl = new CountDownLatch(4);

    @Override
    public void run() {

//        try {
//
//        } catch () {
//
//        }

        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(this.name + " готов");
            cdl.countDown();
//            System.out.println(cdl.getCount());
//            cdl.await();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                cdl.await();
                System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }





        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }

    }
}
