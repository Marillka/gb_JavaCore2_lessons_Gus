package lesson14_ОбзорСредствРазработки;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CurrentClass {
    private static final Logger logger = LogManager.getLogger(CurrentClass.class);


    // уровни логирования LogLevels от большего к меньшему (от самого критичного к менее критичному):
    /*
    Fatal - самая жуткая ошибка, скорее всего краш
    Error - какая то ошибка, исключение
    Warn - предупреждение, например осторожно сервис отвечает слишком долго
    Info - информативное логирование
    Debug -
    Trace - пишет все подряд
    Fatal > Error > Warn > Info > Debug > Trace
      6   >   5   >  4   >  3   >   2   >   1
     */


    public static void main(String[] args) {

        int logLevel = 3;

        int a = 50;
        if (logLevel <= 2) { // Debug
            System.out.println(System.currentTimeMillis() + " our method: a = " + a);
        }

        // Можем в самом сообщении в логере указать фигурные скобки, и в них в порядке очереди будут подставляться элементы. Если добавлять так объекты, то toString() выведется у них атоматически
        logger.debug("a = {}", a);


        int b = 10;
        if (logLevel <= 2) {// Debug
            System.out.println(System.currentTimeMillis() + " our method: b = " + b);
        }

        logger.debug("b = {}", b);

        int c = a + b;

        if (logLevel <= 3) { // Info
            System.out.println(System.currentTimeMillis() + " our method: c = " + c);
        }

        logger.info("c = {} + {} = {}", a, b, c);


        /*
        Нужен в ресурсах проекта log4j2.xml файл

        log4j состоит из 3 основных компонентов:
        1) Logger - занимается записью сообщений
        2) Appender -У одного логгера может быть несколько аппендеров, это класс который занимается (Похоэ на класс StringBuilder и метод append() - когда мы записываем в наш объект данные в хвост) записью в хвост
        3) Layout - форматирование сообщения для логгера. Задается в виде патерна.

        <PatternLayout pattern="%-5p %d{yyyy-MM-dd HH:mm:ss} [%t] %C (%F:%L) -
%m%n" />

5p под название логирования
%d{yyyy-MM-dd HH:mm:ss} дата в виде
[%t] - поток
%C - класс
(%F:%L) - файл и строчка
%m%n" - сообщение и перенос строки

<?xml version="1.0" encoding="UTF-8"?>
<Configuration>
    <Properties>
        <Property name="basePath">logs</Property>
    </Properties>
    <Appenders>
        <File name="FILE" fileName="${basePath}/logfile.log" append="true">
            <PatternLayout pattern="%-5p %d{yyyy-MM-dd HH:mm:ss} [%t] %C (%F:%L) -
%m%n" />

        </File>
        <Console name="STDOUT" target="SYSTEM_OUT">
            <PatternLayout pattern="%-5p %d{yyyy-MM-dd HH:mm:ss} [%t] %C (%F:%L) -
%m%n" />
        </Console>
    </Appenders>
    <Loggers>
        <Logger name="com.geekbrains" level="debug" />
        <Root level="info">
            <AppenderRef ref="STDOUT" />
            <AppenderRef ref="FILE" />
        </Root>
    </Loggers>
</Configuration>

         */

        // Получаем логгер для нашего конкретного класса. CurrentClass делает за нас вот эту обвязку our method: c. То есть в каком методе, в каком клвссе мы находимся.

        logger.trace("Trace");
        if (logLevel <= 2) { // Debug
            System.out.println(System.currentTimeMillis() + " our method: a = " + a);
        }
        logger.debug("Debug");
        logger.info("Info");
        logger.warn("Warn");
        logger.error("Error");
        logger.fatal("Fatal");

        // В домашке не надо делать логгер на все приложение. У каждого класс должен быть свой логер.


    }

}
