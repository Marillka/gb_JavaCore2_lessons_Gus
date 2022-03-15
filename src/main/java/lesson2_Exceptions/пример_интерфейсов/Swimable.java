package lesson2_Exceptions.пример_интерфейсов;

public interface Swimable {

    // default - раелизация по умалчанию. Если иного не оговорено, объект будет вести себя вот так. Ипользуется именно в интерфейсах.
    default void swim() {
        System.out.println("Плывет как-то по умолчанию");
    }
}
