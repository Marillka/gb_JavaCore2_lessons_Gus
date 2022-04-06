import lesson14_ОбзорСредствРазработки.Battery;
import lesson14_ОбзорСредствРазработки.Calculator;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class CalculatorTest {

    Calculator calculator;
    //************************//
    /*
        Макита. Мы создаем якобы батарейку, но которой на самом деле не существует. Макито:
        void - делает ничего
        методы - всегда возвращают null или 0 если примитив.
         */
    Battery battery = Mockito.mock(Battery.class);
    //************************//


//    // В каждый тест нужен новый объект. Потому что после теста состояние объекта может меняться.
//    @BeforeEach
//    void init() {
//        System.out.println("initialization");
//        calculator = new Calculator();
//    }


    //************************//

    @BeforeEach
    void init() {
        System.out.println("initialization");
        // Мне здесь нужна батарейка
        calculator = new Calculator(battery);
    }
    //************************//

    @AfterEach
    void tearDown() {
        System.out.println("test finished");
    }


    @Test
    @DisplayName("тестирование сложения")
    void testAddition() {
        // должны описать какую то логику
        // должны проверить, что калькулятор возвращает валидные данные.
        // тест зеленый, если он не упал. Если ничего не сломалось. Пустой тест всегда будет зеленым.
        // Assert - проверка.
        Assertions.assertEquals(10, calculator.sum(3, 7), "sum should be equals 10");
    }

    /*
     У вас есть какой то код, он работает, к вам прибегают ребята из техподдержки или менеджер, и говорят: А у вас здесть работает не так, я ожидаю получить 15, а вы мне отдаете 16.
     Что делают при подходе test Driven Development (разработка через тестирование)? Вы попытаетесь воспроизвести баг, где то подебажиться.
     Напишите тест на этот баг, и исправите баг. Тест останется.
     Когда мы все изменения подпираем в коде тестами, это и есть TDD.
     */
    @Test
//    @Disabled // выключение теста
    @Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
// тест считается успешным, во первых если внутри все проверки прошли, во вторых если все проверки уложились в 500 милисекунд
    void testMultiplication() {

        Assertions.assertEquals(15, calculator.multiply(3, 5));
    }

    // Очень популярный подход в разработке Development Sales and Marketing

    @Test
    void testDivisionWithException() {
        // С помощью этой проверки мы можем определить что наш метод в определенной ситуации кинет исключение.
        Assertions.assertThrows(ArithmeticException.class, () -> calculator.division(10, 0));
    }

    // http 200 - ок, проблемы нет
    // http 400(4xx) = 401 нет авторизации, 403 - нет доступа, 404 -не найдено.....
    // http 500- проблемы на стороне сервера. Произошло внезапное исключение на сервере, отвечает ровно 500. Если совсем хреново 502 или 503.


//    // может возникнуть необходимость протестировать метод на разных параметрах
//    @Test
//    void testAdditionMultiple() {
//        // Проблема такого подхода, если упадет первая проверка, мы не узнаем что падают оствльные. На первой проверке свалится. Тогда захочется написать 3 теста, очень похожищ друг на друга - неудобно. ДЕЛАЕМ ПАРАМЕТРИЗАЦИЮ.
//        Assertions.assertEquals(3, calculator.sum(1, 2));
//        Assertions.assertEquals(5, calculator.sum(3, 2));
//        Assertions.assertEquals(15, calculator.sum(13, 2));


    // Для того чтобы эти параметры передать в тест нужно создать источник данных - source. Csv - строки, записанные через какой то резделитель.
    // a , b , result
    // Тест один, но вызывается несолько раз.
    @CsvSource({
            "1, 2, 3",
            "3, 2, 5",
            "13, 2, 15"
    })
    @ParameterizedTest
    void testAdditionMultiple(int a, int b, int result) {
        Assertions.assertEquals(result, calculator.sum(a, b));
    }


    // Второй вариант передавать source. Если нужно объекты передавать, каких нибудь User - ов.
    // В этом классе просто нужен метод, которые будет давать данные для теста.
    @MethodSource("dataForAddition")
    @ParameterizedTest
    void testAdditionMultipleWithMethod(int a, int b, int result) {
        Assertions.assertEquals(result, calculator.sum(a, b));
    }

    // Для этого нужен стрим аргументов
    public static Stream<Arguments> dataForAddition() {
        List<Arguments> arguments = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            int a = random.nextInt(1000);
            int b = random.nextInt(1000);

            // Заполняем аргументы в таком порядке, как было в прошлом примере.
            arguments.add(Arguments.arguments(a, b, a + b));
        }

        return arguments.stream();
    }

    // Мягкая проверка - тут кароче error, но я пошел исполнять дальше на всякий случай.



// *********************************//
    /*
    В чем суть библиотеки Mockito. Это по сути mock - заглушка, которая позволяет нам вместо объекта подсовывать его якобы реализаци, которой мы можем сами управлять.
     */

    void testWithBattery() {
        // Когда мы у батарейки запросим сколько у нее процентов - она вернет нам 50. Нам не нужно создавать батарейку, заполнять ей поля, что то еще. Она просто вернет нам 50, потому что мы ее так попросили. Такую заглушку сделалали.
        // И дальше мы запускаем любой тест, любую логику и в нашем калькуляторе есть заглушка, которая ведет себя так, как мы ей рассказали.
        // В каком случае в продакшене используется макита. Когда у вас есть несколько сервисов, а вы тестируете один, а он по сети ходит во второй, и в момент запуска тестов у вас второго теста в сети нету и все запросы всегда будут падать. В этот момент нам помогает макита. Мы вместо второго теста вставляем заглушку и говорим вот это якобы сервис. И когда ты его спросишь - он тебе ответит вот так.
        Mockito.doReturn(50).when(battery.getPercent());
    }

//************************//

    /*
     Домашка

     */

}



