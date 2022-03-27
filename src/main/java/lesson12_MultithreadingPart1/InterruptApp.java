package lesson12_MultithreadingPart1;

public class InterruptApp {

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(() -> {
//            boolean isInterrupted = false;
            for (int i = 0; i < 5; i++) {
//                if (Thread.currentThread().isInterrupted() || isInterrupted) {// если поток прерван
                if (Thread.currentThread().isInterrupted()) {// если поток прерван
                    break;
                }
                System.out.println(i + 1);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
//                    isInterrupted = true;
                    Thread.currentThread().interrupt();// ВНУТРЕННИЙ. Concurrency in practice.
                }
            }
        });
        thread.start();
        Thread.sleep(1000);// усыпляем Main
        //  способ убить поток stop () - зачеркнут - запрещен с Java 2+. Плох тем что он убивает поток мгновенно. Не убрали его потому что - обратная совместимость.
//        thread.stop();
        thread.interrupt();// прервать исполнение потока. ВНЕШНИЙ.

        /*
        1
        2
        3
        java.lang.InterruptedException: sleep interrupted
        at java.base/java.lang.Thread.sleep(Native Method)
	    at lesson12_MultithreadingPart1.InterruptApp.lambda$main$0(InterruptApp.java:11)
	    at java.base/java.lang.Thread.run(Thread.java:833)
        4
        5
       */

    }

    /*
     Гарантированного способа прерварть поток (безболезненно) - нет.
     В Java потоки останавливаются кооперативно. То есть поток должен сомостоятельно принять решение о том, когда ему остановиться. Мы не можем прервать поток здесть и сейчас.
     Можем запросить прерываение у этого потока, и он остановиться тогда, когда посчитает это нужным.
     */

}
