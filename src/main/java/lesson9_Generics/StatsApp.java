package lesson9_Generics;

public class StatsApp {

    public static void main(String[] args) {
        Stats<Integer> intStats = new Stats<>(1,2,3,4,5,6);// new Object[6];
        Stats<Double> doubleStats = new Stats<>(1.0,2.0,3.0,4.0,5.0,6.0);// new Object[6];
        System.out.println("intStats avg = " + intStats.avg());
        System.out.println("doubleStats avg = " + doubleStats.avg());


        if (intStats.isSameAvg(doubleStats)) {
            System.out.println("Средние равны");
        } else {
            System.out.println("Не равны");
        }

        Integer[] arr = new Integer[] {1,2,3,4};
        System.out.println(getAvg(arr));
    }

    public static <T extends Number> double getAvg(T[] array) {
        return 0;
    }


}
