package lesson14_ОбзорСредствРазработки;
// ********************* //
// SODD - stack overflow driving development.
public class AssertApp {

    /*
    Собственно почему Assertion. Когда то давно людям не был дарован jUnit, а код проверять было нужно. В Java придумали такую штуку как Assert. Assert это ключевое слово.

     */

    public static void main(String[] args) {
        int a = 10;
        int b = 5;

        // Если он не выполняется, то приложение падает с AssertionError. Не с исключением, а прям с error на конкретной строчке.
        // У нас оно не валится, хотя должно валиться. Почему? Не валиться. потому что в VM Options мы должны передать параметры -ae (enableAssertions)

        assert a < b;
        //Exception in thread "main" java.lang.AssertionError
        //	at lesson14_ОбзорСредствРазработки.AssertApp.main(AssertApp.java:17)

        // Для чего это использовали? Развешивали такие асерты в коде. И в тестовом режиме, когда хотели поймать какую то багулину, добавляли в параметры JVM флаг -ea. И как только приложение сталкивалось с проблемой и оно падало.


        System.out.println("ok");
    }
}
