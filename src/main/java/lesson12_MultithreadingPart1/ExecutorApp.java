package lesson12_MultithreadingPart1;

import java.util.concurrent.*;

/*
Создание нового потока это дорого. А что делать взамен?
Пул потоков- поток живет постоянно.
 */
public class ExecutorApp {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // Один поток, в который по очереди залетают сервисы.
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        // Создали 10 потоков разом. И можем в эти потоки отправлять какие то задания.
        ExecutorService executorService1 = Executors.newFixedThreadPool(10);// 10 раз вызовется new Thread.

        // Пул потоков, которые могут переиспользоваться, но он резиновый. То есть когда под нагрузкой в CachedThreadPool попадает много заданий (100 задач прилетело - его раздуло до 100 потоков), и когда прилетает 101 задача, он не создает новый поток, а засовывает эту задачу в освободивщийся поток. И только потом если он не смог, он создает новый. Со временем сжимается.
        ExecutorService executorService3 = Executors.newCachedThreadPool();

        // Может ставить задачи в очередь по расписанию. Ты говоришь каждые 10 минут делай вот этот вот Runnable. И вот этот вот ThreadPool начинает сам его крутить и каждые 10 минут обрабатывать.
        ExecutorService executorService4 = Executors.newScheduledThreadPool(10);


        // execute()
        executorService3.execute(() -> System.out.println(Thread.currentThread().getName()));
        executorService3.execute(() -> System.out.println(Thread.currentThread().getName()));
        executorService3.execute(() -> System.out.println(Thread.currentThread().getName()));
        executorService3.execute(() -> System.out.println(Thread.currentThread().getName()));
        /*
         submit() принимает либо Runnable, либо Callable. Возвращает Future.
         Callable отчилается ровно так от Runnable как процедура отличается от функции. Callable - интерфейс, который возвращает результат (Runnable - задача которая будет сделанна и все и закончена). Callable ровно тоже самое, но с результатом. Что то там параллельно сделали и вернули результат.
         Future - по сути описание нашей задачи. То есть мы говорим выполни задачу и он дает нам билетик, котоый идентифицирует нашу задачу в этой очереди исполнений.
         */
        Future<?> future = executorService3.submit(() -> System.out.println("smth"));
        /*
        get() - блокирующий. То же самое что join() у обычного Thread-а. Будем ждать пока задача не будет выполнена.
        isDone() - не блокирующий. Он проверяет типо я еще работаю.
        cancel() - отмени. Похоже на обычное прерываение.
         */
//        future.get();

        Future<String> stringFuture = executorService3.submit(new MyCallable());

        System.out.println(stringFuture.get());

        executorService3.shutdown();// executorService перестает принимать входищие задачи,но заканчивает обработку старых. Если его не вызвать - приложение не завершиться.
        executorService.shutdownNow();// завершить здесь и сейчас. Перестает принимать задачи, так еще и всех рабочим задачам рассылает interrupted (остановку).

        //pool-3-thread-1
        //pool-3-thread-2
        //pool-3-thread-1
        //pool-3-thread-1

    }

    private static class MyCallable implements Callable<String> {
        @Override
        public String call() throws Exception {
            return "string from Callable";
            /*
            Вот эту строчку он сгеренирует в новом потоке и вернет ее в существующий поток.
             */
        }
    }


    /*
    В домашке нужно заменить все треды на ExecutorService (пул потоков. Создать пул сразу и потом просто его переиспользовать.
    Те Runnable, которые пихали в new Thread вы будете отправлять в ExecutorService.
    Если вам где то потребуются гетеры для ExecutorService - вы напишите геттер.
     */


}
