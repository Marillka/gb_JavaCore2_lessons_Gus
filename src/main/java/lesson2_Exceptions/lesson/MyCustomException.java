package lesson2_Exceptions.lesson;


// Проверяемое от Exception
// Непроверяемое от RuntimeException
//public class MyCustomException extends RuntimeException {

public class MyCustomException extends Exception {

//    // Переопределяем метод со строкой
//    public MyCustomException(String message) {
//        super(message);
//    }

    public MyCustomException(int a) {
        super("Не умею делить на " + a);
    }
}
