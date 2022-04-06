package lesson13_MultithreadingPart2.HomeWork;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class MainClass {
    // Количество машин
    public static final int CARS_COUNT = 4;

    public static void main(String[] args) {
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));// ноый рейс
        Car[] cars = new Car[CARS_COUNT];// колво машин
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10));// характеристики машин
        }
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        CyclicBarrier cyclicBarrier = new CyclicBarrier(CARS_COUNT);
        Semaphore semaphore = new Semaphore(2);//

        for (int i = 0; i < CARS_COUNT; i++) {
            new Thread(cars[i]).start();
        }


//        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
//        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }
}





