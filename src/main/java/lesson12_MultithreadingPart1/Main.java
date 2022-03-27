package lesson12_MultithreadingPart1;

public class Main {

    private static class MyRunnable implements Runnable {
        @Override
        public void run() {

//            Thread.currentThread().getName();

            try {
                // если пытаемся убить поток, который спит. Он спит и не готов обрабатывать команды.
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                // если все равно хотим упасть. То превращаем проверяемое исключение в непроверяемое.
                throw new RuntimeException(e);// бросаем непроверяемое исключение
            }

            System.out.println("Do smth");
        }
    }

    private static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("Do smth");
        }
    }

    public static void main(String[] args) {

        {
            Thread thread = new Thread(new MyRunnable());
            thread.start();

            Thread thread2 = new MyThread();
            thread2.start();

            Thread thread3 = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("Do smth");
                }
            });
            thread3.start();

            Thread thread4 = new Thread(() -> {
                System.out.println("Do smth");
            });
            thread4.start();
        }


        {// разделение задачи РАБОТАТЬ НЕ БУДЕТ. Все равно будет напечатанно 2 раза.
            Runnable task = new MyRunnable();

            Thread thread5 = new Thread(task);
            Thread thread6 = new Thread(task);
            thread5.start();
            // Ждать поток определенное количество времени.
            try {
                thread5.join(4000);// Если за 4 секунды ты не завершился, то join кинет InterruptedException. И основной метод разблокируется с ошибкой.
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            thread6.start();
        }


        //GB - главный кандинат на имя поток демон.
        // join - блокирующий вызов. Работает как дебаг, дошли до красной точки и ждем ее исполнения. Ожидание подключения клиента.

        //





    }
}
