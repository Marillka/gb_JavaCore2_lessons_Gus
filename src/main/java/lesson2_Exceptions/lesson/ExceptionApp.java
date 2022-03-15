package lesson2_Exceptions.lesson;


// Исключение или исключительная ситуация - ситуация, которая возникает в результате выполнения программы.
// 1)  Error - Ошибка на уровне java машины - код не восстанавливается, потому что машина крашится.
// 2) Exception -Ошибка на уровне кода - может восстановиться.
// checked - проверяются на моменте компиляции
// unchecked - появляются в runtime
public class ExceptionApp {

    public static void main(String[] args) {

        try {
            int a = 10;
            int b = 2;
            System.out.println("a / b = " + (div(a, b)));
            b = 0;
            System.out.println("a / b = " + (div(a, b)));
        } catch (Exception e) {
            System.out.println("Поймал исключение");
            e.printStackTrace(System.out);
        }
        System.out.println();
        System.out.println();
        System.out.println();


//        System.out.println(divFirstAndSecond(args)); // либо пробросить в мейн или окружить блоком try catch.

        // open file
        try {
            // read file
            System.out.println(divFirstAndSecond(args));
        } catch (ArithmeticException | ArrayIndexOutOfBoundsException ex) {
            System.out.println("Попытались поделить на 0 или массив не той длинны");
        } catch (Exception ex) {
            System.out.println("Что-то другое " + ex.getMessage());
        } finally {
            // close file
            System.out.println("Будет выполнено всегда");
        }

    }

    static int div(int a, int b) {
        return div2(a, b);
    }

    static int div2(int a, int b) {
        return a / b;
    }


//    /**
//     * Передали массив. Вернем результат деления первого на второй.
//     *
//     * @param array
//     * @return
//     */
//    static int divFirstAndSecond(String[] array) {
//        return Integer.parseInt(array[0]) / Integer.parseInt(array[1]);
//    }


//    static int divFirstAndSecond(String[] array) {
//
//        int a = Integer.parseInt(array[0]);
//        int b = Integer.parseInt(array[1]);

//        if (b == 10) {
//            throw new IllegalStateException("Не умеем делить на 10");
//        }
//        return a / b;
//    }


//    static int divFirstAndSecond(String[] array) throws Exception {
//
//        int a = Integer.parseInt(array[0]);
//        int b = Integer.parseInt(array[1]);
//
//        if (b == 10) {
//            throw new Exception("Не умеем делить на 10");
//        }
//        return a / b;
//    }

//    static int divFirstAndSecond(String[] array) throws Exception {
//
//        int a = Integer.parseInt(array[0]);
//        int b = Integer.parseInt(array[1]);
//
//        if (b == 10) {
//            throw new MyCustomException("Не умеем делить на 10");
//        }
//        return a / b;
//    }

    static int divFirstAndSecond(String[] array) throws Exception {

        int a = Integer.parseInt(array[0]);
        int b = Integer.parseInt(array[1]);

        if (b == 10) {
            throw new MyCustomException(10);
        }
        return a / b;
    }


}
