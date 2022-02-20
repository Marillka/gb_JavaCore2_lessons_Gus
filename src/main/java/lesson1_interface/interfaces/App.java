package lesson1_interface.interfaces;

import java.util.Arrays;

public class App {
    public static void main(String[] args) {
        Student student = new Student();
        Pupil pupil = new Pupil();

//        Studyable studyable = student;
//        studyable.study();

        Studyable[] studyables = new Studyable[]{student, pupil};
        for (Studyable studyable : studyables) {
            studyable.study();
        }

        System.out.println(Arrays.toString(args));

    }
}
