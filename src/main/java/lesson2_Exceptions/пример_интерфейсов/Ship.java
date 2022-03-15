package lesson2_Exceptions.пример_интерфейсов;

public class Ship extends WaterTransport {
    int waterLine;

    public int getWaterLine() {
        return waterLine;
    }

    public void setWaterLine(int waterLine) {
        this.waterLine = waterLine;
    }

    @Override
    void load() {
        System.out.println("Погрузка корабля");
    }

    @Override
    public void swim() {
        System.out.println("Корабль плывет очень медленно");
    }
}
