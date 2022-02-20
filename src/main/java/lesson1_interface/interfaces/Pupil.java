package lesson1_interface.interfaces;

public class Pupil implements Studyable {

    @Override
    public void study() {
        System.out.println("Ученик учится");
    }
}
