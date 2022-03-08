package lesson10_DataBase;

import java.util.concurrent.atomic.AtomicInteger;

public class LambdaApp {

    public static void main(String[] args) {
        {//1
            final int i = 11;
            new Thread(() -> System.out.println(i)).start();
        }

        {//2
            int i = 11;// Не обязательно должна быть финальной. Теперь должна быть фактически финальной. То есть не должна меняться до ее использования.

        }

        {//3
            int i = 11;
            i++;
//            new Thread(() -> System.out.println(i)).start();// так уже не сработает, переменная поменялась.
        }

        {//4
            // Integer в обертке для конкурентной работы
            AtomicInteger ai = new AtomicInteger(0);

            ai.incrementAndGet();// увеличили значение - так можно
//            ai = new AtomicInteger(10);// а так нельзя
            // Если ссылку меняем то нельзя, если ссылку не изменяем - то можно.

            new Thread(() -> System.out.println(ai.get())).start();
        }



    }
}
