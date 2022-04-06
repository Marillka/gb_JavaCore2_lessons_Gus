package lesson13_MultithreadingPart2.HomeWork;

import java.util.ArrayList;
import java.util.Arrays;

public class Race {


    private ArrayList<Stage> stages;// список этапов


    public ArrayList<Stage> getStages() {// вернуть препятствие
        return stages;
    }

    public Race(Stage... stages) {
        this.stages = new ArrayList<>(Arrays.asList(stages));///???
    }
}
