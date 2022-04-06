package lesson13_MultithreadingPart2.Vitalik;

import java.lang.invoke.StringConcatFactory;
import java.security.cert.CertificateParsingException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.locks.ReentrantLock;

public class LockExample {

    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        // Тот же ArrayList, у которого все методы синхронизированны. Он потокобезопасный. Проблема в том, что они медленно работают.
        Vector<String> vector = new Vector<>();
        // Синхронизированный лист. Тоже не эффективный.
        List<String> list = Collections.synchronizedList(new ArrayList<>());
        Hashtable<String, String> hashtable = new Hashtable<>();
        Map<String, String> map = Collections.synchronizedMap(new HashMap<>());

        // Мапа, которая может в сколько угодно потоков. РАЗОБРАТЬСЯ КАК РАБОТАЕТ.
        ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<>();
//        concurrentHashMap.get();


        // Потокобезопасный лист.
        CopyOnWriteArrayList<String> copyOnWriteArrayList = new CopyOnWriteArrayList();

        // Потокобезопасный список.
        CopyOnWriteArraySet<String> copyOnWriteArraySet = new CopyOnWriteArraySet();


        new Thread(LockExample::test).start();
        new Thread(LockExample::test).start();



    }

//    private static void test() {
//        if (lock.tryLock()) {// или lock().
//            System.out.println("Залочили реентрант лок");
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } finally {
//                lock.unlock();
//                System.out.println("Разлочили реентрант лок");
//            }
//        } else {
//            System.out.println("Замок уже был заблочен");
//        }

        /*
        Залочили реентратк лок
Замок уже был заблочен
Разлочили теернтрат лок
         */
//    }

    private static void test() {
        lock.lock();
        System.out.println("Залочили реентрант лок");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            System.out.println("Разлочили реентрант лок");
        }
    }

    /*
    Залочили реентрант лок
Разлочили реентрант лок
Залочили реентрант лок
Разлочили реентрант лок
     */

}



