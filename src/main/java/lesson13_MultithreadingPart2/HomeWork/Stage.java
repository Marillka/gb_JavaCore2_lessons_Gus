package lesson13_MultithreadingPart2.HomeWork;

public abstract class Stage {// Этап


    protected int length;// длинна
    protected String description;// описание
    public String getDescription() {
        return description;
    }
    public abstract void go(Car c);

}
