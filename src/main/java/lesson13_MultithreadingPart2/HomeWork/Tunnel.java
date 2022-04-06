package lesson13_MultithreadingPart2.HomeWork;

import java.sql.Struct;
import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {
    Semaphore semaphore = new Semaphore(2, true);


    public Tunnel() {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
    }

    @Override
    public void go(Car c) {
        try {

                semaphore.acquire();
                System.out.println(c.getName() + " начал этап: " + description);
                Thread.sleep(length / c.getSpeed() * 1000);


        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(c.getName() + " закончил этап: " + description);
            semaphore.release();
        }
    }
}



