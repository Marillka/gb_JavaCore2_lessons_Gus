package lesson2_Exceptions.пример_интерфейсов;

public abstract class Transport {
    int capacity;// загрузка

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Посадка в транспорт
     */
     abstract void load();
}
