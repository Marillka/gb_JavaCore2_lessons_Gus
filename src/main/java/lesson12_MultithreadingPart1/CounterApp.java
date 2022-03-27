package lesson12_MultithreadingPart1;

public class CounterApp {

    private static class Counter {
        private int value;

        public Counter() {
            this.value = 0;
        }

        public Counter(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        // synchronized - одновременно к этому методу имеет доступ только один поток.
        public synchronized void increment() {
            this.value++;
        }
    }

    public static void main(String[] args) throws InterruptedException {

        final Counter counter = new Counter();

//        counter = new Counter();// не сработает

        /*
         Для того чтобы мы могли использовать внешнюю переменную внутри лямбда выражения или
         анонимного класса она должна быть финальной.
         Effectively final - переменная которая создана, но фактически не менялась. Мы создали Counter, как только мы присвоили ему новое значение, Java такая ага, значит эта переменная может менять значение - значит я ее не буду использовать ее в анонимный классах и лямбда выражениях, ты мне дай финальную переменную.
         Effectively final - напротив этой переменной нету final, но фактически она не менялась. Java 8+. На Java 7- обязательно надо было бы написапть final, иначе бы не скомпилировалось.
         */
        Runnable runnable = () -> {
            for (int i = 0; i < 10000; i++) {
                /*
                 Почему тогда здесь можно изменять counter. Мы здесь его не изменяем, не создаем новую ссылку. Здесь мы непосредственно изменяем уже сущестующий счетчик, значение счетчика. Изменяется значение внутри, но счетчик остается тем же самым.
                 */
                counter.increment();
            }
        };

        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        // Если на засинхронизировать будут такие результаты.
        //11365
        //12212
        //11471
        //11101

        System.out.println(counter.getValue());
    }
}
