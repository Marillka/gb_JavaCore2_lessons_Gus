package lesson9_Generics;

import java.math.BigDecimal;

public class Stats<T extends Number> {
    private T[] nums;

    //private static T obj;// статическое поле класса не может быть дженериком.
    // в исключениях дженерики использовать нельзя
    public Stats(T... nums) {// T...  -> varargs - перечисления, будем передавать объекты в конструктор через запятую, сколько угодно и трансформироваться эти объекты будут в массив.
        // При старании типов будет указан верхний ограничитель, до уровня класса Number.
        this.nums = nums;
    }

    public double avg() {
        double sum = 0.0;
        for (int i = 0; i < nums.length; i++) {
            sum +=  nums[i].doubleValue();
        }
        return sum / nums.length;
    }

    public boolean isSameAvg(Stats<?> stats) {// ? - wildcard (может передаваться любой тип который extends Number)
        return Math.abs(this.avg() - stats.avg()) < 0.0001;// abs - модуль. В Java сравнивать double через равно нельзя (и примитивные с плавающей точкой). Примерно на каждом восьмом символе существует проблема с округление и когда мы ожидает увидеть один, там лежит 0.999999999999999. По модулю разница двух средних больше меньше 0.0001.
        // Не вздумайте считать деньги на работе через такое сравнение. Для этого есть класс BigDecimal.
    }

}
