package lesson1_interface.interfaces;

public class Student implements Studyable, HardWorkable {
    @Override
    public void study() {
        System.out.println("Студент учится только перед сессией");
    }

    @Override
    public void work() {
        System.out.println("Студент еще и работает");
    }

    @Override
    public void workHard() {
        System.out.println("Студент еще и как Папа Карло");
    }


}
