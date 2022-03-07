package lesson8;

import java.util.Optional;

// since Java8+. Призван избавить от необходимости работать с null. Нужен для того, чтобы не передавать null.
// К optionals МОЖНО ПРИМЕНЯТЬ те операции, которые применимы к стримам.
public class OptionalApp {

    public static void main(String[] args) {

        // Класс обертка над любым другим классом. Например над строкой, юзером, человеком, котом, неважно.
        Optional<String> optional = Optional.of("str");

        {//1
            // get() - достать объект
            // orElse() - получи запись объекта, если нету -> верни то то. То есть если значения в optional нет, он вернет "EmptyString".
            // orElseThrow() - можно кинуть исключение, если объекта нет
            String fromOpt = optional.orElse("EmptyString");
        }

        {
            optional.map(s -> s.toUpperCase());
        }

    }
}
