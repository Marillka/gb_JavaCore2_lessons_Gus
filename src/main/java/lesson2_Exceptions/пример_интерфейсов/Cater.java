package lesson2_Exceptions.пример_интерфейсов;

public class Cater extends Transport implements Swimable {
    private int speed;

    @Override
    void load() {
        System.out.println("Диско-шар и алкоголь");
    }
}
